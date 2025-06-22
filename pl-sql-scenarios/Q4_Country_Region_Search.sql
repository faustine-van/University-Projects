-- Q4: Search employees based on a full or partial country or region name.
-- Input: Country or region name (case-insensitive).
-- Output: Full name, department, country, region.

SET SERVEROUTPUT ON;

DECLARE
   v_input_name VARCHAR2(100) := '&enter_full_or_partial_country_or_region_name';
   v_found BOOLEAN := FALSE;
BEGIN
   FOR idx IN (
      SELECT 
         (e.first_name || ' ' || e.last_name) AS full_name,
         d.department_name,
         c.country_name,
         r.region_name
      FROM Emp_26919_2025 e
      JOIN Dep_26919_2025 d ON e.department_id = d.department_id
      JOIN Loc_26919_2024 l ON d.location_id = l.location_id
      JOIN Ctry_2691a9_2024 c ON l.country_id = c.country_id
      JOIN Reg_26919_2024 r ON c.region_id = r.region_id
      WHERE UPPER(c.country_name) LIKE '%' || UPPER(v_input_name) || '%'
         OR UPPER(r.region_name) LIKE '%' || UPPER(v_input_name) || '%'
   ) LOOP
      DBMS_OUTPUT.PUT_LINE('Full Name: ' || idx.full_name || 
                           ' | Department: ' || idx.department_name || 
                           ' | Country: ' || idx.country_name || 
                           ' | Region: ' || idx.region_name);
      v_found := TRUE;
   END LOOP;

   IF NOT v_found THEN
      DBMS_OUTPUT.PUT_LINE('No records found matching "' || v_input_name || '".');
   END IF;
END;
/
