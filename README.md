# 🎯 Quiz Management System

An intelligent, full-featured quiz platform for students and educators, built with Angular, Spring Boot, Microservices, and AI-powered question generation.

---

## 📖 About This Project

The **Quiz Management System** allows:

- 📚 **Students** to register, take quizzes, receive instant results, and track performance.
- 🧑‍🏫 **Admins/Teachers** to create quizzes either manually or by uploading PDFs (like books or notes).
- 🤖 **AI-Generated Quizzes** using **Gemini API**, which extracts and creates questions from uploaded documents.
- 💬 A built-in **chatbot** to assist users with navigating and using the system.

---

## 🚀 Live Demo

🔗 [Click here to access the live app](https://quiz-management-system-smoky.vercel.app/)

---

## 🔧 Tech Stack

### Frontend:
- Angular
- HTML5, SCSS
- Angular Material

### Backend:
- Spring Boot
- Spring Security + JWT
- Spring Cloud (Eureka, Feign)
- MySQL
- Swagger

### AI Integration:
- **Gemini API** for extracting and generating questions from uploaded PDFs.

### DevOps & Tools:
- Docker
- Git & GitHub
- Vercel (for frontend hosting)
- Postman (for API testing)

---

## 📂 Microservices Architecture

- **API Gateway** – Routes requests to services
- **Auth Service** – Manages registration, login, JWT tokens
- **Quiz Service** – Manages quiz creation, attempt, and results
- **AI Service** – Processes PDFs and uses Gemini API to generate questions
- **Discovery Server (Eureka)** – Service registry for communication
- **Admin Dashboard** – For uploading PDFs, adding quizzes, managing users

---

## 📈 Features

### 👨‍🎓 Student Features
- Register & login
- Attempt quizzes
- See results instantly
- Track performance over time

### 👨‍🏫 Admin Features
- Create quizzes manually or via PDF
- Automatically generate questions using AI
- Visualize participation and performance stats

### 🤖 AI Question Generator
- Upload any PDF with educational content
- AI (Gemini API) scans the content
- Generates meaningful questions from the material
- Questions added directly to quiz bank

### 💬 Chatbot Assistance
- Built-in support chatbot to help students & teachers
- Guides users through quiz flow, creation, and navigation

---

## 🛠️ How to Run the Project Locally

### 1. Clone the Repository

git clone https://github.com/tapanKr277/QuizManagementSystem.git
cd QuizManagementSystem

---

## 🏁 How to Run the Project Locally (Continued)

### 2. Setup Backend

Navigate to backend directory:

cd backend

# For Maven:
mvn clean install
mvn spring-boot:run

# OR for Gradle:
./gradlew bootRun

### 3. Setup Frontend

cd frontend

npm install

ng serve

App will be available at:
👉 http://localhost:4200/


🤖 AI Integration Details
When a teacher/admin uploads a PDF:

The backend calls the Gemini API

Extracts text content from the PDF

Converts it into relevant quiz questions

Saves it to the quiz database automatically

✅ This process is powered by a dedicated microservice and runs asynchronously using Feign Clients and background processing.

📊 API Documentation
Swagger is available at:

bash
Copy
Edit
http://localhost:PORT/swagger-ui/
Use it to test and explore REST endpoints for all services.

🎓 Education Use Case
Ideal for:

🏫 Schools & Colleges

🧪 Coaching Centers

💡 Self-Practice Platforms

🧠 Online Mock Test Portals

🧑‍💻 Author
Tapan Kumar
📫 Email: tapankr277@gmail.com
🌐 Portfolio: tapmad.space
💼 LinkedIn: linkedin.com/in/tapan-kumar-0447691b0

📌 Future Enhancements
📊 Performance insights powered by AI

📈 Real-time student dashboards

🔐 Role-based dashboards for teachers vs students

💬 Enhanced natural language chatbot support

Built with ❤️ using Angular, Spring Boot, Microservices & AI

<!-- Optional Enhancements --> <!-- Let me know if you'd like: - GitHub badges - Screenshots or animated GIFs - Dark mode styling - Deployment CI/CD status -->
markdown
Copy
Edit

You can paste this directly into your existing `README.md` file under the previous sections.

Let me know if you'd like:
- 📸 Screenshots or demo GIFs  
- 📦 `vercel.json` setup  
- 🛠 GitHub action CI badge  
- 🌙 Dark-mode optimized markdown  

I can generate all that next.
