# Java Spring Boot 3 JWT Authentication Maven Boilerplate

This repository provides a Java Spring Boot 3.3.5 Maven boilerplate, featuring a JWT authentication, role-based access control, PostgreSQL integration and Docker setup.
The project also includes basic AI image classification using the Deep Java Library (DJL), making it ideal for rapid development of secure, AI-enabled web applications.

## Quick Start
- Configure Lombok on your IDE (IntelliJ)
- docker-compose up
- mvn clean install
- mvn clean package / Build the project from IDE
- mvn spring-boot:run

## Features
- JWT Authentication: Secure role-based access with JSON Web Tokens
- PostgreSQL Integration: Efficient database management using PostgreSQL
- Docker Support: Containerized setup for easy deployment and scaling
- Maven and Lombok: Simplify project dependencies and reduce boilerplate code
- Sample Endpoints: User auth and image classification test endpoints

## Tech Stack
- Java 21
- Spring Boot 3.3.5
- Maven for dependency management
- Lombok for reducing boilerplate Java code
- PostgreSQL as the database
- Docker for containerization
- JSON Web Token (JWT) for secure authentication
- DJL (Deep Java Library) for AI integration

## Configuration
Update application.yml with your database configuration and JWT secret key.
The key should be HS512.

config/SecurityConfig contains a whitelist for endpoints.

Test the setup by calling the endpoints.

```
spring:
    datasource:
        url: jdbc:postgresql://localhost:5432/your_db_name
        username: your_db_user
        password: your_db_password
```

```
jwt:
    secret: your_jwt_secret_key
```

## API Endpoints

### POST /auth/signup
```
{
    "firstName": "Satoshi",
    "lastName": "Nakamoto",
    "email": "satoshi.nakamoto@gmail.com",
    "password": "mysecretpw"
}
```

### POST /auth/signin
```
Include the token you received as Authorization Bearer token
```

### GET /classify-image
```
Add url param imageUrl=https://urltoimg.jpg
```

## JWT Authentication with Spring Security Overview
This project uses Spring Security to configure JWT-based authentication, which ensures secure access to protected resources.

### SecurityFilterChain
This configuration customizes Spring Security’s default behavior:
- Disable Basic Authentication: Disables default form-based login.
- Disable CSRF: Since this is a stateless REST API, CSRF is disabled.
- Whitelist Endpoints: Configures public endpoints that do not require authentication, specified in WHITE_LIST_URL (e.g., /auth/** and /user/**).
- Session Management: Sets the session policy to STATELESS to avoid creating sessions for each request, suitable for JWT.
- Add JWT Filter: Adds a custom JwtAuthFilter to the filter chain before the UsernamePasswordAuthenticationFilter. This filter intercepts requests, validates the JWT token, and sets the user authentication context if valid.

### JwtAuthFilter
This filter intercepts each request to:
- Extract the JWT from the request headers.
- Validate the token and, if valid, retrieve user details.
- Set the Authentication object in the security context, enabling access to authenticated endpoints. 

### AuthenticationProvider
Configures authentication using DaoAuthenticationProvider, which integrates with UserDetailsService for loading user information and uses BCrypt for password encoding.
AuthenticationEntryPoint: When configured, this entry point handles unauthorized access attempts by returning an appropriate HTTP error response (e.g., 401 Unauthorized).

## Development

When starting the application `docker compose up` is called and the app will connect to the contained services.
[Docker](https://www.docker.com/get-started/) must be available on the current system.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options". Create your own
`application-local.yml` file to override settings for development.

Lombok must be supported by your IDE. For IntelliJ install the Lombok plugin and enable annotation processing -
[learn more](https://bootify.io/next-steps/spring-boot-with-lombok.html).

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
mvn clean package
```

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/java-spring-boot-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=io.bootify/java-spring-boot
```

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Keywords and SEO Tags
- Java Spring Boot 3.3.5 boilerplate
- JWT authentication with Spring Boot
- Java Spring Boot 3 JWT boilerplate
- Spring Boot and Docker setup
- Java AI image classification
- Spring Boot PostgreSQL integration
- Java AI and JWT boilerplate project
- Secure Java backend with JWT
- Java 21 Spring Boot 3 JWT boilerplate

## Support the Dev:
BTC: bc1qpftdtjggrq8dpa6x6x7dnqvgv7ttc2x2m8rgvy
ETH / POL / BNB: 0xdCC9f5281B8bb40B11A792C280aA2cdd434C34AF
