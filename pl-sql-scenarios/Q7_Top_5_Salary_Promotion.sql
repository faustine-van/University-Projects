-- Q7: Promote top 5 highest earners above average salary by prefixing "Senior" to job titles.
-- Do NOT commit; allow user to choose COMMIT or ROLLBACK.

SET SERVEROUTPUT ON;
DECLARE
   commit_rollback VARCHAR2(10);
BEGIN
   -- Step 1: Display and update top 5 employees
   FOR idx IN (
      SELECT e.employee_id, (e.first_name || ' ' || e.last_name) AS full_name,
             e.salary, j.job_id, j.job_title
      FROM Emp_26919_2025 e
      JOIN Job_26919_2025 j ON e.job_id = j.job_id
      WHERE e.salary > (SELECT AVG(salary) FROM Emp_26919_2025)
      ORDER BY e.salary DESC
      FETCH FIRST 5 ROWS ONLY
   ) LOOP
      DBMS_OUTPUT.PUT_LINE('Before: ' || idx.full_name || ' | Job: ' || idx.job_title);

      UPDATE Job_26919_2025
      SET job_title = 'Senior ' || idx.job_title
      WHERE job_id = idx.job_id;
   END LOOP;

   DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- After Update ---');
   FOR idx IN (
      SELECT e.employee_id, (e.first_name || ' ' || e.last_name) AS full_name,
             j.job_title
      FROM Emp_26919_2025 e
      JOIN Job_26919_2025 j ON e.job_id = j.job_id
      WHERE e.salary > (SELECT AVG(salary) FROM Emp_26919_2025)
      ORDER BY e.salary DESC
      FETCH FIRST 5 ROWS ONLY
   ) LOOP
      DBMS_OUTPUT.PUT_LINE('Updated: ' || idx.full_name || ' | New Job Title: ' || idx.job_title);
   END LOOP;

   -- Step 2: Ask user for commit/rollback
   commit_rollback := '&commit_or_rollback';
   IF LOWER(commit_rollback) = 'commit' THEN
      COMMIT;
      DBMS_OUTPUT.PUT_LINE('Changes committed.');
   ELSE
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('Changes rolled back.');
   END IF;
END;
/
