# Task Breakdown for Library Fine System

## Phase 1: Project Initialization
- [ ] Create a new Spring Boot project (Java 17+, Maven, Web, H2, Spring Data JPA).
- [ ] Set up the directory structure (`controller`, `service`, `repository`, `model`, `exception`).
- [ ] Configure `application.properties` for H2 database and JPA settings.

## Phase 2: Entity & Repository Implementation
- [ ] Create `Book` entity and `BookRepository`.
- [ ] Create `Member` entity and `MemberRepository`.
- [ ] Create `Loan` entity and `LoanRepository`.
- [ ] Validate entity relationships (One-To-Many / Many-To-One).

## Phase 3: Service Layer Implementation
- [ ] Create `BookService` (add, update, get books).
- [ ] Create `MemberService` (register, get members, manage fines).
- [ ] Create `LoanService` (borrow logic, return logic, fine calculation).
  - *Logic*: Decrease `availableCopies` on borrow.
  - *Logic*: Check due date vs return date on return and calculate fine.

## Phase 4: Controller Layer Implementation (REST APIs)
- [ ] Create `BookController` with endpoints to manage books.
- [ ] Create `MemberController` with endpoints to register members and view fine status.
- [ ] Create `LoanController` with endpoints to borrow and return books.

## Phase 5: Exception Handling & Validation
- [ ] Add `@RestControllerAdvice` for global exception handling.
- [ ] Handle cases like `BookNotFoundException`, `MemberNotFoundException`, `BookNotAvailableException`.
- [ ] Add Request body validation (e.g., `@Valid` for emails, required fields).

## Phase 6: Testing
- [ ] Write logic to insert initial dummy data.
- [ ] Test API endpoints via Postman or Curl.
- [ ] (Optional) Add unit tests for `FineCalculationService`.
