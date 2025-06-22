-- Q2: Lookup an employee's manager and department using their ID.
-- If either is not assigned, display a clear message.

SET SERVEROUTPUT ON;

DECLARE
   v_emp_id Emp_26919_2025.employee_id%TYPE := &enter_employee_id;
BEGIN
   FOR idx IN (
      SELECT 
         e.employee_id AS empID,
         (e.first_name || ' ' || e.last_name) AS employee_name,
         m.employee_id AS mgrID,
         (m.first_name || ' ' || m.last_name) AS manager_name,
         d.department_name
      FROM Emp_26919_2025 e
      LEFT JOIN Emp_26919_2025 m ON e.manager_id = m.employee_id
      LEFT JOIN Dep_26919_2025 d ON e.department_id = d.department_id
      WHERE e.employee_id = v_emp_id
   ) LOOP
      IF idx.mgrID IS NULL AND idx.department_name IS NULL THEN
         DBMS_OUTPUT.PUT_LINE('Employee: ' || idx.employee_name || 
                              ' | Manager: No Manager' || 
                              ' | Department: No Department');
      ELSIF idx.mgrID IS NULL THEN
         DBMS_OUTPUT.PUT_LINE('Employee: ' || idx.employee_name || 
                              ' | Manager: No Manager' || 
                              ' | Department: ' || idx.department_name);
      ELSIF idx.department_name IS NULL THEN
         DBMS_OUTPUT.PUT_LINE('Employee: ' || idx.employee_name || 
                              ' | Manager: ' || idx.manager_name || 
                              ' | Department: No Department');
      ELSE
         DBMS_OUTPUT.PUT_LINE('Employee: ' || idx.employee_name || 
                              ' | Manager: ' || idx.manager_name || 
                              ' | Department: ' || idx.department_name);
      END IF;
   END LOOP;
END;
/
