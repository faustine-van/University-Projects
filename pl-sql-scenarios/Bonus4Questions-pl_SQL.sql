-- PL/SQL 

-- Q1. You are part of the HR team. Write a PL/SQL block to retrieve the full name (first and last) and department name of the employee 
-- specified by the user. Check if the employee exists with an IF statement, and use exception handling to display a custom message if 
-- the employee ID is not found. Simulate scanning through employee records with a loop, even if searching for just one record.

SET SERVEROUTPUT ON
DECLARE
    v_empID employees.employee_id%TYPE := &enter_employee_id;
    v_names employees.first_name%TYPE;
    v_deptNames departments.department_name%type;
    employee_found_flag BOOLEAN := FALSE; 
    CURSOR empDeptCursor IS
        SELECT e.employee_id, (e.first_name || ' ' ||e.last_name) AS fullname, d.department_name FROM employees e
        LEFT JOIN departments d ON e.department_id = d.department_id;
BEGIN
    for rec IN empDeptCursor LOOP
        IF v_empID = rec.employee_id THEN
        v_names := rec.fullname;
        v_deptNames := rec.department_name;
        employee_found_flag := TRUE;
        EXIT;
        END IF;
    END LOOP;
    IF employee_found_flag THEN
        DBMS_OUTPUT.PUT_LINE(v_names || ' ' || NVL(v_deptNames, 'No department'));
    ELSE
        RAISE_APPLICATION_ERROR(-20001, 'Employee not found.');
    END IF;
    EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


--Q2. The recruitment team aims to analyze hiring trends. Write a PL/SQL block that lists employees hired in a user-specified year and month, 
--displaying their ID, first name, last name, and hire date. Use a loop to iterate through records, include an IF condition to check the 
--hire date, and implement exception handling for unexpected errors.

SET SERVEROUTPUT ON
DECLARE
    v_input VARCHAR2(10) := '&enter_month_year'; -- enter examples like 05-2006
    v_month_year DATE; 
    v_found BOOLEAN := FALSE;
    CURSOR empCursor IS
        SELECT employee_id, first_name, last_name , hire_date FROM employees;
BEGIN
    -- Convert input to first day of the month
    v_month_year := TO_DATE(v_input, 'MM-YYYY');
    FOR rec IN empCursor LOOP
        IF TO_CHAR(v_month_year, 'MM-YYYY') = TO_CHAR(rec.hire_date, 'MM-YYYY') THEN
            DBMS_OUTPUT.PUT_LINE(rec.employee_id||'  '||rec.first_name||' '||rec.last_name||'  '||TO_CHAR(rec.hire_date, 'MM-YYYY'));
            v_found := TRUE; 
        END IF;
    END LOOP;
    IF NOT v_found THEN
        DBMS_OUTPUT.PUT_LINE('No employees hired in ' || TO_CHAR(v_month_year, 'Month YYYY'));
    END IF;
    EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


-- Q13. The finance department is reviewing salary structures. Write a PL/SQL block to display the names and salaries of employees earning 
-- between $5,000 and $5,999. Use a loop to iterate through salary records and an IF condition to filter the salaries. Include exception 
-- handling for data-related errors, such as null values.

SET SERVEROUTPUT ON
DECLARE
    v_found BOOLEAN := FALSE;
    NOT_FOUND EXCEPTION;
    CURSOR empCursor IS
        SELECT (first_name || ' ' ||last_name) AS names , salary FROM employees;
BEGIN
    FOR rec IN empCursor LOOP
        IF rec.salary IS NULL THEN
            DBMS_OUTPUT.PUT_LINE(rec.names || ' has a NULL salary.');
        ELSIF rec.salary >= 5000 AND rec.salary <= 5999 THEN
            DBMS_OUTPUT.PUT_LINE(rec.names||'  '||rec.salary || '$');
            v_found := TRUE; 
        END IF;
    END LOOP;
    IF NOT v_found THEN
        Raise NOT_FOUND;
    END IF;
    EXCEPTION
    WHEN NOT_FOUND THEN
      DBMS_OUTPUT.PUT_LINE('No employees earning between $5,000 and $5,999');  
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


-- Q14. As an HR analyst, generate a report for departments with more than 40 employees, including the department name and employee count. 
-- Use loops for iteration, apply conditional statements to check counts, and implement exception handling for errors.
SET SERVEROUTPUT ON
DECLARE
    v_found BOOLEAN := FALSE;
    NOT_FOUND EXCEPTION;
    CURSOR empCursor IS
        SELECT d.department_name, COUNT(e.employee_id) AS numberOfEmployees FROM departments d  
        JOIN  employees e ON  d.department_id  = e.department_id 
        GROUP BY d.department_name;
BEGIN
    FOR rec IN empCursor LOOP
        IF rec.numberOfEmployees > 40 THEN
            DBMS_OUTPUT.PUT_LINE(rec.department_name ||' ' || rec.numberOfEmployees);
              v_found := TRUE; 
        END IF;
    END LOOP;
    IF NOT v_found THEN
        Raise NOT_FOUND;
    END IF;
    EXCEPTION
    WHEN NOT_FOUND THEN
      DBMS_OUTPUT.PUT_LINE('No department has more than 40 employees');  
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/