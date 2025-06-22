-- Q10: Test transaction flow for employees managed by Kevin (delete and rollback).

SET SERVEROUTPUT ON;
BEGIN
   DBMS_OUTPUT.PUT_LINE('--- Employees Managed by Kevin (Before Deletion) ---');
   FOR idx IN (
      SELECT e.employee_id AS emp_id, (e.first_name || ' ' || e.last_name) AS full_name
      FROM Emp_26919_2025 e
      JOIN Emp_26919_2025 m ON e.manager_id = m.employee_id
      WHERE m.first_name = 'Kevin'
   ) LOOP
      DBMS_OUTPUT.PUT_LINE('ID: ' || idx.emp_id || ' | Name: ' || idx.full_name);
   END LOOP;

   -- Step 2: Delete employees managed by Kevin
   FOR idx IN (
      SELECT e.employee_id AS emp_id
      FROM Emp_26919_2025 e
      JOIN Emp_26919_2025 m ON e.manager_id = m.employee_id
      WHERE m.first_name = 'Kevin'
   ) LOOP
      DELETE FROM Emp_26919_2025 WHERE employee_id = idx.emp_id;
   END LOOP;

   -- Step 3: Confirm deletion
   DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- After Deletion ---');
   FOR idx IN (
      SELECT e.employee_id AS emp_id, (e.first_name || ' ' || e.last_name) AS full_name
      FROM Emp_26919_2025 e
      JOIN Emp_26919_2025 m ON e.manager_id = m.employee_id
      WHERE m.first_name = 'Kevin'
   ) LOOP
      DBMS_OUTPUT.PUT_LINE('ID: ' || idx.emp_id || ' | Name: ' || idx.full_name);
   END LOOP;

   -- Step 4: Rollback changes
   ROLLBACK;

   -- Step 5: Confirm rollback
   DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- After Rollback ---');
   FOR idx IN (
      SELECT e.employee_id AS emp_id, (e.first_name || ' ' || e.last_name) AS full_name
      FROM Emp_26919_2025 e
      JOIN Emp_26919_2025 m ON e.manager_id = m.employee_id
      WHERE m.first_name = 'Kevin'
   ) LOOP
      DBMS_OUTPUT.PUT_LINE('ID: ' || idx.emp_id || ' | Name: ' || idx.full_name);
   END LOOP;
END;
/
