-- Q6: Display each employee and their previous job (if any).
-- Output: Employee full name, previous job title (blank if none).

SET SERVEROUTPUT ON;

BEGIN
   FOR idx IN (
      SELECT 
         (e.first_name || ' ' || e.last_name) AS full_name,
         j.job_title AS previous_job
      FROM Emp_26919_2025 e
      LEFT JOIN JHisto_26919_2025 jh ON e.employee_id = jh.employee_id
      LEFT JOIN Job_26919_2025 j ON jh.job_id = j.job_id
   ) LOOP
      DBMS_OUTPUT.PUT_LINE('Full Name: ' || idx.full_name || 
                           ' | Previous Job: ' || NVL(idx.previous_job, 'No previous job'));
   END LOOP;
END;
/
