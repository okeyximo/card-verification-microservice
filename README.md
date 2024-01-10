# Card Verification Microservice

This Spring Boot microservice provides card verification functionality using a third-party API (https://binlist.net) to fetch card details. It also includes a signup and login feature to protect the endpoints.

## Features

1. **Card Verification Endpoint**
    - **Endpoint:** `GET /card-scheme/verify/{cardNumber}`
    - **Description:** Verifies the card details using the provided card number.
    - **Example Response:**
      ```json
      {
        "success": true,
        "payload": {
          "scheme": "visa",
          "type": "debit",
          "bank": "UBS"
        }
      }
      ```

2. **Statistics Endpoint**
    - **Endpoint:** `GET /card-scheme/stats?start={start}&limit={limit}`
    - **Description:** Retrieves card verification statistics based on start and limit parameters.
    - **Example Response:**
      ```json
      {
        "success": true,
        "start": 1,
        "limit": 3,
        "size": 133,
        "payload": {
          "111111": "1",
          "234233": "3",
          "545451": "5"
        }
      }
      ```

3. **Signup and Login**
    - **Endpoint:** `POST /signup` and `POST /login`
    - **Description:** Allows users to sign up and login to access protected endpoints.

## Implementation Details

### 1. OOP Skills
- The project is structured using object-oriented principles, with classes for entities, services, controllers, and DTOs (Data Transfer Objects).

### 2. Database Modelization (JPA)
- JPA (Java Persistence API) is used for database modelization.
- Entities like `User` and `CardVerification` are defined to represent database tables.

### 3. Code Standards/Clean Code
- The code follows Java coding standards and best practices.
- Clear and meaningful variable and method names are used for improved readability.

### 4. Software Patterns
- The project incorporates design patterns where applicable, such as the Singleton pattern for the WebClient bean.

### 5. Performance and API Requests
- Caching mechanisms are implemented to minimize the number of requests to the third-party API.
- Asynchronous processing is used where suitable to improve overall performance.

### 6. Unit Tests
- JUnit and Mockito are used for unit testing.
- Test coverage includes service methods, controllers, and repository classes.

## How to Run

1. Clone the repository: `git clone https://github.com/Chesca22/card-verification-microservice.git`
2. Navigate to the project directory: `cd card-verification-microservice`
3. Configure the application.properties file with your database details and other configurations.
4. Build the project: `mvn clean install`
5. Run the application: `java -jar target/card-verification-microservice.jar`

## API Documentation

- Swagger/OpenAPI documentation is available at `http://localhost:8080/swagger-ui.html` after starting the application.

## Dependencies

- Java 8+
- Spring Boot
- Spring Data JPA
- H2 Database (for simplicity; can be replaced with other databases in production)
- WebClient (Spring WebFlux)
