-- 🔧 SETUP SCRIPT: Clone Oracle HR Sample Schema into Custom-Named Virtual Tables
-- Author: Muhayemariya Faustine
-- Description: Creates working copies of HR tables for educational use

-- Drop existing tables if they exist (avoiding constraint issues)
DROP TABLE JHisto_26919_2025 CASCADE CONSTRAINTS;
DROP TABLE Emp_26919_2025 CASCADE CONSTRAINTS;
DROP TABLE Dep_26919_2025 CASCADE CONSTRAINTS;
DROP TABLE Loc_26919_2024 CASCADE CONSTRAINTS;
DROP TABLE Ctry_2691a9_2024 CASCADE CONSTRAINTS;
DROP TABLE Reg_26919_2024 CASCADE CONSTRAINTS;
DROP TABLE Job_26919_2025 CASCADE CONSTRAINTS;

-- Create virtual copies of Oracle HR tables
CREATE TABLE Emp_26919_2025 AS SELECT * FROM employees;
CREATE TABLE Reg_26919_2024 AS SELECT * FROM regions;
CREATE TABLE Loc_26919_2024 AS SELECT * FROM locations;
CREATE TABLE Ctry_2691a9_2024 AS SELECT * FROM countries;
CREATE TABLE Dep_26919_2025 AS SELECT * FROM departments;
CREATE TABLE Job_26919_2025 AS SELECT * FROM jobs;
CREATE TABLE JHisto_26919_2025 AS SELECT * FROM job_history;

-- Optional: Show confirmation
BEGIN
   DBMS_OUTPUT.PUT_LINE('✅ All virtual HR tables created successfully.');
END;
/
