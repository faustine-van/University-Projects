DROP DATABASE IF EXISTS transcript_system;
CREATE DATABASE transcript_system;
USE transcript_system;

CREATE TABLE faculties (
    faculty_id INT PRIMARY KEY AUTO_INCREMENT,
    faculty_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE departments(
  department_id INT PRIMARY KEY AUTO_INCREMENT,
  department_name VARCHAR(255) NOT NULL,
  faculty_id INT NOT NULL,

  CONSTRAINT fk_faculty
  FOREIGN KEY (faculty_id)
  REFERENCES faculties(faculty_id)
);

CREATE TABLE students(
  student_id INT PRIMARY KEY AUTO_INCREMENT,
  reg_no VARCHAR(50) UNIQUE NOT NULL,
  fullname VARCHAR(255) NOT NULL,
  department_id INT NOT NULL,

  CONSTRAINT fk_department
   FOREIGN KEY (department_id)
   REFERENCES departments(department_id)
);

CREATE TABLE courses(
  course_id INT PRIMARY KEY AUTO_INCREMENT,
  courses_code VARCHAR(50) UNIQUE NOT NULL,
  courses_name VARCHAR(255) NOT NULL,
  credit_hours INT NOT NULL
);

CREATE TABLE results(
  result_id INT PRIMARY KEY AUTO_INCREMENT,
  student_id INT NOT NULL,
  course_id INT NOT NULL,
  marks DECIMAL(5, 2) NOT NULL,
  semester VARCHAR(255) NOT NULL,
  academic_year VARCHAR(255) NOT NULL,

  CONSTRAINT fk_student
   FOREIGN KEY (student_id)
   REFERENCES students(student_id),

  CONSTRAINT fk_course
   FOREIGN KEY (course_id)
   REFERENCES courses(course_id)
);

-- sample data

INSERT INTO faculties (faculty_name) VALUES ('Faculty of Computing and Information Sciences');

INSERT INTO departments (department_name, faculty_id) VALUES
('Software Engineering', 1),
('Information Management', 1);

INSERT INTO courses (courses_code, courses_name, credit_hours) VALUES
('CIS101', 'Programming in Java', 4),
('CIS102', 'Database Systems', 3),
('CIS103', 'Web Development', 3),
('CIS104', 'Computer Networks', 3),
('CIS105', 'Software Engineering', 4);

INSERT INTO students (reg_no, fullname, department_id) VALUES
('26101/2024', 'Jean Claude Habimana', 1),
('26245/2024', 'Janet Uwase', 1),
('26378/2024', 'Joseph Nkurunziza', 1),
('26412/2024', 'Kevin Mugisha', 2),
('26587/2024', 'Keza Ingabire', 2),
('26633/2024', 'Kelly Mutesi', 2),
('26719/2024', 'Alice Mukamana', 1),
('26854/2024', 'Eric Niyonsenga', 2);

INSERT INTO results (student_id, course_id, marks, semester, academic_year) VALUES
(1,1,90,'Semester 1','2025-2026'), (1,2,88,'Semester 1','2025-2026'), (1,3,92,'Semester 1','2025-2026'), (1,4,86,'Semester 1','2025-2026'), (1,5,91,'Semester 1','2025-2026'),
(2,1,80,'Semester 1','2025-2026'), (2,2,78,'Semester 1','2025-2026'), (2,3,82,'Semester 1','2025-2026'), (2,4,76,'Semester 1','2025-2026'), (2,5,79,'Semester 1','2025-2026'),
(3,1,65,'Semester 1','2025-2026'), (3,2,70,'Semester 1','2025-2026'), (3,3,62,'Semester 1','2025-2026'), (3,4,68,'Semester 1','2025-2026'), (3,5,72,'Semester 1','2025-2026'),
(4,1,55,'Semester 1','2025-2026'), (4,2,52,'Semester 1','2025-2026'), (4,3,58,'Semester 1','2025-2026'), (4,4,50,'Semester 1','2025-2026'), (4,5,56,'Semester 1','2025-2026'),
(5,1,88,'Semester 1','2025-2026'), (5,2,91,'Semester 1','2025-2026'), (5,3,85,'Semester 1','2025-2026'), (5,4,87,'Semester 1','2025-2026'), (5,5,90,'Semester 1','2025-2026'),
(6,1,76,'Semester 1','2025-2026'), (6,2,80,'Semester 1','2025-2026'), (6,3,74,'Semester 1','2025-2026'), (6,4,78,'Semester 1','2025-2026'), (6,5,81,'Semester 1','2025-2026'),
(7,1,62,'Semester 1','2025-2026'), (7,2,66,'Semester 1','2025-2026'), (7,3,60,'Semester 1','2025-2026'), (7,4,64,'Semester 1','2025-2026'), (7,5,63,'Semester 1','2025-2026'),
(8,1,45,'Semester 1','2025-2026'), (8,2,40,'Semester 1','2025-2026'), (8,3,52,'Semester 1','2025-2026'), (8,4,48,'Semester 1','2025-2026'), (8,5,38,'Semester 1','2025-2026');