-- Connect to the correct database
-- (Do this manually in psql if you're not using pgAdmin)
-- \c University

-- Step 1: Create the table if it doesn't exist
CREATE TABLE IF NOT EXISTS students (
  Id VARCHAR(50) PRIMARY KEY,
  LName VARCHAR(20),
  Course VARCHAR(20),
  Country VARCHAR(20)
);

-- Step 2: Insert student records
INSERT INTO students (Id, LName, Course, Country) VALUES
('2011', 'John', 'Programming', 'Rwanda'),
('2012', 'Marry', 'IT', 'Burundi'),
('2014', 'John', 'C++', 'Burundi'),
('2015', 'Peter', 'IT', 'Rwanda');
