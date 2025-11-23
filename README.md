# TodoApp - SpringBoot & Angular

A full-stack Todo application built with **Spring Boot**, **PostgreSQL**, **Docker**, and **Angular**.  
The app includes authentication, user-specific todos, CRUD operations, and auto-sync between frontend and backend.

## Getting Started

To get a local copy up and running follow these steps.

### Installation

1. Clone the repository:

```bash
git clone <repo-link>
cd <repo-folder>
```
2. Start the application:

```
npm run start
```

---

### Features
### Frontend (Angular)
-  Login form
-  Logged-in user dashboard
-  Create Todo
-  Edit Todo
-  Delete Todo
-  Mark Todo as completed

### Backend (Spring Boot)
-  PostgreSQL + Flyway-style initialization
-  Full CRUD for Todos
-  Swagger API Documentation
-  Fully containerized with Docker Compose


---

### API Documentation
The API is documented with Swagger. Once the application is running, you can access Swagger UI at:
```
http://localhost:8080/swagger-ui/index.html
```
---

### Test Account

You can log in using the following test user:  
Email: ``` dimitar@gmail.com ```  
Password: ``` dimitar ```

User already has a few generated todos

---
## Technologies Used

This TodoApp is built with the following technologies:

- Backend: Spring Boot, PostgreSQL
- Frontend: Angular, TypeScript, Angular Material
- Database: PostgreSQL
- API Documentation: Swagger
- Authentication: BCrypt password hashing
- Docker: Docker Compose for containerized setup
- Version Control: Git & GitHub

---  