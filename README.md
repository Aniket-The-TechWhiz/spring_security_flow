# 🔐 Spring Security with JWT Authentication

This project demonstrates how to secure backend APIs using **Spring Security** and **JWT (JSON Web Token)** for authentication and authorization.

---

## 🚀 Features

- ✅ User Authentication using JWT
- ✅ Secure REST APIs with Spring Security
- ✅ Stateless Session Management
- ✅ Role-Based Authorization
- ✅ Custom Authentication Filter
- ✅ Token Validation & Request Filtering

---

## 🧠 What I Learned

- How authentication and authorization work in real-world applications
- Implementing **JWT-based security** instead of session-based authentication
- Configuring **Spring Security filters and security chains**
- Managing **user roles and authorities**
- Building secure and scalable backend APIs

---

## ⚙️ Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Maven 

---

## 🔄 Authentication Flow

1. User sends login request with credentials
2. Server validates credentials
3. JWT token is generated
4. Token is sent back to the client
5. Client includes JWT in Authorization Header
6. Server validates token for every request

---

## 🔑 API Security

- Public endpoints → Accessible without authentication
- Protected endpoints → Require valid JWT token
- Role-based access → Restricted based on user roles

---

## 📂 Project Structure (As Implemented)

This repository is built to demonstrate **Spring Security + JWT-based API security** using a simple and focused project structure.

```
src/
├── main/
│   ├── java/com/project/security/
│   │   ├── SecurityApplication.java       # Spring Boot application entry point
│   │   ├── SecurityConfig.java            # Security filter chain and endpoint access rules
│   │   ├── AuthTokenFilter.java           # Reads JWT from request and sets authentication context
│   │   ├── JwtUtils.java                  # Generates and validates JWT tokens
│   │   ├── LoginRequest.java              # Request model for login credentials
│   │   └── hello.java                     # Sample secured/public endpoint controller
│   └── resources/
│       └── application.properties         # Application and security-related configuration
└── test/
	└── java/com/project/security/
		└── SecurityApplicationTests.java  # Basic Spring Boot context test
```

---

## 🧪 How to Test

1. Run the application
2. Use Postman or any API client
3. Call login API to get JWT token
4. Add token in headers:

```
Authorization: Bearer <your_token>
```

5. Access protected endpoints

---

## 🔮 Future Improvements

- OAuth 2.0 Integration
- Refresh Tokens
- API Rate Limiting
- Microservices Security
- Role-Based Access Control (RBAC) Enhancement

---

## 🤝 Contributing

Feel free to fork this repo and contribute!

---

## 📬 Connect with Me

If you found this helpful or have suggestions, let's connect on LinkedIn!

---

⭐ If you like this project, don't forget to give it a star!
