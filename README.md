# E-Commerce Auction System

A full-stack auction-based e-commerce platform built using Java, RESTful APIs, WebSockets, SQLite, and HTML/CSS. The application enables users to create listings, search products, participate in real-time auctions, and complete purchases through an integrated payment workflow.

This project demonstrates backend API development, real-time communication, database management, session-based authentication, and full-stack application design.

---

## Features

### User Management
- User registration and authentication
- Session-based login
- User profile management
- Secure access to protected features

### Marketplace
- Create auction listings
- Browse available products
- Keyword search functionality
- View detailed product information
- Delete listings

### Auction System
Supports multiple auction formats:

- **Forward Auction**
  - Real-time bidding
  - Highest bid validation
  - Automatic highest bidder updates

- **Dutch Auction**
  - Descending price auction
  - Instant purchase at current auction price

### Real-Time Communication
- WebSocket-based live bidding
- Instant bid updates
- Multiple connected clients receive auction events

### Order & Payment
- Purchase workflow
- Shipping cost calculation
- Optional expedited shipping
- Transaction management
- Order details page

---

# System Architecture

```
Frontend (HTML/CSS/JavaScript)
            │
            ▼
REST APIs (Jersey / Jakarta)
            │
            ▼
Business Logic Controllers
            │
            ▼
DAO Layer
            │
            ▼
SQLite Database
```

WebSocket connections run alongside the REST API to provide live auction updates without requiring page refreshes.

---

# Technologies Used

## Backend

- Java 17
- Jakarta REST (JAX-RS)
- Jersey
- Jakarta Servlet
- WebSocket API
- Maven

## Database

- SQLite
- DAO Design Pattern
- JDBC

## Frontend

- HTML5
- CSS3
- JavaScript

## Tools

- Maven
- Docker
- Postman
- Git

---

# Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── AuctionController.java
│   │   ├── ItemController.java
│   │   ├── UserController.java
│   │   ├── TransactionController.java
│   │   ├── ItemDAO.java
│   │   ├── UserDAO.java
│   │   ├── TransactionDAO.java
│   │   ├── BiddingWebSocket.java
│   │   ├── ForwardBidWebSocket.java
│   │   └── DutchBidWebSocket.java
│   │
│   └── webapp/
│       ├── login.html
│       ├── signup.html
│       ├── displayItems.html
│       ├── createItem.html
│       ├── Payment.html
│       └── ...
```

---

# REST API Overview

## User API

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/users` | Register user |
| GET | `/users/all` | Retrieve all users |
| GET | `/users/{id}` | Get user information |
| PUT | `/users/{id}` | Update user |

---

## Item API

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/items/all` | Retrieve all products |
| GET | `/items/{id}` | Get product details |
| GET | `/items/search/{keyword}` | Search products |
| POST | `/items` | Create listing |
| DELETE | `/items/{id}` | Delete listing |

---

## Auction API

### Forward Auction

- Retrieve auction item
- Place bids
- Validate bid amounts
- Update highest bidder

### Dutch Auction

- Retrieve auction
- Submit purchase/bid
- Handle descending price logic

---

## Payment API

| Method | Endpoint |
|---------|----------|
| POST | `/payment/submit` |
| GET | `/payment/order` |
| PUT | `/payment/{id}` |
| DELETE | `/payment/{id}` |

---

# Database Design

The application uses SQLite with separate DAO classes responsible for data access.

Main entities include:

- Users
- Items
- Transactions
- Auction data
- Bid history

The DAO pattern separates persistence logic from business logic, making the application easier to maintain and extend.

---

# Real-Time Auction Workflow

```
Seller creates auction
        │
        ▼
Product listed
        │
        ▼
Clients connect via WebSocket
        │
        ▼
User submits bid
        │
        ▼
Server validates bid
        │
        ▼
Database updated
        │
        ▼
Broadcast updated bid
        │
        ▼
All connected users receive live update
```

---

# Installation

## Prerequisites

- Java 17+
- Maven
- Tomcat (or compatible Jakarta Servlet container)
- SQLite

---

## Clone Repository

```bash
git clone https://github.com/yourusername/e-commerce-auction-system.git
cd e-commerce-auction-system
```

---

## Build

```bash
mvn clean install
```

---

## Deploy

Deploy the generated WAR file to your servlet container (e.g., Apache Tomcat).

---

# API Testing

A Postman collection is included:

```
Auction System.postman_collection.json
```

Import the collection into Postman to test:

- User registration
- Authentication
- Product creation
- Search
- Auctions
- Payments

---

# Skills Demonstrated

- Full-stack web application development
- RESTful API design
- Real-time communication with WebSockets
- Java backend development
- MVC architecture
- DAO design pattern
- Session-based authentication
- SQLite database integration
- CRUD operations
- HTTP request handling
- JSON serialization
- Maven project management
- API testing with Postman

---

# Future Improvements

- JWT authentication
- Password hashing (BCrypt)
- Shopping cart functionality
- Product image uploads
- Email notifications
- Admin dashboard
- Bid history analytics
- Docker Compose deployment
- Unit and integration testing
- CI/CD pipeline using GitHub Actions

---

# Author

**Abhirami Venugopal**

Bachelor of Software Engineering (Big Data)  
York University

```
