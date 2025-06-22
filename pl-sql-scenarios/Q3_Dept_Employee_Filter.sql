-- Q3: Display all employees working in a specific department.
-- Input: Department name (case-insensitive).
-- Output: Full name, job title, and department name.

SET SERVEROUTPUT ON;

DECLARE 
   v_department_name Dep_26919_2025.department_name%TYPE := '&enter_department_name';
   v_found BOOLEAN := FALSE;
BEGIN
   FOR idx IN (
      SELECT 
         (e.first_name || ' ' || e.last_name) AS full_name,
         j.job_title,
         d.department_name
      FROM Emp_26919_2025 e
      JOIN Dep_26919_2025 d ON e.department_id = d.department_id
      JOIN Job_26919_2025 j ON e.job_id = j.job_id
      WHERE UPPER(d.department_name) = UPPER(v_department_name)
   ) LOOP
      DBMS_OUTPUT.PUT_LINE('Employee: ' || idx.full_name || 
                           ' | Job Title: ' || idx.job_title || 
                           ' | Department: ' || idx.department_name);
      v_found := TRUE;
   END LOOP;

   IF NOT v_found THEN
      DBMS_OUTPUT.PUT_LINE('No employees found in department "' || v_department_name || '".');
   END IF;
END;
/
