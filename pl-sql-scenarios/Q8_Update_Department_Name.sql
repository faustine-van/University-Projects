-- Q8: View all departments, allow user to rename one by ID, then show updated list.

SET SERVEROUTPUT ON;
DECLARE
   selected_id Dep_26919_2025.department_id%TYPE := &enter_department_id;
   new_name    Dep_26919_2025.department_name%TYPE := '&enter_new_department_name';
BEGIN
   -- Display current departments
   DBMS_OUTPUT.PUT_LINE('--- Current Departments ---');
   FOR dept IN (SELECT department_id, department_name FROM Dep_26919_2025) LOOP
      DBMS_OUTPUT.PUT_LINE('ID: ' || dept.department_id || ' | Name: ' || dept.department_name);
   END LOOP;

   -- Update selected department name
   UPDATE Dep_26919_2025
   SET department_name = new_name
   WHERE department_id = selected_id;

   DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Updated Departments ---');
   FOR dept IN (SELECT department_id, department_name FROM Dep_26919_2025) LOOP
      DBMS_OUTPUT.PUT_LINE('ID: ' || dept.department_id || ' | Name: ' || dept.department_name);
   END LOOP;
END;
/
