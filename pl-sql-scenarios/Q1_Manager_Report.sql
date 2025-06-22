-- Q1. Display employees with their respective managers (or 'No Manager' if not assigned)

SET SERVEROUTPUT ON;

BEGIN
   FOR idx IN (
      SELECT 
         e.employee_id AS empID, 
         (e.first_name || ' ' || e.last_name) AS employee_name,
         e.manager_id AS managerID, 
         (m.first_name || ' ' || m.last_name) AS manager_name
      FROM Emp_26919_2025 e 
      LEFT JOIN Emp_26919_2025 m ON e.manager_id = m.employee_id
   ) LOOP
      IF idx.managerID IS NULL THEN
         DBMS_OUTPUT.PUT_LINE('Employee_ID: ' || idx.empID ||
                              ' | Name: ' || idx.employee_name ||
                              ' | Manager_ID: NULL' ||
                              ' | Manager_Name: No Manager');
      ELSE
         DBMS_OUTPUT.PUT_LINE('Employee_ID: ' || idx.empID ||
                              ' | Name: ' || idx.employee_name ||
                              ' | Manager_ID: ' || idx.managerID ||
                              ' | Manager_Name: ' || idx.manager_name);
      END IF;
   END LOOP;
END;
/
