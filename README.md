# ⚙️ Artistic Ankit - Backend API

Welcome to the **Artistic Ankit Backend API** repository! This is a robust Spring Boot application that serves as the core engine for the Artistic Ankit Studio Gallery platform. It handles user authentication, database management, painting uploads, and commission requests.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.1-6DB33F?logo=spring)
![Java 17](https://img.shields.io/badge/Java-17-ED8B00?logo=java)
![MySQL](https://img.shields.io/badge/Database-Aiven_MySQL-4479A1?logo=mysql)
![Render Deployment](https://img.shields.io/badge/Deployed_on-Render-black?logo=render)

## ✨ Key Features
- **RESTful Architecture**: Clean controllers and service layers structured logically.
- **Data Persistence**: Uses Spring Data JPA with Hibernate connecting to a live Aiven MySQL cloud database.
- **Authentication System**: Secure user registration and login endpoints with BCrypt password hashing.
- **File Management**: Endpoints for uploading and serving images seamlessly.
- **CORS Configured**: Fully integrated and allowed origins configured for the Vercel frontend.

## 🗄️ Database Structure (Entities)
1. **User (`users`)**: Stores client accounts, hashed passwords, and contact info.
2. **Painting (`paintings`)**: Stores art metadata (title, price, image URL, YouTube Shorts link, category).
3. **CommissionRequest (`commission_requests`)**: Tracks user-submitted requests for custom paintings.

## 🚀 Running Locally

1. **Clone the repository:**
   ```bash
   git clone https://github.com/sumit-sagar0/ankit-backend.git
   cd ArtisticAnkit_Backend
   ```

2. **Configure Database:**
   Update `src/main/resources/application.properties` with your local MySQL credentials, or export the environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`) for the Aiven cloud instance.

3. **Run the Server:**
   ```bash
   ./mvnw spring-boot:run
   ```
   The backend will start at `http://localhost:8080`.

## 🌐 Live Server
The backend is continuously deployed on **Render**. Any push to the `main` branch automatically triggers a fresh build.
Base URL: `https://ankit-backend-yxoi.onrender.com`

---
*Developed for Artistic Ankit's Digital Portfolio & Studio.*
