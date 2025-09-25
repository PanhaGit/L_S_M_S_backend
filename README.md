# School Library Management System Backend

Backend REST API for **School Library Management System (L_S_M_S)**, handling students, courses, and enrollments.

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/SQL%20Server-CC2927?style=for-the-badge&logo=microsoftsqlserver&logoColor=white" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" />
  <img src="https://img.shields.io/badge/Flyway-007ACC?style=for-the-badge&logo=flyway&logoColor=white" />
</p>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## 🚀 Features
- Manage **students**, **courses**, and **enrollments**
- RESTful API with JSON responses
- SQL Server database integration
- Flyway migrations for database schema
- Spring Data JPA repositories
- Layered architecture (**Controller → Service → Repository**)

---

## 🛠️ Tech Stack
- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **SQL Server**
- **Flyway**
- **Maven**

---

## ▶️ Getting Started

### Prerequisites
- JDK 21+
- Maven 3+
- Docker (for running SQL Server)

---

### 1️⃣ Run SQL Server with Docker

```bash
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=YourStrong@Passw0rd" \
   -p 1433:1433 --name sqlserver_lsms -d mcr.microsoft.com/mssql/server:2022-latest
