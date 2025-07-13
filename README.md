---
post_title: "Bernie Store - Full-Stack E-commerce Application"
author1: "Bernie Truong"
post_slug: "bernie-store-fullstack-ecommerce"
microsoft_alias: "bernie.truong"
featured_image: "https://berniesticker.onrender.com/featured-image.png"
categories: ["Full-Stack Development", "E-commerce", "React", "Spring Boot"]
tags:
  [
    "React",
    "Spring Boot",
    "E-commerce",
    "JWT",
    "Stripe",
    "MySQL",
    "REST API",
    "Redux",
  ]
ai_note: "This documentation was created with AI assistance based on comprehensive project analysis"
summary: "A complete full-stack e-commerce application featuring a React frontend with Redux state management and a Spring Boot backend with JWT authentication, Stripe payments, and comprehensive order management."
post_date: "2025-07-13"
---

## Bernie Store - Full-Stack E-commerce Platform

Bernie Store is a modern, full-stack e-commerce application specializing in
creative stickers and posters. The platform combines a responsive React frontend
with a robust Spring Boot backend to deliver a complete online shopping
experience with secure payments, user authentication, and administrative
management capabilities.

## Architecture Overview

### Frontend (berniestore-ui)

- **React 19** with modern hooks and concurrent features
- **Redux Toolkit** for state management
- **TailwindCSS** for responsive design
- **Stripe Elements** for secure payment processing
- **Vite** for fast development and building

### Backend (berniestore)

- **Spring Boot 3.5.3** with Java 24
- **Spring Security** with JWT authentication
- **JPA/Hibernate** for data persistence
- **MySQL** database with H2 for development
- **Stripe API** for payment processing

## Key Features

### Customer Experience

- **Product Catalog**: Browse and search creative stickers and posters
- **Shopping Cart**: Persistent cart with real-time updates
- **Secure Checkout**: Stripe-powered payment processing
- **User Accounts**: Registration, login, and profile management
- **Order Tracking**: Complete order history and status updates
- **Responsive Design**: Mobile-first approach with dark/light themes

### Administrative Features

- **Order Management**: Approve, confirm, or cancel customer orders
- **Message System**: Handle customer inquiries and support requests
- **User Management**: Role-based access control (USER/ADMIN)
- **Analytics**: Application monitoring with Spring Boot Actuator

### Technical Highlights

- **Security**: JWT-based authentication with CSRF protection
- **Performance**: Redis-style caching with Caffeine
- **API Documentation**: OpenAPI 3.0 with interactive Swagger UI
- **Monitoring**: Health checks and application metrics
- **Error Handling**: Comprehensive error boundaries and user feedback

## Technology Stack

### Frontend Technologies

| Technology    | Version | Purpose             |
| ------------- | ------- | ------------------- |
| React         | 19.1.0  | UI Framework        |
| Redux Toolkit | 2.8.2   | State Management    |
| React Router  | 7.6.3   | Client-side Routing |
| TailwindCSS   | 4.1.11  | Styling Framework   |
| Stripe React  | 3.7.0   | Payment Processing  |
| Axios         | 1.10.0  | HTTP Client         |
| Vite          | 7.0.0   | Build Tool          |

### Backend Technologies

| Technology      | Version | Purpose                        |
| --------------- | ------- | ------------------------------ |
| Spring Boot     | 3.5.3   | Application Framework          |
| Spring Security | 6.x     | Authentication & Authorization |
| Spring Data JPA | 3.x     | Data Persistence               |
| MySQL           | 8.0+    | Primary Database               |
| H2 Database     | 2.x     | Development Database           |
| JWT (JJWT)      | 0.12.6  | Token Authentication           |
| Stripe Java SDK | 29.0.0  | Payment Processing             |
| Caffeine        | 3.x     | Caching                        |

## Project Structure

```
fullstack-react-springboot/
├── beriniestore-ui/                    # React Frontend Application
│   ├── public/                         # Static assets and sticker images
│   │   └── stickers/                   # Product image gallery
│   ├── src/
│   │   ├── api/                        # API client configuration
│   │   ├── components/                 # React components
│   │   │   ├── admin/                  # Admin-specific components
│   │   │   └── footer/                 # Footer components
│   │   ├── hooks/                      # Custom React hooks
│   │   ├── store/                      # Redux store and slices
│   │   ├── App.jsx                     # Root component
│   │   └── main.jsx                    # Application entry point
│   ├── package.json                    # Dependencies and scripts
│   ├── vite.config.js                  # Vite configuration
│   └── netlify.toml                    # Deployment configuration
│
├── berniestore/                        # Spring Boot Backend Application
│   ├── src/main/java/com/bernie/berniestore/
│   │   ├── controller/                 # REST API controllers
│   │   ├── service/                    # Business logic services
│   │   ├── repository/                 # Data access layer
│   │   ├── entity/                     # JPA entities
│   │   ├── dto/                        # Data transfer objects
│   │   ├── security/                   # Security configuration
│   │   ├── config/                     # Application configuration
│   │   └── BerniestoreApplication.java # Main application class
│   ├── src/main/resources/
│   │   └── application.properties      # Application configuration
│   ├── pom.xml                         # Maven dependencies
│   └── Dockerfile                      # Container configuration
│
└── .github/                            # Documentation and instructions
    └── instructions/
        └── markdown.instructions.md    # Content creation standards
```

## Getting Started

### Prerequisites

- **Node.js** 18+ and npm/yarn
- **Java** 24+ and Maven 3.6+
- **MySQL** 8.0+ (or use H2 for development)
- **Git** for version control

### Quick Start

1. **Clone the Repository**

   ```bash
   git clone <repository-url>
   cd fullstack-react-springboot
   ```

2. **Backend Setup**

   ```bash
   cd berniestore

   # Configure database (see Backend Configuration below)
   # Set environment variables in application.properties

   # Build and run
   mvn clean install
   mvn spring-boot:run
   ```

   Backend will start on `http://localhost:8080`

3. **Frontend Setup**

   ```bash
   cd beriniestore-ui

   # Install dependencies
   npm install

   # Set environment variables
   echo "VITE_API_BASE_URL=http://localhost:8080/api/v1" > .env

   # Start development server
   npm run dev
   ```

   Frontend will start on `http://localhost:5173`

### Backend Configuration

Create environment variables or update `application.properties`:

```properties
# Database Configuration (MySQL)
DATASOURCE_URL=jdbc:mysql://localhost:3307/berniestore
DATASOURCE_USERNAME=your_username
DATASOURCE_PASSWORD=your_password

# Frontend URL for CORS
FRONTEND_URL=http://localhost:5173

# JWT Secret (generate a secure key for production)
JWT_SECRET=your_jwt_secret_key

# Stripe Configuration
STRIPE_SECRET_KEY=your_stripe_secret_key
STRIPE_PUBLISHABLE_KEY=your_stripe_publishable_key
```

## API Documentation

### Authentication Endpoints

```
POST /api/v1/auth/login      # User login
POST /api/v1/auth/register   # User registration
```

### Product Endpoints

```
GET  /api/v1/products        # Get all products (public)
```

### Order Management

```
POST /api/v1/orders          # Create order (authenticated)
GET  /api/v1/orders          # Get user orders (authenticated)
```

### Admin Endpoints

```
GET    /api/v1/admin/orders                 # Get pending orders
PATCH  /api/v1/admin/orders/{id}/confirm    # Confirm order
PATCH  /api/v1/admin/orders/{id}/cancel     # Cancel order
GET    /api/v1/admin/messages               # Get open messages
PATCH  /api/v1/admin/messages/{id}/close    # Close message
```

### Interactive Documentation

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI Spec**: `http://localhost:8080/v3/api-docs`

## Development Workflow

### Frontend Development

```bash
cd beriniestore-ui

# Development server with hot reload
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview

# Linting
npm run lint
```

### Backend Development

```bash
cd berniestore

# Run with development profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Run tests
mvn test

# Package for production
mvn clean package

# Run with Docker
docker build -t berniestore .
docker run -p 8080:8080 berniestore
```

## Deployment

### Frontend Deployment (Netlify)

The frontend is configured for Netlify deployment with `netlify.toml`:

```bash
# Build command
npm run build

# Publish directory
dist/
```

### Backend Deployment

The Spring Boot application can be deployed as:

- **JAR file**: `java -jar berniestore-0.0.1-SNAPSHOT.jar`
- **Docker container**: Using the included Dockerfile
- **Cloud platforms**: AWS, Google Cloud, Azure, etc.

## Security Considerations

### Frontend Security

- XSS prevention with React's built-in escaping
- CSRF protection for state-changing operations
- Secure token storage and automatic expiration
- Input validation and sanitization

### Backend Security

- JWT token-based authentication
- Role-based access control (RBAC)
- Password encryption with BCrypt
- CORS configuration for cross-origin requests
- Compromised password checking
- Request validation with JSR-380 annotations

## Testing

### Frontend Testing

```bash
# Run component tests
npm test

# Run e2e tests
npm run test:e2e
```

### Backend Testing

```bash
# Run unit tests
mvn test

# Run integration tests
mvn verify

# Generate test coverage report
mvn jacoco:report
```

## Performance Optimization

### Frontend Performance

- Code splitting with React.lazy()
- Component memoization with React.memo
- Bundle optimization with Vite
- Image optimization and lazy loading
- Redux state normalization

### Backend Performance

- Caffeine caching for frequently accessed data
- JPA query optimization
- Connection pooling
- Response compression
- Database indexing

## Monitoring and Observability

### Application Health

- **Health Checks**: `/berniestore/actuator/health`
- **Metrics**: `/berniestore/actuator/metrics`
- **Application Info**: `/berniestore/actuator/info`

### Logging

- Structured logging with SLF4J
- Configurable log levels
- File and console output options

## Contributing

### Development Standards

1. Follow the coding standards in `.github/instructions/`
2. Write comprehensive tests for new features
3. Use conventional commit messages
4. Create feature branches for new development
5. Submit pull requests with detailed descriptions

### Code Quality

- Frontend: ESLint configuration with React rules
- Backend: Maven checkstyle and PMD plugins
- Both: Comprehensive test coverage requirements

## Troubleshooting

### Common Issues

**Frontend Issues:**

- **CORS errors**: Verify backend CORS configuration
- **API connection**: Check environment variables and backend availability
- **Build failures**: Clear node_modules and reinstall dependencies

**Backend Issues:**

- **Database connection**: Verify MySQL is running and credentials are correct
- **JWT errors**: Check JWT secret configuration
- **Stripe issues**: Verify Stripe API keys (use test card: 4242 4242 4242 4242)

### Development Tips

- Use browser dev tools for frontend debugging
- Check Spring Boot logs for backend issues
- Use Swagger UI for API testing
- Monitor network requests for API communication

## License

This project demonstrates modern full-stack development practices and is
intended for educational and portfolio purposes.

## Contact

For questions or support, please use the contact form within the application
or reach out through the administrative interface.

---

**Note**: This application uses test payment processing. For production use,
ensure proper Stripe configuration and security measures are implemented.
