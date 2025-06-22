-- Q9: Promote all above-average earners by prefixing "Senior" to their job titles (once per job_id).

SET SERVEROUTPUT ON;
DECLARE
   v_avg_salary NUMBER;
BEGIN
   -- Step 1: Calculate average salary
   SELECT AVG(salary) INTO v_avg_salary FROM Emp_26919_2025;

   -- Step 2: Find distinct job_ids of high earners, and update titles
   FOR idx IN (
      SELECT DISTINCT e.job_id, j.job_title
      FROM Emp_26919_2025 e
      JOIN Job_26919_2025 j ON e.job_id = j.job_id
      WHERE e.salary > v_avg_salary
      AND UPPER(j.job_title) NOT LIKE 'SENIOR%'
   ) LOOP
      UPDATE Job_26919_2025
      SET job_title = SUBSTR('Senior ' || idx.job_title, 1, 35)
      WHERE job_id = idx.job_id;

      DBMS_OUTPUT.PUT_LINE('Updated Job ID: ' || idx.job_id || ' to "Senior ' || idx.job_title || '"');
   END LOOP;
END;
/
