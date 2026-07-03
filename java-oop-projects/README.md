# Java OOP Projects

This directory contains a collection of Java projects I built while learning **Object-Oriented Programming (OOP)**. These projects were created as part of my coursework and practice in applying core OOP principles.

## Topics Covered
- Classes and Objects
- Constructors and Methods
- Inheritance and Interfaces
- Encapsulation and Access Modifiers
- Abstract Classes
- Exception Handling
- File I/O
- JDBC with PostgreSQL

## Project List

| Project Name         | Description                                                       |
|----------------------|-------------------------------------------------------------------|
| `Classesandobjects`  | include some others files                                         |
| `User.java`          | Demonstrates polymorphism with shape classes.                     |
| `PostgresConnect.java` | Connects to a PostgreSQL database using JDBC. Demonstrates DB access. |

> *Note: These Files names above are examples included in this*

---

## Requirements
- Java 8 or higher
- PostgreSQL installed
- JDBC Driver (`postgresql-42.6.0.jar`)
- IDE (e.g., IntelliJ, Eclipse, VS Code) or terminal

---

## How to Run Any Project
```bash
# Clone the repository:
git clone https://github.com/faustine-van/University-Projects.git
cd University-Projects/java-oop-projects
# Compile:
javac FileName.java
# Run:
java FileName
```
For JDBC-based files, include the driver in the classpath as shown below.

---

## How I Learned JDBC with PostgreSQL

I created the file `PostgresConnect.java` to learn how Java connects with PostgreSQL databases using JDBC. Here's how I set it up:

### Download JDBC Driver
```bash
wget https://jdbc.postgresql.org/download/postgresql-42.6.0.jar
# move driver
mv postgresql-42.6.0.jar java-oop-projects/
cd java-oop-projects
# Compile the Java file
javac -cp postgresql-42.6.0.jar PostgresConnect.java
# Run the program
java -cp postgresql-42.6.0.jar PostgresConnect
```

---

Think you can improve this? Fork it, code it, and show us what you’ve got!
