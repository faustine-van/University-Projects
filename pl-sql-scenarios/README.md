# PL/SQL Scenario-Based Exercises

Welcome to a curated collection of **10 practical PL/SQL programs**—each crafted to solve real-world HR challenges using the Oracle **HR sample schema**. Whether you’re a student, developer, or database enthusiast, these exercises will help you practice PL/SQL concepts in realistic scenarios.

> Note: All scripts use custom-named tables cloned from the HR schema (e.g., `Emp_26919_2025`, `Dep_26919_2025`, etc.).

---

## Educational Disclaimer

These exercises are for educational use only and were developed based on scenarios provided by my university lecturer. The HR schema referenced is a standard Oracle sample schema used for learning purposes.

---

## Folder Structure

```
PL-SQL-Scenarios/
├── 01_SETUP_HR_Virtual_Tables.sql
├── Q1_Manager_Report.sql
├── Q2_Employee_Manager_Lookup.sql
├── Q3_Dept_Employee_Filter.sql
├── Q4_Country_Region_Search.sql
├── Q5_Job_History_Report.sql
├── Q6_Previous_Job_Title.sql
├── Q7_Top_5_Salary_Promotion.sql
├── Q8_Update_Department_Name.sql
├── Q9_Prefix_Senior_Title.sql
├── Q10_Transaction_Workflow.sql
└── README.md
```

Q2_Employee_Manager_Lookup.sql
Q3_Dept_Employee_Filter.sql
Q4_Country_Region_Search.sql
Q5_Job_History_Report.sql
Q6_Previous_Job_Title.sql
Q7_Top_5_Salary_Promotion.sql
Q8_Update_Department_Name.sql
Q9_Prefix_Senior_Title.sql
Q10_Transaction_Workflow.sql
---

## Scenario Overviews

Each `.sql` script is fully documented, features a runnable PL/SQL block, and tackles a distinct HR scenario:

### Q1 – Manager Report
**Goal:** List all employees with their managers’ names (or 'No Manager' if not applicable).  
_File: Q1_Manager_Report.sql_

---

### Q2 – Employee Lookup by ID
**Goal:** For a given employee ID, display their manager’s and department’s names, handling missing assignments gracefully.  
_File: Q2_Employee_Manager_Lookup.sql_

---

### Q3 – Filter by Department Name
**Goal:** Retrieve all employees working in a department of your choice (provided via input), showing their names and job titles.  
_File: Q3_Dept_Employee_Filter.sql_

---

### Q4 – Filter by Country or Region (Partial Match)
**Goal:** Find employees based on a (partial) country or region name input; show their full name, department, country, and region.  
_File: Q4_Country_Region_Search.sql_

---

### Q5 – Job History Report
**Goal:** Report all job changes for employees, including names, department, job title, and duration.  
_File: Q5_Job_History_Report.sql_

---

### Q6 – Previous Job Titles
**Goal:** Display each employee’s name and their previous job title if they have one; show blank if none.  
_File: Q6_Previous_Job_Title.sql_

---

### Q7 – Top 5 High Performers (No Commit)
**Goal:** Identify and (temporarily) promote the top 5 employees earning above average, prefixing their job titles with 'Senior' (no commit until user decides).  
_File: Q7_Top_5_Salary_Promotion.sql_

---

### Q8 – Department Name Editor
**Goal:** Let user view, select, and rename a department, then confirm the update with a refreshed list.  
_File: Q8_Update_Department_Name.sql_

---

### Q9 – Promote All Above-Average Earners
**Goal:** Mass-promote all employees earning above average by prefixing their job titles with 'Senior' (logic only; role unchanged).  
_File: Q9_Prefix_Senior_Title.sql_

---

### Q10 – Transaction Test for Kevin's Team
**Goal:** Demonstrate transactional control: display, delete, confirm deletion, rollback, and confirm restoration—all for employees managed by Kevin.  
_File: Q10_Transaction_Workflow.sql_

---

## Key Concepts Practiced

- Advanced **JOINs** across HR-related tables
- Use of `DBMS_OUTPUT.PUT_LINE` for clean output
- **Loops** for processing result sets
- **Substitution variables** (e.g., `&`) for interactive queries
- **Transaction control:** `COMMIT` and `ROLLBACK` illustrated

---

## Example Use Cases

- Map employee-manager relationships
- Find staff by department, country, or region
- Bulk-update job titles based on performance
- Safely test deletions with rollback

---

> **All code is reviewed for clarity, maintainability, and best practices.**  
> Feel free to fork, adapt, or extend these scenarios for your own learning or projects!

---

## Author

**[Muhayemariya Faustine](https://github.com/faustine-van)**  
_Software Engineer & Database Enthusiast | Exploring real-world scenarios through code_  

---

## License

This project is licensed under the [MIT License](LICENSE).
