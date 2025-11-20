# DAO JDBC Implementation

This project was developed as part of the Udemy course **"Java COMPLETO Programação Orientada a Objetos + Projetos"**. It serves as a practical demonstration of using **JDBC (Java Database Connectivity)** with the **DAO (Data Access Object)** pattern in Java.

## Technologies Used

- **Java**: Core programming language.
- **JDBC**: For database connectivity.
- **DAO Pattern**: Architectural pattern for data access.

## Configuration

To run this project locally, you need to configure the database connection settings.

### Prerequisites

1.  **Java Development Kit (JDK)** installed.
2.  A SQL Database installed (e.g., MySQL or PostgreSQL).
3.  The appropriate JDBC Driver added to your project's classpath.

### Setting up the Database Connection

1.  Navigate to the file `db.properties`.
2.  Open the file and ensure it contains the correct credentials for your local database environment.

    **Structure:**
    ```properties
    user=YOUR_DB_USERNAME
    password=YOUR_DB_PASSWORD
    dburl=jdbc:mysql://localhost:3306/your_database_name
    useSSL=false
    ```
3.  **Database Schema**: Run the `database.sql` file (present of the root folder) in your SGBD. It was used on PostgreSQL and may require adjustments based on the SGBD you're using.

## Usage

1.  Clone the repository.
2.  Import the project into your IDE (Eclipse, IntelliJ, NetBeans).
3.  Configure the `db.properties` as shown above.
4.  Run the `database.sql` script on your SGBD.
5.  Run the main application class to test the database operations.
