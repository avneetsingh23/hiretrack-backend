# 🚀 HireTrack - Job Portal Backend

HireTrack is a Full Stack Job Portal application built using **Spring Boot**. It provides secure authentication, job management, recruiter workflows, application tracking, admin controls, and resume upload functionality.

---

## 📌 Features

### 👤 User
- User Registration & Login (JWT Authentication)
- View Profile
- Upload Resume (PDF)
- Search Jobs
- Apply for Jobs
- View Applied Jobs
- Withdraw Application
- Forgot Password (OTP Verification)
- Reset Password

### 👨‍💼 Recruiter
- Recruiter Registration & Login
- Create Job
- Update Job
- Delete Job
- View Own Jobs
- View Applicants
- Shortlist / Reject Applications
- Recruiter Dashboard

### 👑 Admin
- View All Users
- View All Jobs
- View All Applications
- Safe Delete Users
- Safe Delete Jobs
- Dashboard Statistics

---

# 🛠 Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Swagger (OpenAPI)
- Postman

---

# 📂 Project Structure

```
src
 ├── controller
 ├── service
 ├── service.impl
 ├── repository
 ├── entity
 ├── dto
 ├── enums
 ├── security
 ├── exception
 ├── util
 └── config
```

---

# 🔐 Authentication

JWT Based Authentication

Roles Supported

- USER
- RECRUITER
- ADMIN

---

# 📊 Modules

## Authentication
- Register
- Login
- Forgot Password
- Verify OTP
- Reset Password

## User Module
- Upload Resume
- Apply Job
- My Applications
- Withdraw Application

## Job Module
- Create Job
- Update Job
- Delete Job
- Search Jobs

## Recruiter Module
- My Jobs
- Applicants
- Update Status

## Admin Module
- Users Management
- Jobs Management
- Applications Management
- Dashboard

---

# 📁 REST APIs

Authentication APIs

```
POST /api/auth/register
POST /api/auth/login
POST /api/auth/forgot-password
POST /api/auth/verify-otp
POST /api/auth/reset-password
```

Job APIs

```
GET    /api/jobs
POST   /api/jobs
PUT    /api/jobs/{id}
DELETE /api/jobs/{id}
```

Application APIs

```
POST   /api/applications/{jobId}
GET    /api/applications/my
PUT    /api/applications/{id}/status
DELETE /api/applications/{id}
```

Admin APIs

```
GET    /api/admin/users
GET    /api/admin/jobs
GET    /api/admin/applications
DELETE /api/admin/users/{id}
DELETE /api/admin/jobs/{id}
```

---

# ⚙️ Installation

Clone Repository

```bash
git clone https://github.com/avneetsingh23/HireTrack.git
```

Go to Project

```bash
cd HireTrack
```

Configure MySQL

```
application.properties
```

Run

```bash
mvn spring-boot:run
```

---

# 📷 API Testing

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

# ✅ Validation

- Bean Validation
- Global Exception Handling
- JWT Security
- Role Based Authorization

---

# 📈 Future Improvements

- React Frontend
- Email Notifications
- Interview Scheduling
- Company Profiles
- Saved Jobs
- Pagination
- Docker Deployment
- AWS Deployment

---

# 👨‍💻 Developer

**Avneet Singh**

Java Full Stack Developer

- Java
- Spring Boot
- Spring Security
- MySQL
- REST APIs
- React (In Progress)

---

⭐ If you like this project, give it a Star.
