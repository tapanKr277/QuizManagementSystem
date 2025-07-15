Quiz Management System
🚀 About This Project
The Quiz Management System allows students to register, take exams, track their performance, and practice their knowledge. Teachers or admins can manually create quizzes by adding questions and answers or upload a PDF file containing questions (e.g., books). After submitting a quiz, students receive their results immediately. This system also includes an AI-powered feature to automatically generate quiz questions from uploaded PDFs, utilizing the Gemini API for intelligent question generation.

🛠️ Features
Student Registration & Authentication: Users can register, log in, and securely access the quiz system.

Take Quizzes: Students can take quizzes, with results generated instantly after submission.

Performance Visualization: Students can view their quiz performance and track progress.

Quiz Creation: Admins/Teachers can create quizzes manually by adding questions and answers or by uploading question PDFs.

AI-Powered Question Generation: Using the Gemini API (or similar AI models), the system can extract and generate quiz questions from uploaded PDFs, providing an interactive learning experience.

Chatbot Support: A chatbot interface is available to assist users with queries and provide support.

🖥️ Tech Stack
Frontend: Angular

Backend: Spring Boot, JWT Authentication, Spring Security

Microservices: Spring Cloud (Eureka & Feign Client for service discovery and communication)

API Integration: Gemini API for question generation from PDFs

Database: MySQL

Swagger: API documentation and testing

Other Tools: Docker, Kubernetes, AI (for question generation)

🏃‍♂️ How to Run This Application
1. Clone the Repository
bash
Copy
Edit
git clone https://github.com/tapanKr277/QuizManagementSystem.git
cd QuizManagementSystem
2. Backend Setup (Spring Boot)
Install Dependencies
Navigate to Backend Folder:

bash
Copy
Edit
cd backend
Run the Spring Boot Application:

If you're using Maven:

bash
Copy
Edit
mvn clean install
mvn spring-boot:run
Or if you're using Gradle:

bash
Copy
Edit
./gradlew bootRun
Database Configuration
Make sure your database (e.g., MySQL) is running and properly configured. Update the application.properties file in the src/main/resources folder with your database connection details.

JWT Authentication & Spring Security
The application uses JWT and Spring Security for secure user authentication. Make sure you have proper user credentials for testing or access the default credentials provided in the application.

3. Frontend Setup (Angular)
Install Dependencies
Navigate to Frontend Folder:

bash
Copy
Edit
cd frontend
Install Node Modules:

bash
Copy
Edit
npm install
Run the Angular Application:

bash
Copy
Edit
ng serve
The application will run at http://localhost:4200/.

4. AI-powered Question Generation (Gemini API Integration)
The AI question generation system is integrated using the Gemini API. When a user uploads a question PDF, the system uses the API to extract and generate relevant questions for the quiz.

To set up the AI feature:

You need a Gemini API key.

The API is integrated in the backend service that processes the uploaded PDFs and generates questions automatically.

You can configure the Gemini API key in the backend's application.properties or any relevant configuration file.

🔧 System Architecture
Frontend (Angular): User interfaces for registration, quiz-taking, performance visualization, and quiz creation.

Backend (Spring Boot):

JWT & Spring Security: For user authentication and authorization.

Microservices: Using Spring Cloud Eureka for service discovery and Feign for inter-service communication.

AI Question Generation: A microservice that interacts with the Gemini API to process uploaded PDFs and generate quiz questions.

Database (MySQL): Stores user data, quiz results, and quiz content.

Swagger: API documentation and testing interface for backend services.

⚙️ AI Question Generation (Technical Details)
The AI feature works as follows:

The user uploads a PDF document containing questions.

The backend calls the Gemini API to analyze the uploaded content.

The API processes the PDF, extracts relevant text, and generates questions from the text.

These generated questions are then stored in the database and made available to the student for quiz-taking.

📝 How to Use the System
Student Registration: Sign up with your email, username, and password to create a student account.

Take a Quiz: Select a quiz and start answering questions. After submission, your results will be immediately displayed.

Admin/Teacher Quiz Creation: Admins can either manually create quizzes by inputting questions/answers or upload PDFs containing questions. The system will automatically extract questions using AI and add them to the quiz bank.

AI Assistance: For any support or doubts, the chatbot will help you navigate the platform.

📢 Future Enhancements
AI-based Performance Analysis: Use AI to suggest areas for improvement based on student performance.

Real-time Collaboration: Enable real-time collaboration for students during practice sessions.

Enhanced Chatbot: Integrate more advanced AI-driven chatbots for better support.

📫 Connect with Me
🌐 Portfolio: tapmad.space

💼 LinkedIn: linkedin.com/in/tapan-kumar-0447691b0

📧 Email: tapankr277@gmail.com
