# DAO-JDBC - Java Database Access Object Implementation

A practical implementation of the **Data Access Object (DAO)** design pattern using **JDBC (Java Database Connectivity)** in Java. This project demonstrates professional database interaction patterns, CRUD operations, and clean architecture principles for data persistence.

This project was developed as part of the Udemy course **"Java COMPLETO Programação Orientada a Objetos + Projetos"** and serves as a complete reference implementation for building database-driven Java applications.

## 📋 Table of Contents

- [About](#about)
- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [Usage Examples](#usage-examples)

## 🔍 About

This project implements a complete data access layer for managing **Sellers** and **Departments** using the DAO pattern. It provides a clean separation between business logic and data access logic, making the code more maintainable, testable, and scalable.

### What is the DAO Pattern?

The DAO (Data Access Object) pattern is a structural pattern that provides an abstract interface to a database or other persistence mechanism. It separates the data persistence logic from business logic, allowing you to:

- Change database implementations without affecting business logic
- Write more testable code
- Maintain clean architecture principles
- Reuse data access code across different parts of the application

## ✨ Features

- **Complete CRUD Operations**: Create, Read, Update, and Delete for both Sellers and Departments
- **DAO Pattern Implementation**: Clean separation of concerns with interface-based design
- **Factory Pattern**: DAOFactory for creating DAO instances
- **Database Connection Management**: Centralized connection handling with resource cleanup
- **Exception Handling**: Custom exceptions for database errors
- **Relationship Mapping**: Handles foreign key relationships between Sellers and Departments
- **Query Support**: 
  - Find by ID
  - Find all records
  - Find sellers by department
  - Custom queries with joins

## 🛠️ Technologies

- **Java 11+**: Core programming language
- **JDBC**: Java Database Connectivity API for database interaction
- **PostgreSQL**: Default relational database (configurable for other databases)
- **DAO Pattern**: Design pattern for data access abstraction
- **Factory Pattern**: For object creation

## 📦 Prerequisites

Before you begin, ensure you have the following installed on your computer:

1. **Java Development Kit (JDK) 11 or higher**
   - Download from: [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/)
   - Verify installation:
     ```bash
     java -version
     ```

2. **PostgreSQL Database** (or another SQL database of your choice)
   - Download from: [PostgreSQL Official Site](https://www.postgresql.org/download/)
   - Verify installation:
     ```bash
     psql --version
     ```

3. **PostgreSQL JDBC Driver**
   - Download from: [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download/)
   - Or use Maven/Gradle to manage dependencies

4. **IDE** (optional but recommended)
   - [Eclipse](https://www.eclipse.org/downloads/)
   - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
   - [VS Code](https://code.visualstudio.com/) with Java Extension Pack

## 🚀 Installation

Follow these steps to set up the project on your local machine:

### 1. Clone the Repository

```bash
git clone https://github.com/CarlosHenriqueSL/DAO-JDBC.git
cd DAO-JDBC
```

### 2. Create the lib Directory (if using JDBC driver manually)

If you're not using a build tool like Maven or Gradle, you'll need to manually add the JDBC driver:

```bash
mkdir lib
```

### 3. Download and Add the PostgreSQL JDBC Driver

1. Download the PostgreSQL JDBC driver (`.jar` file) from [here](https://jdbc.postgresql.org/download/)
2. Place the downloaded `.jar` file in the `lib/` directory you just created

**Note**: If you're using a different database (MySQL, MariaDB, etc.), download the appropriate JDBC driver for that database.

### 4. Import the Project into Your IDE

#### For Eclipse:
1. Open Eclipse
2. Go to `File` → `Import` → `General` → `Existing Projects into Workspace`
3. Select the `DAO-JDBC` folder
4. Click `Finish`

#### For IntelliJ IDEA:
1. Open IntelliJ IDEA
2. Click `File` → `Open`
3. Navigate to the `DAO-JDBC` folder and select it
4. Click `OK`

#### For VS Code:
1. Open VS Code
2. Click `File` → `Open Folder`
3. Navigate to and select the `DAO-JDBC` folder

## 🗄️ Database Setup

### 1. Create the Database

Open your PostgreSQL client (pgAdmin, psql, or command line) and create a new database:

```sql
CREATE DATABASE coursejdbc;
```

**Note**: You can use a different database name, but make sure to update it in the `db.properties` file later.

### 2. Run the Database Schema Script

The project includes a `database.sql` file in the root directory. Execute this script to create the tables and populate them with sample data:

#### Using psql command line:

```bash
psql -U your_username -d coursejdbc -f database.sql
```

#### Using pgAdmin:
1. Open pgAdmin
2. Connect to your PostgreSQL server
3. Right-click on the `coursejdbc` database → `Query Tool`
4. Open the `database.sql` file or copy its contents
5. Execute the script

#### Using another SQL client:
Simply open the `database.sql` file and run all the SQL statements in your database client.

### 3. Verify the Database Setup

After running the script, verify that the tables were created:

```sql
-- Check tables
\dt  -- (in psql)

-- Or use:
SELECT * FROM department;
SELECT * FROM seller;
```

You should see 4 departments and 6 sellers in the database.

### Database Schema

The database consists of two tables:

**Department Table:**
- `id` (SERIAL PRIMARY KEY)
- `name` (VARCHAR(60))

**Seller Table:**
- `id` (SERIAL PRIMARY KEY)
- `name` (VARCHAR(60) NOT NULL)
- `email` (VARCHAR(100) NOT NULL)
- `birthdate` (TIMESTAMP NOT NULL)
- `basesalary` (NUMERIC(10,2) NOT NULL)
- `departmentid` (INTEGER NOT NULL, FOREIGN KEY to department.id)

## ⚙️ Configuration

### Configure Database Connection

1. Open the `db.properties` file in the root directory of the project

2. Update the file with your database credentials:

```properties
user=YOUR_DATABASE_USERNAME
password=YOUR_DATABASE_PASSWORD
dburl=jdbc:postgresql://localhost:5432/coursejdbc
useSSL=false
```

**Parameters Explanation:**
- `user`: Your PostgreSQL username (default is usually `postgres`)
- `password`: Your PostgreSQL password
- `dburl`: JDBC connection URL
  - Format: `jdbc:postgresql://[host]:[port]/[database_name]`
  - Default host: `localhost`
  - Default port: `5432` (PostgreSQL default)
  - Database name: `coursejdbc` (or whatever you named your database)
- `useSSL`: Set to `false` for local development (use `true` for production)

### Using a Different Database

If you want to use MySQL instead of PostgreSQL:

1. **Download the MySQL JDBC driver** and place it in the `lib/` directory

2. **Update `db.properties`:**
   ```properties
   user=YOUR_MYSQL_USERNAME
   password=YOUR_MYSQL_PASSWORD
   dburl=jdbc:mysql://localhost:3306/coursejdbc?useSSL=false&serverTimezone=UTC
   useSSL=false
   ```

3. **Modify `database.sql`** to use MySQL syntax:
   - Change `SERIAL` to `INT AUTO_INCREMENT`
   - Change `TIMESTAMP` to `DATETIME`
   - Adjust data types as needed

## ▶️ Running the Application

The project includes two main programs to test the DAO implementations:

### Program 1: Seller Operations

This program demonstrates CRUD operations on the Seller entity.

1. **Navigate to** `src/application/Program.java` in your IDE
2. **Run the program** (Right-click → Run as Java Application)

The program will execute the following tests:
- Find seller by ID
- Find sellers by department
- Find all sellers
- Insert a new seller
- Update an existing seller
- Delete a seller (requires user input)

### Program 2: Department Operations

This program demonstrates CRUD operations on the Department entity.

1. **Navigate to** `src/application/Program2.java` in your IDE
2. **Run the program**

The program will execute the following tests:
- Find department by ID
- Find all departments
- Insert a new department
- Update an existing department
- Delete a department (requires user input)

### Running from Command Line

If you prefer to run from the command line:

```bash
# Compile the project
javac -cp ".:lib/*" -d bin src/**/*.java

# Run Program (Seller operations)
java -cp "bin:lib/*" application.Program

# Run Program2 (Department operations)
java -cp "bin:lib/*" application.Program2
```

**Note**: On Windows, replace `:` with `;` in the classpath:
```bash
java -cp "bin;lib/*" application.Program
```

## 📁 Project Structure

```
DAO-JDBC/
│
├── src/
│   ├── application/
│   │   ├── Program.java           # Main program for Seller operations
│   │   └── Program2.java          # Main program for Department operations
│   │
│   ├── db/
│   │   ├── DB.java                # Database connection management
│   │   ├── DbException.java       # Custom exception for database errors
│   │   └── DbIntegrityException.java  # Exception for integrity violations
│   │
│   └── model/
│       ├── dao/
│       │   ├── DAOFactory.java         # Factory for creating DAO instances
│       │   ├── DepartmentDAO.java      # Interface for Department operations
│       │   ├── SellerDAO.java          # Interface for Seller operations
│       │   └── impl/
│       │       ├── DepartmentDAOJDBC.java  # JDBC implementation of DepartmentDAO
│       │       └── SellerDAOJDBC.java      # JDBC implementation of SellerDAO
│       │
│       └── entities/
│           ├── Department.java     # Department entity class
│           └── Seller.java         # Seller entity class
│
├── lib/                            # JDBC drivers (create this directory)
├── database.sql                    # Database schema and sample data
├── db.properties                   # Database configuration file
└── README.md                       # This file
```

### Key Components:

- **Entities**: Represent database tables as Java objects (Department, Seller)
- **DAO Interfaces**: Define contracts for data access operations
- **DAO Implementations**: Concrete implementations using JDBC
- **DAOFactory**: Factory class for creating DAO instances
- **DB**: Utility class for managing database connections
- **Exceptions**: Custom exceptions for handling database errors

## 💡 Usage Examples

### Example 1: Finding a Seller by ID

```java
SellerDAO sellerDAO = DAOFactory.createSellerDAO();
Seller seller = sellerDAO.findById(1);
System.out.println(seller);
```

### Example 2: Finding All Departments

```java
DepartmentDAO departmentDAO = DAOFactory.createDepartmentDAO();
List<Department> departments = departmentDAO.findAll();
for (Department dept : departments) {
    System.out.println(dept);
}
```

### Example 3: Inserting a New Seller

```java
SellerDAO sellerDAO = DAOFactory.createSellerDAO();
Department dept = new Department(1, null);  // Department with ID 1
Seller newSeller = new Seller(
    null,                      // ID will be auto-generated
    "John Doe",
    "john@example.com",
    LocalDate.of(1990, 5, 15),
    3500.00,
    dept
);
sellerDAO.insert(newSeller);
System.out.println("New seller ID: " + newSeller.getId());
```

### Example 4: Updating a Department

```java
DepartmentDAO departmentDAO = DAOFactory.createDepartmentDAO();
Department dept = departmentDAO.findById(1);
dept.setName("IT Department");
departmentDAO.update(dept);
System.out.println("Department updated successfully!");
```

### Example 5: Finding Sellers by Department

```java
SellerDAO sellerDAO = DAOFactory.createSellerDAO();
Department dept = new Department(2, null);
List<Seller> sellers = sellerDAO.findByDepartment(dept);
for (Seller seller : sellers) {
    System.out.println(seller);
}
```

## 🤝 Contributing

Contributions are welcome! If you'd like to improve this project:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/improvement`)
3. Make your changes
4. Commit your changes (`git commit -am 'Add new feature'`)
5. Push to the branch (`git push origin feature/improvement`)
6. Open a Pull Request

## 📝 License

This project is open source and available for educational purposes.

## 👨‍💻 Author

**Carlos Henrique SL**

## 🙏 Acknowledgments

- Course: "Java COMPLETO Programação Orientada a Objetos + Projetos" on Udemy
- Thanks to all contributors and the Java community

---

**Happy Coding!** 🚀

If you encounter any issues or have questions, please open an issue on the GitHub repository.
