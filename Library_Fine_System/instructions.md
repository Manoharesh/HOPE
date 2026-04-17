# Library Fine System - Instructions

## Setup Instructions

### Prerequisites
- JDK 17 or higher
- Maven 3.6+
- Basic understanding of REST APIS

### Running the Application
1. **Build the project**
   Navigate to the root directory where the `pom.xml` is located and run:
   ```bash
   mvn clean install
   ```

2. **Run the Spring Boot application**
   ```bash
   mvn spring-boot:run
   ```

3. **Accessing the Application**
   - The REST API will be available at: `http://localhost:8080/api`
   - The H2 Database Console will be available at: `http://localhost:8080/h2-console`
     - **JDBC URL**: `jdbc:h2:mem:librarydb`
     - **Username**: `sa`
     - **Password**: `password`

### Interacting with the API
You can use tools like `curl`, Postman, or IntelliJ HTTP Client to hit the available endpoints:
- Create a Book: `POST /api/books`
- Create a Member: `POST /api/members`
- Borrow a Book: `POST /api/loans/borrow` (pass bookId and memberId)
- Return a Book: `POST /api/loans/return` (pass loanId or bookId/memberId pair)
