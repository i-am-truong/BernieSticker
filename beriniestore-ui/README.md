---
post_title: "Bernie Store UI - React E-commerce Frontend"
author1: "Development Team"
post_slug: "bernie-store-ui-react-frontend"
microsoft_alias: "bernie-dev"
featured_image: "https://berniesticker.onrender.com/featured-image.png"
categories: ["Web Development", "E-commerce", "React"]
tags: ["React", "Vite", "Redux", "Stripe", "TailwindCSS", "E-commerce"]
ai_note: "This documentation was created with AI assistance"
summary: "A modern React-based e-commerce frontend for Bernie Store, featuring 
sticker and poster sales with integrated payment processing, user authentication, 
and responsive design."
post_date: "2025-07-13"
---

## Project Overview

Bernie Store UI is a modern, responsive e-commerce frontend application built 
with React 19 and Vite. It serves as the client-side interface for Bernie Store, 
an online marketplace specializing in creative stickers and posters. The 
application features a complete shopping experience with product browsing, 
cart management, secure checkout, and user account functionality.

## Features

### Core E-commerce Functionality
- **Product Catalog**: Browse and search through a curated collection of 
  stickers and posters
- **Product Details**: View detailed product information with zoom functionality
- **Shopping Cart**: Add, remove, and modify items with persistent cart storage
- **Secure Checkout**: Integrated Stripe payment processing with card validation
- **Order Management**: Track order history and status updates

### User Experience
- **Authentication System**: User registration, login, and profile management
- **Responsive Design**: Mobile-first approach with dark/light theme support
- **Search & Filter**: Advanced product search and sorting capabilities
- **Admin Panel**: Administrative interface for order and message management

### Technical Features
- **State Management**: Redux Toolkit for global state management
- **API Integration**: Axios-based API client with CSRF protection
- **Routing**: React Router with protected routes and lazy loading
- **Form Handling**: Controlled components with validation
- **Error Handling**: Comprehensive error boundaries and user feedback

## Technology Stack

### Frontend Framework
- **React 19**: Latest React with modern hooks and concurrent features
- **Vite**: Fast build tool and development server
- **React Router DOM**: Client-side routing with nested routes

### State Management & Data Fetching
- **Redux Toolkit**: Simplified Redux for state management
- **React Redux**: React bindings for Redux
- **Axios**: HTTP client for API communication

### UI & Styling
- **TailwindCSS**: Utility-first CSS framework
- **FontAwesome**: Icon library for consistent iconography
- **React Toastify**: Toast notifications for user feedback

### Payment Processing
- **Stripe**: Secure payment processing integration
- **Stripe React Elements**: Pre-built UI components for payment forms

### Development Tools
- **ESLint**: Code linting with React-specific rules
- **Vite Plugin React**: React support for Vite

## Project Structure

```
src/
├── api/                    # API client configuration
│   └── apiClient.js       # Axios instance with interceptors
├── assets/                # Static assets
│   └── util/             # Utility images (empty cart, error, success)
├── components/            # React components
│   ├── admin/            # Admin-specific components
│   │   ├── AdminOrders.jsx
│   │   └── Messages.jsx
│   ├── footer/           # Footer components
│   │   └── Footer.jsx
│   ├── About.jsx         # About page component
│   ├── Cart.jsx          # Shopping cart page
│   ├── CartTable.jsx     # Cart items table
│   ├── CheckoutForm.jsx  # Stripe payment form
│   ├── Contact.jsx       # Contact page
│   ├── Dropdown.jsx      # Reusable dropdown component
│   ├── ErrorPage.jsx     # Error boundary component
│   ├── Header.jsx        # Navigation header
│   ├── Home.jsx          # Homepage with product listings
│   ├── Login.jsx         # User authentication
│   ├── Orders.jsx        # Order history page
│   ├── OrderSuccess.jsx  # Post-purchase confirmation
│   ├── PageHeading.jsx   # Reusable page headings
│   ├── PageTitle.jsx     # Reusable page titles
│   ├── Price.jsx         # Price display component
│   ├── ProductCard.jsx   # Product grid item
│   ├── ProductDetail.jsx # Product detail page
│   ├── ProductListings.jsx # Product grid with search/filter
│   ├── Profile.jsx       # User profile management
│   ├── ProtectedRoute.jsx # Route protection wrapper
│   ├── Register.jsx      # User registration
│   └── SearchBox.jsx     # Search input component
├── hooks/                 # Custom React hooks
│   └── useCsrfToken.js   # CSRF token management
├── store/                 # Redux store configuration
│   ├── auth-slice.js     # Authentication state
│   ├── cart-slice.js     # Shopping cart state
│   ├── csrf-slice.js     # CSRF token state
│   └── store.js          # Redux store setup
├── App.jsx               # Root application component
├── App.css              # Global application styles
├── index.css            # Base styles and Tailwind imports
└── main.jsx             # Application entry point
```

## Getting Started

### Prerequisites
- Node.js (version 18 or higher)
- npm or yarn package manager
- Git for version control

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd berniestore-ui
```

2. Install dependencies:
```bash
npm install
```

3. Set up environment variables:
```bash
# Create .env file in root directory
VITE_API_BASE_URL=https://berniesticker.onrender.com/api/v1
```

### Development

1. Start the development server:
```bash
npm run dev
```

2. Open your browser and navigate to `http://localhost:5173`

3. The application will automatically reload when you make changes

### Building for Production

1. Create a production build:
```bash
npm run build
```

2. Preview the production build:
```bash
npm run preview
```

## Component Architecture

### State Management
The application uses Redux Toolkit with three main slices:

- **Auth Slice**: Manages user authentication state, JWT tokens, and user profile
- **Cart Slice**: Handles shopping cart operations with localStorage persistence
- **CSRF Slice**: Manages CSRF tokens for secure API requests

### API Integration
- Centralized API client with Axios interceptors
- Automatic JWT token attachment for authenticated requests
- CSRF token handling for POST/PUT/DELETE operations
- Error handling with user-friendly messages

### Routing Structure
- Public routes: Home, About, Contact, Product Details
- Authentication routes: Login, Register
- Protected routes: Cart, Checkout, Profile, Orders
- Admin routes: Order management, Message handling

## Key Features Implementation

### Shopping Cart
- Persistent cart storage using localStorage
- Real-time quantity updates with Redux state management
- Cart total calculations with tax and shipping
- Empty cart state with call-to-action

### Payment Processing
- Stripe Elements integration for secure card input
- Real-time form validation with error handling
- Payment confirmation and order creation workflow
- PCI DSS compliant payment processing

### User Authentication
- JWT-based authentication with automatic token refresh
- Protected route guards for secure pages
- User profile management with address storage
- Registration and login with form validation

### Product Management
- Dynamic product loading from REST API
- Search and filter functionality with debounced input
- Product detail pages with image zoom effects
- Responsive product grid with lazy loading

## Deployment

### Environment Configuration
- **Development**: Local development with hot reload
- **Staging**: Preview environment for testing
- **Production**: Optimized build with code splitting

### Build Optimization
- Vite-based build system for fast compilation
- Code splitting for optimal bundle sizes
- Tree shaking to eliminate unused code
- Asset optimization with compression

## Contributing

### Development Workflow
1. Create feature branch from main
2. Implement changes following React best practices
3. Add tests for new components and functionality
4. Submit pull request with detailed description

### Code Standards
- Follow React functional component patterns
- Use TypeScript for type safety (when applicable)
- Implement proper error boundaries
- Write accessible components with ARIA attributes
- Follow naming conventions and component composition patterns

## Performance Considerations

### Optimization Strategies
- React.memo for component memoization
- useMemo and useCallback for expensive calculations
- Lazy loading for route-based code splitting
- Image optimization with proper sizing and formats

### Bundle Analysis
- Use Vite's built-in bundle analyzer
- Monitor bundle size and identify optimization opportunities
- Implement dynamic imports for large dependencies

## Security Features

### Client-Side Security
- Input validation and sanitization
- XSS prevention with proper data escaping
- CSRF protection for state-changing operations
- Secure storage of authentication tokens

### API Security
- JWT token expiration and refresh handling
- HTTPS enforcement for all API communications
- Proper error handling without information leakage

## Browser Support

- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+
- Mobile browsers with ES2020 support

## Troubleshooting

### Common Issues
- **Build failures**: Check Node.js version and clear node_modules
- **API connection**: Verify environment variables and backend availability
- **Payment errors**: Confirm Stripe configuration and test keys (Please use Card Number: 4242 4242 4242 4242 for testing, not any other card number)
- **Authentication issues**: Clear localStorage and check JWT token validity

### Development Tips
- Use React Developer Tools for component debugging
- Monitor Redux state with Redux DevTools Extension
- Check network tab for API request/response issues
- Use browser console for JavaScript errors and warnings
