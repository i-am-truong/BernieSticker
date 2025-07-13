---
post_title: "Bernie Store - E-commerce Spring Boot Application"
author1: "Bernie Truong"
post_slug: "berniestore-springboot-ecommerce"
microsoft_alias: "bernie.truong"
featured_image: "https://spring.io/img/spring-boot.png"
categories: ["Spring Boot", "E-commerce", "REST API"]
tags:
  ["Java", "Spring Boot", "JPA", "Security", "MySQL", "JWT", "Stripe", "Maven"]
ai_note: "This README was generated with AI assistance based on project structure analysis"
summary: "A comprehensive Spring Boot backend application for Bernie Store e-commerce platform with JWT authentication, order management, payment processing, and admin functionality"
post_date: "2025-07-13"
---

## Bernie Store Backend Application

A robust Spring Boot backend service for Bernie Store e-commerce platform, providing comprehensive REST APIs for user authentication, product management, order processing, and payment integration with Stripe.

## Features

### Core Functionality

- **User Authentication & Authorization**: JWT-based authentication with role-based access control (USER/ADMIN)
- **Product Management**: REST APIs for product catalog browsing
- **Order Management**: Complete order lifecycle management with status tracking
- **Payment Processing**: Stripe integration for secure payment handling
- **Contact System**: Customer support messaging system
- **Admin Dashboard**: Administrative endpoints for order and message management

### Technical Features

- **Security**: Spring Security with JWT tokens and CSRF protection
- **Caching**: Caffeine cache implementation for performance optimization
- **Database**: JPA/Hibernate with MySQL and H2 support
- **Validation**: JSR-380 validation annotations for request validation
- **API Documentation**: OpenAPI 3.0 with Swagger UI
- **Monitoring**: Spring Boot Actuator for application health monitoring
- **Scoped Beans**: Demonstration of request, session, and application scopes

## Technology Stack

### Core Framework

- **Spring Boot 3.5.3**: Main application framework
- **Java 24**: Programming language version
- **Maven**: Build and dependency management

### Database & Persistence

- **Spring Data JPA**: Data access layer
- **MySQL Connector**: Production database
- **H2 Database**: Development and testing
- **JPA Auditing**: Automatic entity auditing

### Security

- **Spring Security**: Authentication and authorization
- **JWT (JJWT 0.12.6)**: Token-based authentication
- **Password Validation**: Compromised password checking

### Additional Libraries

- **Lombok**: Boilerplate code reduction
- **Stripe SDK 29.0.0**: Payment processing
- **Caffeine**: High-performance caching
- **SpringDoc OpenAPI**: API documentation
- **Bean Validation**: Request validation

## Project Structure

```
src/main/java/com/bernie/berniestore/
├── BerniestoreApplication.java          # Main application class
├── config/                              # Configuration classes
│   ├── AuditorAwareImplement.class      # JPA auditing configuration
│   ├── CaffeineCacheConfiguration.class # Cache configuration
│   └── StripeConfiguration.class        # Stripe payment configuration
├── constants/                           # Application constants
├── controller/                          # REST API controllers
│   ├── AdminController.java             # Admin management endpoints
│   ├── AuthController.java              # Authentication endpoints
│   ├── ContactController.java           # Contact/messaging endpoints
│   ├── OrderController.java             # Order management endpoints
│   ├── PaymentController.java           # Payment processing endpoints
│   ├── ProductController.java           # Product catalog endpoints
│   └── ProfileController.java           # User profile endpoints
├── dto/                                 # Data Transfer Objects
├── entity/                              # JPA entities
├── exception/                           # Custom exceptions
├── filter/                              # Security filters
├── repository/                          # Data access repositories
├── security/                            # Security configuration
├── service/                             # Business logic services
├── scopes/                              # Bean scope demonstrations
└── util/                                # Utility classes
```

## API Endpoints

### Authentication (`/api/v1/auth`)

- `POST /login` - User authentication with JWT token generation
- `POST /register` - New user registration

### Products (`/api/v1/products`)

- `GET /` - Retrieve all products (public endpoint)

### Orders (`/api/v1/orders`)

- `POST /` - Create new order (authenticated users)
- `GET /` - Get customer's orders (authenticated users)

### Admin (`/api/v1/admin`)

- `GET /orders` - Get all pending orders
- `PATCH /orders/{orderId}/confirm` - Confirm order
- `PATCH /orders/{orderId}/cancel` - Cancel order
- `GET /messages` - Get all open messages
- `PATCH /messages/{contactId}/close` - Close message

### Contacts (`/api/v1/contacts`)

- Contact form submission and management

### Payments

- Stripe payment processing integration

### Profile

- User profile management

## Configuration

### Environment Variables

The application requires the following environment variables:

```properties
# Database Configuration
DATASOURCE_URL=jdbc:mysql://localhost:3307/berniestore (for avoiding port 3306 at MySQL)
DATASOURCE_USERNAME=your_db_username
DATASOURCE_PASSWORD=your_db_password

# Frontend Configuration
FRONTEND_URL=http://localhost:5173

# Logging Configuration
LOG_LEVEL=INFO
LOG_FILE_NAME=/path/to/logs/app.log

# JPA Configuration
JPA_SHOW_URL=true
HIBERNATE_FORMAT_SQL=true
```

### Database Setup

1. Create a MySQL database named `berniestore`
2. Update the database connection properties in `application.properties`
3. The application will automatically create tables using JPA

## Getting Started

### Prerequisites

- Java 24 or higher
- Maven 3.6+
- MySQL 8.0+ (for production) or use H2 for development

### Installation

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd berniestore
   ```

2. **Configure environment variables**
   Set up the required environment variables as listed above

3. **Build the application**

   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on port 8080 by default.

### Development Mode

For development, the application can use H2 in-memory database. The configuration will automatically switch based on the active profile.

## API Documentation

Once the application is running, you can access:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI Specification**: `http://localhost:8080/v3/api-docs`

## Monitoring

### Actuator Endpoints

Spring Boot Actuator provides monitoring endpoints:

- **Health Check**: `/berniestore/actuator/health`
- **Application Info**: `/berniestore/actuator/info`
- **Metrics**: `/berniestore/actuator/metrics`

Access to actuator endpoints requires ADMIN role.

## Security

### Authentication Flow

1. Users register or login through `/api/v1/auth` endpoints
2. Successful authentication returns a JWT token
3. Subsequent requests include the JWT token in Authorization header
4. Role-based access control enforces endpoint permissions

### Public Endpoints

- Product catalog viewing
- Contact form submission
- Authentication endpoints
- Error handling

### Protected Endpoints

- Order management (USER/ADMIN)
- Profile management (USER/ADMIN)
- Admin operations (ADMIN only)

## Testing

Run the test suite:

```bash
mvn test
```

The application includes comprehensive unit and integration tests.

## Build

### Production Build

```bash
mvn clean package
```

### Docker Support

A Dockerfile is included for containerized deployment.

## Contributing

1. Follow the Spring Boot development guidelines in `.github/instructions/springboot.instructions.md`
2. Use constructor injection for dependencies
3. Implement proper error handling and validation
4. Write comprehensive tests
5. Follow the established package structure

## License

This project is part of a fullstack e-commerce application demonstrating modern Spring Boot development practices.
