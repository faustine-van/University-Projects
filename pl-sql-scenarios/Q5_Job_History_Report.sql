-- Q5: Report job history for employees who have changed roles.
-- Output: Full name, department, job title, start and end dates of previous positions.

SET SERVEROUTPUT ON;

BEGIN
   FOR idx IN (
      SELECT 
         (e.first_name || ' ' || e.last_name) AS full_name,
         d.department_name,
         j.job_title,
         jh.start_date,
         jh.end_date
      FROM JHisto_26919_2025 jh
      JOIN Emp_26919_2025 e ON e.employee_id = jh.employee_id
      LEFT JOIN Dep_26919_2025 d ON jh.department_id = d.department_id
      LEFT JOIN Job_26919_2025 j ON jh.job_id = j.job_id
   ) LOOP
      DBMS_OUTPUT.PUT_LINE('Full Name: ' || idx.full_name || 
                           ' | Department: ' || idx.department_name || 
                           ' | Job Title: ' || idx.job_title || 
                           ' | From: ' || TO_CHAR(idx.start_date, 'DD-Mon-YYYY') || 
                           ' To: ' || TO_CHAR(idx.end_date, 'DD-Mon-YYYY'));
   END LOOP;
END;
/
