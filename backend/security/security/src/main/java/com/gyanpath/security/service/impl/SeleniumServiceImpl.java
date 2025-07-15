package com.gyanpath.security.service.impl;

import com.gyanpath.security.service.SeleniumService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class SeleniumServiceImpl implements SeleniumService {

    // Path to the chromedriver executable, set this to the path on your system
    private static final String CHROME_DRIVER_PATH = "https://storage.googleapis.com/chrome-for-testing-public/131.0.6778.264/win64/chromedriver-win64.zip";

    // Method to start the quiz page in full-screen mode
    @Override
    public void startQuizInFullScreen(String quizUrl) {
        // Set the system property for chromedriver path
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

        // Create ChromeOptions object
        ChromeOptions options = new ChromeOptions();

        // Use Chrome's --start-fullscreen argument to open in full screen mode
        options.addArguments("--start-fullscreen");

        // Create a new instance of ChromeDriver with the specified options
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open the provided URL (e.g., the quiz page)
            driver.get(quizUrl);

            // If needed, you can add additional logic here to interact with the quiz page

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the browser is closed after the quiz is started
            // This step can be modified based on your use case (e.g., close after quiz completion)
            driver.quit();
        }
    }

}
