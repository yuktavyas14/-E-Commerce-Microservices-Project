# E-Commerce Frontend Application

A modern Angular application with Material Design for the E-Commerce Microservices project.

## Features

- 🔐 **Authentication**: JWT-based login and registration
- 🛡️ **Guards**: Auth and Admin guards for route protection
- 🛍️ **Product Management**: Browse, create, update, and delete products (Admin only)
- 📦 **Order Placement**: Place orders for products
- 🎨 **Material Design**: Beautiful UI with Angular Material components
- 📱 **Responsive**: Mobile-friendly design

## Tech Stack

- **Angular 20** - Latest Angular framework
- **Angular Material 19** - Material Design components
- **RxJS** - Reactive programming
- **TypeScript** - Type-safe development
- **JWT** - Token-based authentication


## Project Structure

```
src/app/
├── components/
│   ├── home/              # Landing page
│   ├── login/             # Login component
│   ├── register/          # Registration component
│   ├── navbar/            # Navigation bar
│   ├── product-list/      # Product listing
│   ├── product-form/      # Product create/edit dialog
│   └── order-dialog/      # Order placement dialog
├── guards/
│   ├── auth.guard.ts      # Authentication guard
│   └── admin.guard.ts     # Admin role guard
├── interceptors/
│   └── auth.interceptor.ts # JWT token interceptor
├── models/
│   ├── user.model.ts      # User interfaces
│   ├── product.model.ts   # Product interfaces
│   └── order.model.ts     # Order interfaces
├── services/
│   ├── auth.service.ts    # Authentication service
│   ├── product.service.ts # Product service
│   └── order.service.ts   # Order service
└── environments/
    └── environment.ts     # Environment configuration
```

## Getting Started

### Prerequisites

- Node.js (v18.19 or higher)
- npm
- Backend microservices running on http://localhost:8080

### Installation

1. Navigate to the project directory:
```bash
cd ecommerce-frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm start
```

4. Open your browser and navigate to `http://localhost:4200`

## Configuration

Update the API URL in `src/environments/environment.ts`:

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080'  // Your API Gateway URL
};
```

## User Roles

### Regular User (USER)
- Browse products
- Place orders
- View profile

### Admin (ADMIN)
- All USER permissions
- Create new products
- Edit existing products
- Delete products
- Delete users

## API Endpoints Used

- `POST /users/register` - Register new user
- `POST /users/login` - Login and get JWT token
- `GET /users` - Get all users (requires auth)
- `PUT /users/:id` - Update user (requires auth)
- `DELETE /users/:id` - Delete user (requires admin)
- `GET /products` - Get all products (requires auth)
- `GET /products/:id` - Get product by ID (requires auth)
- `POST /products` - Create product (requires admin)
- `PUT /products/:id` - Update product (requires admin)
- `DELETE /products/:id` - Delete product (requires admin)
- `POST /orders` - Place order (requires auth)

## Authentication Flow

1. User registers with username, password, and role
2. User logs in with credentials
3. Backend returns JWT token
4. Token is stored in localStorage
5. Auth interceptor adds token to all API requests
6. Auth guard protects routes requiring authentication
7. Admin guard protects routes requiring admin role

## Material Components Used

- MatToolbar - Navigation bar
- MatCard - Content containers
- MatButton - Buttons
- MatIcon - Material icons
- MatFormField - Form inputs
- MatInput - Text inputs
- MatSelect - Dropdowns
- MatDialog - Modal dialogs
- MatSnackBar - Toast notifications
- MatProgressSpinner - Loading indicators
- MatMenu - Dropdown menus

## License

This project is part of the E-Commerce Microservices Project.
