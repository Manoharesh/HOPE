# Library Fine System - Implementation Plan

## 1. Project Overview
The Library Fine System is a backend application designed to manage library operations. It tracks books, library members, book lending (loans), and calculates fines for overdue book returns.

## 2. Technology Stack
- **Language**: Java
- **Framework**: Spring Boot (Provides robust REST API capabilities, Dependency Injection, and easier configuration)
- **Database**: H2 (In-memory database for easy local setup and testing without external dependencies)
- **Data Access**: Spring Data JPA (Hibernate)
- **Build Tool**: Maven

## 3. Core Entities & Relationships

### 3.1 Book
Represents a book in the library.
- `id` (Long) - Primary Key
- `title` (String)
- `author` (String)
- `category` (String)
- `totalCopies` (Integer)
- `availableCopies` (Integer)

### 3.2 Member
Represents a library user.
- `id` (Long) - Primary Key
- `name` (String)
- `email` (String) - Unique
- `phoneNumber` (String)
- `totalPendingFines` (Double) - Accumulated fines

### 3.3 Loan (Transaction)
Tracks the borrowing of a book.
- `id` (Long) - Primary Key
- `book` (Many-to-One relationship to Book)
- `member` (Many-to-One relationship to Member)
- `issueDate` (LocalDate)
- `dueDate` (LocalDate)
- `returnDate` (LocalDate) - Nullable, set when returned
- `fineAmount` (Double) - Calculated if returned past due date
- `status` (Enum: ACTIVE, RETURNED, OVERDUE)

## 4. System Logic & Rules
- **Lending**: A member can borrow a book if `availableCopies > 0`. Upon borrowing, `availableCopies` decreases by 1.
- **Due Date**: By default, books are due 14 days from the `issueDate`.
- **Returning**: When a book is returned, `availableCopies` increases by 1.
- **Fine Calculation**: If `returnDate` is after `dueDate`, a fine is calculated (e.g., $1.00 per day overdue). This amount is added to the Loan record and the Member's `totalPendingFines`.

## 5. API Endpoints Structure (REST)

### Books API
- `POST /api/books` - Add a new book
- `GET /api/books` - List all books (with optional category filter)
- `GET /api/books/{id}` - Get book details

### Members API
- `POST /api/members` - Register a new member
- `GET /api/members/{id}` - Get member details (including fines)
- `POST /api/members/{id}/pay-fine` - Pay member's accumulated fines

### Loans API
- `POST /api/loans/borrow` - Borrow a book (requires `bookId`, `memberId`)
- `POST /api/loans/return` - Return a book (requires `loanId`)
- `GET /api/loans` - Get all active/overdue loans

## 6. Architecture Layers
1. **Controllers**: REST layer, handles HTTP requests.
2. **Services**: Business logic layer, handles borrowing rules, fine calculation.
3. **Repositories**: Data access layer using Spring Data JPA.
4. **Entities**: JPA mapped Java Objects.
