-- SQL Recap


-- Q1. You are a part of the HR team and need to retrieve the full
-- name (first and last) and department name of the employee
-- whose ID is 171. Construct a query to get this information.

SELECT (e.first_name || ' ' ||e.last_name) AS Names, d.department_name 
FROM employees e
INNER JOIN departments d
ON e.department_id = d.department_id WHERE employee_id = 171;

-- ✔ Q2. The recruitment team wants to analyze hiring trends.
-- Write a SQL query to list all employees who were hired in May
-- 2006, displaying their employee ID, first name, last name, and
-- hire date, ordered by the hire date.
SELECT * FROM (SELECT * FROM employees WHERE TO_CHAR(hire_date, 'MM-YYYY') = '05-2006') ORDER BY hire_date;
SELECT * FROM employees WHERE TO_CHAR(hire_date, 'MM-YYYY') = '05-2006' ORDER BY hire_date;

-- Q3. The finance department is reviewing salary structures. Write a query to list the names and salaries of employees earning 
-- between $5,000 and $5,999.

SELECT (first_name || ' ' ||last_name) AS Names, salary FROM employees WHERE salary BETWEEN 5000 AND 5999;

-- Q4. As part of a reporting task, you’ve been asked to list
-- employees along with their manager’s name. Specifically, find all
-- employees whose manager’s last name includes the substring
-- ‘Haan’. Display employee ID, employee name, and manager name.

SELECT e.employee_id, (e.first_name || ' ' ||e.last_name) AS employee_name, (m.first_name || ' ' ||m.last_name)  AS manager_name
FROM employees e
LEFT JOIN employees m
ON e.manager_id = m.employee_id WHERE m.last_name LIKE '%Haan%';


-- Q5. Your task is to identify the employee(s) who receive the
-- highest salary in the company. Write a query that displays their
-- first name, last name, and salary.

SELECT * FROM employees  ORDER BY salary DESC FETCH FIRST 1 ROWS WITH TIES;
SELECT * FROM employees WHERE salary = (SELECT MAX(salary) FROM employees);

-- ✔ Q5. List all employees (Names) with their department names.
SELECT (e.first_name || ' ' ||e.last_name) AS Names, d.department_name from employees e
LEFT JOIN departments d ON e.department_id = d.department_id;

-- 6. Display employees along with their job title and department name. LEFT JOIN OR INNER JOIN
SELECT e.first_name, e.last_name, d.department_name, j.job_title FROM employees e 
LEFT JOIN departments d ON e.department_id = d.department_id LEFT JOIN jobs j ON e.job_id = j.job_id;

-- ✔ Q7. List all departments along with their location city and country.
-- Typical Business Scenario: In most well-designed HR databases:
-- A department wouldn't exist without a location
-- A location wouldn't exist without a country
SELECT d.department_name, l.city, c.country_name FROM departments d INNER JOIN locations l ON d.location_id = l.location_id 
INNER JOIN countries c ON  l.country_id = c.country_id;


-- ❌ ✔ Q8. Show all employees and their previous job titles (if any).  
SELECT e.*, j.job_title AS prevoius_job  FROM employees e LEFT JOIN job_history jh ON e.employee_id = jh.employee_id
LEFT JOIN jobs j ON jh.job_id = j.job_id;


-- ✔ Q9. List job history details with employee names and job titles (Full
-- Name, Start & End date, Department_Name, Job_title).
SELECT (e.first_name||' '|| e.last_name) AS Fullname, jh.start_date, jh.end_date, d.department_name, j.job_title from job_history jh
JOIN employees e ON e.employee_id = jh.employee_id
LEFT JOIN jobs j ON jh.job_id = j.job_id
LEFT JOIN departments d ON jh.department_id = d.department_id;

-- ✔ Q10. Find all employees working in departments located in the
-- United States.

SELECT e.* FROM employees e  
JOIN departments d ON e.department_id = d.department_id
JOIN locations l ON d.location_id = l.location_id
JOIN countries c ON l.country_id = c.country_id WHERE c.country_name='United States of America';

-- Q12. Show the number of employees in each department.
SELECT department_id, COUNT(*) AS numberOfEmployees FROM employees GROUP BY department_id ;

-- Q13. Display each region and the countries within it.
SELECT r.region_id, r.region_name, c.country_name FROM regions r 
JOIN countries c ON r.region_id = c.region_id ORDER BY r.region_name, c.country_name; 
-- Q14. List employees whose salary is above the department average.
SELECT e.employee_id, e.first_name, e.last_name, e.salary, e.department_id
FROM employees e
WHERE e.salary > (
    SELECT AVG(e2.salary)
    FROM employees e2
    WHERE e2.department_id = e.department_id
);

-- Q15. Display employees who earn more than the average salary.
SELECT * FROM employees WHERE salay > (SELECT AVG(salary) FROM employees);

-- Q16 = Q5

-- Q17. List departments that have no employees.
SELECT d.department_id, d.department_name FROM departments d
LEFT JOIN employees e ON d.department_id = e.department_id
GROUP BY d.department_id, d.department_name
HAVING COUNT(e.employee_id) = 0;

-- Q18. Show employees who work in the same job as employee 141.
SELECT * FROM employees WHERE job_id = (SELECT job_id FROM employees WHERE employee_id = 141);

-- Q19. Display job titles for which no employee has ever worked.
SELECT ob_title FROM jobs WHERE job_id NOT IN(SELECT DISTINCT job_id FROM employees);
SELECT j.job_title FROM jobs j LEFT JOIN employees e ON j.job_id = e.job_id WHERE job_id IS NULL;

-- Q20. List employees who have never changed their job.
SELECT * FROM employees  WHERE employee_id NOT IN (SELECT employee_id FROM job_history);

-- Q21. Show the names of departments that are located in the region named 'Europe'.
SELECT d.department_name FROM  departments d JOIN locations l ON d.location_id = l.location_id
JOIN countries c ON l.country_id = c.country_id JOIN regions r ON c.region_id = r.region_id WHERE region_name = 'Europe';

-- Q22. Display the cities where more than 2 departments are located.
SELECT l.city, COUNT(d.department_id) AS department_count
FROM departments d
JOIN locations l ON d.location_id = l.location_id
GROUP BY l.city
HAVING COUNT(d.department_id) > 2;

-- Q23. List employees whose salary is greater than the salary of employee 103.
SELECT e.* FROM employees WHERE salary > (SELECT salary FROM Employees WHERE employee_id = 103);

-- Q24. Show departments where the maximum salary is greater than $10,000,
SELECT department_id, MAX(salary) AS max_salary
FROM employees
GROUP BY department_id
HAVING MAX(salary) > 10000;


