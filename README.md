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
```bash
git clone https://github.com/tapanKr277/QuizManagementSystem.git
cd QuizManagementSystem
