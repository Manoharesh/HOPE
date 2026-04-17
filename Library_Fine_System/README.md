# 📚 Library Fine System

A full-stack **Library Management System** with a Spring Boot REST API backend and a built-in vanilla HTML/CSS/JS frontend — fully self-contained, no separate frontend server needed.

---

## ✨ Features

- **📖 Book Management** — Add books by category, track total and available copies
- **👥 Member Management** — Register members, view details, track pending fines
- **🔄 Loan Management** — Borrow books, return them; due date auto-set to 14 days
- **💰 Fine Calculation** — $1.00 per day overdue; auto-applied on return
- **🖥️ Web Dashboard** — SPA frontend served directly by Spring Boot
- **🗄️ H2 In-Memory DB** — Zero-config setup, browser console included

---

## 🛠️ Technology Stack

| Layer        | Technology              |
|--------------|-------------------------|
| Language     | Java 17                 |
| Framework    | Spring Boot 3.2.4       |
| Database     | H2 In-Memory            |
| ORM          | Spring Data JPA         |
| Build Tool   | Maven 3.6+              |
| Frontend     | HTML5 / CSS3 / Vanilla JS |

---

## 📁 Project Structure

```
Library_Fine_System/
├── pom.xml
├── src/
│   └── main/
│       ├── java/com/library/
│       │   ├── LibraryFineSystemApplication.java
│       │   ├── config/
│       │   │   └── DataSeeder.java           # Seeds sample data on startup
│       │   ├── controller/
│       │   │   ├── BookController.java
│       │   │   ├── MemberController.java
│       │   │   └── LoanController.java
│       │   ├── exception/
│       │   │   ├── BookNotFoundException.java
│       │   │   ├── MemberNotFoundException.java
│       │   │   ├── BookNotAvailableException.java
│       │   │   ├── LoanNotFoundException.java
│       │   │   └── GlobalExceptionHandler.java
│       │   ├── model/
│       │   │   ├── Book.java
│       │   │   ├── Member.java
│       │   │   ├── Loan.java
│       │   │   └── LoanStatus.java           # Enum: ACTIVE, RETURNED, OVERDUE
│       │   ├── repository/
│       │   │   ├── BookRepository.java
│       │   │   ├── MemberRepository.java
│       │   │   └── LoanRepository.java
│       │   └── service/
│       │       ├── BookService.java
│       │       ├── MemberService.java
│       │       └── LoanService.java          # Fine calculation logic
│       └── resources/
│           ├── application.properties
│           └── static/
│               ├── index.html                # SPA frontend
│               ├── styles.css
│               └── app.js
├── plan.md
├── tasks.md
├── instructions.md
└── gemini.md
```

---

## ⚙️ Fine Calculation Rule

| Condition | Fine |
|-----------|------|
| Returned **on or before** due date | **$0.00** |
| Returned **after** due date | `(returnDate − dueDate) × $1.00/day` |

---

## 🚀 Getting Started

### Prerequisites
- **Java 17+**
- **Maven 3.6+** (must be on your system `PATH`)

### Run the Application

```bash
# 1. Clone the repository
git clone https://github.com/your-username/Library_Fine_System.git
cd Library_Fine_System

# 2. Build the project
mvn clean package -DskipTests

# 3. Start the server
mvn spring-boot:run
```

The app will start on **`http://localhost:8080`**

> On startup, sample data is seeded automatically: 5 books, 3 members, and 2 demo loans.

---

## 🌐 Access Points

| Resource       | URL                                          |
|----------------|----------------------------------------------|
| Frontend UI    | http://localhost:8080                        |
| REST API Base  | http://localhost:8080/api                    |
| H2 DB Console  | http://localhost:8080/h2-console             |

**H2 Console credentials:**
- JDBC URL: `jdbc:h2:mem:librarydb`
- Username: `sa` | Password: `password`

---

## 📡 REST API Reference

### Books — `/api/books`
| Method | Endpoint         | Description                           |
|--------|------------------|---------------------------------------|
| POST   | `/api/books`     | Add a new book                        |
| GET    | `/api/books`     | List all books (optional `?category=`) |
| GET    | `/api/books/{id}`| Get a book by ID                      |
| PUT    | `/api/books/{id}`| Update a book                         |
| DELETE | `/api/books/{id}`| Delete a book                         |

### Members — `/api/members`
| Method | Endpoint                    | Description               |
|--------|-----------------------------|---------------------------|
| POST   | `/api/members`              | Register a new member     |
| GET    | `/api/members`              | List all members          |
| GET    | `/api/members/{id}`         | Get member with fine info |
| POST   | `/api/members/{id}/pay-fine`| Clear pending fines       |

### Loans — `/api/loans`
| Method | Endpoint                        | Description                       |
|--------|---------------------------------|-----------------------------------|
| POST   | `/api/loans/borrow`             | Borrow a book `{bookId, memberId}`|
| POST   | `/api/loans/return`             | Return a book `{loanId}`          |
| GET    | `/api/loans`                    | List all active / overdue loans   |
| GET    | `/api/loans/all`                | Full loan history                 |
| GET    | `/api/loans/member/{memberId}`  | All loans for a member            |
| POST   | `/api/loans/update-overdue`     | Sync overdue statuses             |

---

## 📝 Sample Requests (curl)

```bash
# Add a book
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","author":"Robert C. Martin","category":"Programming","totalCopies":3}'

# Register a member
curl -X POST http://localhost:8080/api/members \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice Johnson","email":"alice@example.com","phoneNumber":"9876543210"}'

# Borrow a book
curl -X POST http://localhost:8080/api/loans/borrow \
  -H "Content-Type: application/json" \
  -d '{"bookId":1,"memberId":1}'

# Return a book
curl -X POST http://localhost:8080/api/loans/return \
  -H "Content-Type: application/json" \
  -d '{"loanId":1}'
```

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
