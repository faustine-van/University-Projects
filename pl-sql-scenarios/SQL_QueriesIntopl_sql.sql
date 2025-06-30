-- Q15: List employees with above average salary
DECLARE
    v_average employees.salary%TYPE;
    v_found_flag BOOLEAN := FALSE;
BEGIN
    SELECT AVG(salary) INTO v_average FROM employees;
    FOR rec IN (
        SELECT employee_id, (first_name || ' ' || last_name) AS Names, salary
        FROM employees
    ) LOOP
        IF rec.salary > v_average THEN
            DBMS_OUTPUT.PUT_LINE('Employee_ID: ' || rec.employee_id ||
                                 ' Employee_NAME: ' || rec.Names ||
                                 ' Salary:' || rec.salary);
            v_found_flag := TRUE;
        END IF;
    END LOOP;
    IF NOT v_found_flag THEN
        DBMS_OUTPUT.PUT_LINE('No employees have above average salary');  
    END IF;
END;
/
-- Q16: List employee(s) with maximum salary
DECLARE
    v_max_salary employees.salary%TYPE;
BEGIN
    SELECT MAX(salary) INTO v_max_salary FROM employees;
    IF v_max_salary IS NULL THEN
        DBMS_OUTPUT.PUT_LINE('No employees have a salary value.');
    ELSE
        FOR rec IN (
            SELECT employee_id, (first_name || ' ' || last_name) AS Names, salary
            FROM employees
        ) LOOP
            IF rec.salary = v_max_salary THEN
                DBMS_OUTPUT.PUT_LINE('Employee_ID: ' || rec.employee_id ||
                                     ' Employee_NAME: ' || rec.Names ||
                                     ' Salary:' || rec.salary);
            END IF;
        END LOOP;
    END IF;
END;
/
-- Q17: List departments with no employees
DECLARE
    no_empty_departments EXCEPTION;
    v_found_flag BOOLEAN := FALSE;
BEGIN
    FOR rec IN (
        SELECT d.department_id, d.department_name
        FROM departments d
        LEFT JOIN employees e ON d.department_id = e.department_id
        GROUP BY d.department_id, d.department_name
        HAVING COUNT(e.employee_id) = 0
    ) LOOP
        v_found_flag := TRUE;
        DBMS_OUTPUT.PUT_LINE('Department name: ' || rec.department_name);
    END LOOP;
    IF NOT v_found_flag THEN
        RAISE no_empty_departments;
    END IF;
EXCEPTION
    WHEN no_empty_departments THEN
        DBMS_OUTPUT.PUT_LINE('All departments have at least one employee.');
END;
/
-- Q18: List employees with same job as employee 141
DECLARE
    v_job_id employees.job_id%TYPE;
BEGIN
    SELECT job_id INTO v_job_id FROM employees WHERE employee_id = 141;
    FOR rec IN (
        SELECT employee_id, first_name, last_name, job_id FROM employees
    ) LOOP
        IF rec.job_id = v_job_id THEN
            DBMS_OUTPUT.PUT_LINE('ID: ' || rec.employee_id ||
                                 ' | Name: ' || rec.first_name || ' ' || rec.last_name ||
                                 ' | Job: ' || rec.job_id);
        END IF;
    END LOOP;
END;
/
-- Q19: List jobs not assigned to any employee
SET SERVEROUTPUT ON;
DECLARE
    v_found_flag BOOLEAN := FALSE;
BEGIN
    FOR rec IN (
        SELECT job_title
        FROM jobs
        WHERE job_id NOT IN (SELECT DISTINCT job_id FROM employees)
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Unused Job Title: ' || rec.job_title);
        v_found_flag := TRUE;
    END LOOP;
    IF NOT v_found_flag THEN
        DBMS_OUTPUT.PUT_LINE('All job titles assigned to at least one employee.');
    END IF;
END;
/
-- Q20: List employees who never changed jobs
SET SERVEROUTPUT ON;
DECLARE
    v_found_flag BOOLEAN := FALSE;
BEGIN
    FOR rec IN (
        SELECT * FROM employees
        WHERE employee_id NOT IN (SELECT employee_id FROM job_history)
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || rec.employee_id ||
                             ' | Name: ' || rec.first_name || ' ' || rec.last_name);
        v_found_flag := TRUE;
    END LOOP;
    IF NOT v_found_flag THEN
        DBMS_OUTPUT.PUT_LINE('No employee found who has never changed job.');
    END IF;
END;
/
-- Q21: List departments located in Europe
SET SERVEROUTPUT ON;
BEGIN
    FOR rec IN (
        SELECT d.department_name, r.region_name
        FROM departments d
        JOIN locations l ON d.location_id = l.location_id
        JOIN countries c ON l.country_id = c.country_id
        JOIN regions r ON c.region_id = r.region_id
    ) LOOP
        IF rec.region_name = 'Europe' THEN
            DBMS_OUTPUT.PUT_LINE('Department in Europe: ' || rec.department_name);
        END IF;
    END LOOP;
END;
/
-- Q22: List cities with more than 2 departments
SET SERVEROUTPUT ON;
DECLARE
    v_count NUMBER := 0;
BEGIN
    FOR rec IN (
        SELECT l.city, COUNT(d.department_id) AS department_count
        FROM departments d
        JOIN locations l ON d.location_id = l.location_id
        GROUP BY l.city
        HAVING COUNT(d.department_id) > 2
    ) LOOP
        v_count := v_count + 1;
        DBMS_OUTPUT.PUT_LINE('City ' || rec.city ||
                             ' have ' || rec.department_count || ' departments');
    END LOOP;
    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No city have more than 2 departments');
    END IF;
END;
/
-- Q23: List employees with higher salary than employee 103
SET SERVEROUTPUT ON;
DECLARE
    v_salary employees.salary%TYPE;
BEGIN
    SELECT salary INTO v_salary FROM employees WHERE employee_id = 103;
    FOR rec IN (
        SELECT * FROM employees WHERE salary > v_salary
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || rec.employee_id ||
                             ' | Name: ' || rec.first_name || ' ' || rec.last_name ||
                             ' | Salary: ' || rec.salary);
    END LOOP;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Employee with ID 103 not found.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
-- Q24: List departments with max salary > 10000
SET SERVEROUTPUT ON;
BEGIN
    FOR rec IN (
        SELECT department_id, MAX(salary) AS max_salary
        FROM employees
        GROUP BY department_id
        HAVING MAX(salary) > 10000
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Department with ID ' || rec.department_id ||
                             ' have Max Salary ' || rec.max_salary);
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error ' || SQLERRM);
END;
/