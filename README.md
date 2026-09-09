# Inventory and Order Management API

A backend-focused Java project for managing products, customers, inventory, and orders.

The project is being developed as part of my software engineering internship preparation and is designed to progressively incorporate backend development concepts including object-oriented design, business logic, SQL, PostgreSQL, JDBC, REST APIs, testing, transactions, and deployment.

## Current Status

In Progress

The project currently includes:

* Core Java domain model and business logic
* Product inventory management
* Customer and order management
* Order placement and cancellation
* Stock validation and restoration
* PostgreSQL database schema
* JDBC database connectivity
* Complete PostgreSQL CRUD operations for products
* Initial PostgreSQL persistence for customers

The REST API layer has not yet been implemented.

## Technologies

* Java 17
* Maven
* PostgreSQL
* JDBC
* pgAdmin 4
* Git
* GitHub

Planned later in the project:

* JUnit
* Spring Boot
* REST APIs
* JSON
* OpenAPI and Swagger
* Flyway database migrations
* Docker
* Deployment

## Current Architecture

The project currently separates domain logic, services, and persistence responsibilities.

Examples of current classes include:

```text
Product
Customer
Order
OrderItem
OrderStatus
Inventory
CustomerManager
OrderManager
OrderService
OrderRepository
ProductRepository
CustomerRepository
DatabaseConnection
```

Custom exceptions are also used for business rule failures, including:

```text
OrderNotFoundException
OrderAlreadyPlacedException
InsufficientStockException
EmptyOrderException
```

## Core Business Logic

### Products

Products contain:

* ID
* Name
* Price
* Stock quantity

Product validation prevents invalid IDs, blank names, negative prices, and negative stock.

Stock can be added or removed through controlled methods.

### Orders

Orders:

* Belong to a customer
* Contain one or more order items
* Track an order status
* Validate stock before placement
* Subtract inventory when successfully placed
* Restore inventory when cancelled

Current order statuses include:

```text
PENDING
PLACED
CANCELLED
```

Orders cannot be modified after placement, and invalid state transitions are prevented.

### Order Items

Each order item stores:

* Product
* Quantity
* Unit price

The unit price is captured when the order item is created so the line total does not depend on future product price changes.

### Customers

Customers contain:

* ID
* Name
* Email
* Associated orders

Customer validation prevents invalid data and duplicate order IDs.

## PostgreSQL Database

The project uses a PostgreSQL database named:

```text
inventory_order_management
```

The current schema contains four main tables:

```text
products
customers
orders
order_items
```

### Relationships

```text
orders.customer_id references customers.id
order_items.order_id references orders.id
order_items.product_id references products.id
```

The database uses:

* Primary keys
* Foreign keys
* NOT NULL constraints
* UNIQUE constraints
* CHECK constraints
* Composite primary key on order_items using order_id and product_id

## JDBC Persistence

Java successfully connects to PostgreSQL using JDBC.

Database credentials are not stored directly in the source code. The PostgreSQL password is read from the environment variable:

```text
DB_PASSWORD
```

The current JDBC URL is:

```text
jdbc:postgresql://localhost:5432/inventory_order_management
```

## ProductRepository

ProductRepository currently supports full CRUD functionality:

```text
Create: insertProduct()
Read: getProductById()
Read: getAllProducts()
Update: updateProduct()
Delete: deleteProduct()
```

Examples of completed persistence behavior include:

* Inserting Java Product objects into PostgreSQL
* Retrieving a product by ID
* Returning null when a product does not exist
* Retrieving all products as Java objects
* Updating an existing database row
* Deleting a product by ID

JDBC concepts currently used include:

```text
Connection
PreparedStatement
ResultSet
executeQuery()
executeUpdate()
try-with-resources
parameterized SQL
```

## CustomerRepository

CustomerRepository is currently being developed.

Implemented:

```text
insertCustomer()
```

Remaining customer persistence operations will include:

```text
getCustomerById()
getAllCustomers()
updateCustomer()
deleteCustomer()
```

## Example Product Data

```text
ID | Name     | Price  | Stock
1  | Keyboard | 50.00  | 25
2  | Mouse    | 25.00  | 50
3  | Monitor  | 200.00 | 10
```

## Current Project Progress

```text
Core Java domain model: Complete
Business rules: Complete
Order placement and cancellation: Complete
Inventory management: Complete
PostgreSQL schema: Complete
Foundational SQL: Complete
Java to PostgreSQL JDBC connection: Complete
ProductRepository CRUD: Complete
CustomerRepository: In Progress
Order persistence: Not Started
Order item persistence: Not Started
Database transactions: Not Started
JUnit test suite: Not Started
Spring Boot: Not Started
REST API: Not Started
API documentation: Not Started
Docker and deployment: Not Started
```

## Next Steps

The immediate development plan is:

1. Complete CustomerRepository
2. Add PostgreSQL persistence for orders and order items
3. Implement database transactions for order placement and cancellation
4. Replace remaining in-memory persistence with PostgreSQL-backed repositories
5. Improve automated testing with JUnit
6. Introduce Spring Boot
7. Expose the application through REST endpoints
8. Add validation and structured API error handling
9. Add database migrations and API documentation
10. Containerize and deploy the application

## Long-Term Goal

The final goal is a portfolio-ready backend application demonstrating:

* Object-oriented Java
* Backend architecture
* Business rule implementation
* Relational database design
* SQL
* JDBC
* Transaction management
* REST API development
* Automated testing
* API documentation
* Deployment

The project is intentionally being developed incrementally so each new backend concept is integrated into an existing working system rather than implemented as an isolated exercise.
