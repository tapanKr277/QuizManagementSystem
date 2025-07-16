package com.gyanpath.security.controller;

import com.gyanpath.security.dto.QuizAttemptDto;
import com.gyanpath.security.dto.QuizDto;
import com.gyanpath.security.dto.UserDto;
import com.gyanpath.security.exception.QuizNotFoundException;
import com.gyanpath.security.exception.ResourceNotFoundException;
import com.gyanpath.security.exception.UserNotFoundException;
import com.gyanpath.security.service.SeleniumService;
import com.gyanpath.security.service.UserService;
import com.gyanpath.security.service.client.QuizServiceClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/auth/user/")
@Tag(name = "User Controller", description = "User controller for creating user")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

	@Autowired
	private QuizServiceClient quizServiceClient;

	@Autowired
	private SeleniumService seleniumService;


	@GetMapping("/")
	public String test(){
		return "Hello world";
	}

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/get-user-profile")
	public ResponseEntity<UserDto> getUserByUserName(@RequestParam String username) throws ResourceNotFoundException {
		UserDto userDto = null;
		try{
			userDto = userService.getUserDtoByUserName(username);
		}
		catch (Exception e){
			throw new ResourceNotFoundException("User not found, error is "+ e.getMessage());
		}

        return ResponseEntity.ok(userDto);
	}


	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/get-user-id")
	public ResponseEntity<UserDto> getUserById(@RequestParam(name = "user_id") Short userId){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
	}


	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/create-quiz-attempt")
	public ResponseEntity<QuizAttemptDto> createQuizAttempt(@RequestParam(name = "quiz_id") Short quizId,
															@RequestParam(name = "user_id") Short userId) throws QuizNotFoundException, QuizNotFoundException {

//		 = new ChromeDriver();

//		String examUrl = "http://localhost:8080/exam-page?quiz_id=1&user_id=2";
//		driver.get(examUrl);
//		driver.window.ma

		System.setProperty("webdriver.chrome.driver", "C:\\Installers\\chromedriver-138\\chromedriver-win64\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-fullscreen");
//
		WebDriver driver = new ChromeDriver(options);
//
//		// Replace with your actual exam URL
		String examUrl = "http://localhost:4200/exam/${quiz_id}";
		driver.get(examUrl);
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://localhost:8080/api/auth/user/create-quiz-attempt?quiz_id=13&user_id=1");
//		driver.get("http://localhost:4200/exam/${quiz_id}");
//		driver.manage().window().fullscreen();



		return quizServiceClient.createQuizAttempt(quizId, userId);
	}

		// Endpoint to start the quiz and open in full-screen
//		@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//		@GetMapping("/create-quiz-attempt")
//		public ResponseEntity<QuizAttemptDto> createQuizAttempt(
//				@RequestParam(name = "quiz_id") Short quizId,
//				@RequestParam(name = "user_id") Short userId) throws QuizNotFoundException {
//
//			// Step 1: Create the quiz attempt
//			QuizAttemptDto quizAttemptDto = quizServiceClient.createQuizAttempt(quizId, userId).getBody();
//
//			// Step 2: Trigger Selenium to open the quiz in full-screen
//			if (quizAttemptDto != null) {
//				// Construct the URL of the quiz page (you can pass the actual URL dynamically)
//				String quizUrl = "http://localhost:4200/exam/" + quizId;  // Example URL structure
//				seleniumService.startQuizInFullScreen(quizUrl); // This will open the quiz in full-screen
//			}
//
//			// Return the response after quiz attempt creation
//			return ResponseEntity.ok(quizAttemptDto);
//		}

	// Update a quiz attempt - Accessible by USER and ADMIN
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PostMapping("/update-quiz-attempt")
	public ResponseEntity<QuizAttemptDto> updateQuizAttempt(@RequestBody QuizAttemptDto quizAttemptDto) throws QuizNotFoundException {
		return quizServiceClient.updateQuizAttempt(quizAttemptDto);
	}

	// Submit a quiz attempt - Accessible by USER and ADMIN
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PostMapping("/submit-quiz-attempt")
	public ResponseEntity<QuizAttemptDto> submitQuizAttempt(@RequestBody QuizAttemptDto quizAttemptDto) throws QuizNotFoundException {
		return quizServiceClient.submitQuizAttempt(quizAttemptDto);
	}

	// Get quiz attempt list for a user - Accessible by USER and ADMIN
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/get-user-quiz-attempt-list")
	public ResponseEntity<List<QuizAttemptDto>> getUserQuizAttemptList(@RequestParam(name = "user_id") Short userId) {
		return quizServiceClient.getUserQuizAttemptList(userId);
	}

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/get-quiz-by-id")
	public ResponseEntity<QuizDto> getQuizById(@RequestParam(name = "quiz_id") Short quizId) throws QuizNotFoundException {
		return quizServiceClient.getQuizById(quizId);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PostMapping("/update-user-data")
	public ResponseEntity<UserDto> updateUserPartialData(@RequestBody UserDto userDto) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserPartialData(userDto));
	}

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/get-user-number-of-quiz-attempt-month-wise")
	public ResponseEntity<List<Map<Integer, Integer>>> getNumberOfQuizAttemptMonthWise(@RequestParam Short userId, @RequestParam Year year){
		UserDto user = userService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(quizServiceClient.getNumberOfQuizAttemptMonthWiseInYear(userId, year).getBody());
	}

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/update-warning-count")
	public ResponseEntity<Map<String, Integer>> updateWarningCount(@RequestParam Short quizId,@RequestParam Short userId,@RequestParam Integer warningCount) throws QuizNotFoundException {
		UserDto user = userService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(quizServiceClient.updateWarningCount(quizId, userId, warningCount).getBody());
	}


}
