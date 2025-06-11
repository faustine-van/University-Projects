# Java Projects 🚀

Welcome to my collection of Java projects! This repository contains various Java programs and mini-projects I’ve worked on for practice, learning, and demonstration purposes.

## 📁 Project Structure

Each folder inside this repository represents a different Java project or concept. Examples include:

- **OOPExamples/** – Demonstrates object-oriented programming (OOP) principles like inheritance, polymorphism, encapsulation, and abstraction.
- **JDBCExample/** – Java programs using JDBC to connect with PostgreSQL databases.
- **InnerClasses/** – Examples of inner, static, and anonymous inner classes.
- **Miscellaneous/** – Standalone Java programs for learning different concepts.

## 🛠️ Technologies Used

- Java (JDK 8 or higher)
- PostgreSQL (for JDBC database projects)
- Git (for version control)
- WSL (Ubuntu on Windows Subsystem for Linux) or any OS with Java support

## ⚙️ How to Run Java Programs
### 🔹 For General Java Programs

1. Navigate to the project folder:
   `cd java-projects`
2. Compile the Java program:
    `javac Main.java`
3. Run the program:
    `java Main`
✅ Make sure you're in the correct directory and have Java installed (`java --version`).
---
### 🐘 How to Run the PostgresConnect (JDBC) Program
If you’re using a Java program that connects to a PostgreSQL database using JDBC:

1. Ensure you have the PostgreSQL JDBC driver file (e.g., `postgresql-42.6.0.jar`) in your project folder or system path.

2. Compile the Java file with the JDBC driver:

`javac -cp postgresql-42.6.0.jar PostgresConnect.java`
3. Run the program:
`java -cp .:postgresql-42.6.0.jar PostgresConnect`

💡 If you are using Windows (cmd or PowerShell) instead of Linux/WSL, use a semicolon ; instead of a colon :

`java -cp .;postgresql-42.6.0.jar PostgresConnect`
---
## 🙌 Contributing
This is a personal learning repository, but feel free to fork it or suggest improvements via pull requests.
--- 
## ✨ Author
[Muhayemariya Faustine] – @faustine-van
Learning Java, one project at a time ☕
---
## 📜 License
This project is licensed under the [MIT License](https://github.com/faustine-van/java-projects?tab=License-1-ov-file).