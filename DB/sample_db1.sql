--
-- Sample DB for a company whose hours of operation are Monday to Sunday,
-- 8 AM to 10 PM.
--

-- Employees --
INSERT INTO employees (name, password_hash, username, max_hours_per_day) 
values ('Employee A', 'a', 'a', 7);
INSERT INTO employees (name, password_hash, username, max_hours_per_day) 
values ('Employee B', 'b', 'b', 12);
INSERT INTO employees (name, password_hash, username, max_hours_per_day) 
values ('Employee C', 'c', 'c', 10);
INSERT INTO employees (name, password_hash, username, max_hours_per_day) 
values ('Employee D', 'd', 'd', 9);
INSERT INTO employees (name, password_hash, username, max_hours_per_day) 
values ('Employee E', 'e', 'e', 4);
INSERT INTO employees (name, password_hash, username, max_hours_per_day) 
values ('Employee F', 'f', 'f', 6);
INSERT INTO employees (name, password_hash, username, max_hours_per_day) 
values ('Employee G', 'g', 'g', 8);
INSERT INTO employees (name, password_hash, username, max_hours_per_day) 
values ('Employee H', 'h', 'h', 8);
INSERT INTO employees (name, password_hash, username, max_hours_per_day) 
values ('Employee I', 'i', 'i', 10);

-- Availabilities for Employee A --
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (0, 8, 15, 0, 1);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (1, 8, 14, 0, 1);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (4, 10, 22, 0, 1);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (5, 10, 22, 0, 1);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (6, 8, 12, 0, 1);

-- Availabilities for Employee B --
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (0, 8, 22, 0, 2);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (1, 10, 22, 0, 2);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (2, 10, 22, 0, 2);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (3, 8, 22, 0, 2);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (4, 8, 20, 0, 2);

-- Availabilities for Employee C --
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (1, 8, 20, 0, 3);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (2, 10, 20, 0, 3);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (3, 10, 20, 0, 3);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (4, 8, 20, 0, 3);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (5, 8, 22, 0, 3);

-- Availabilities for Employee D --
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (2, 8, 12, 0, 4);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (3, 8, 12, 0, 4);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (4, 12, 22, 0, 4);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (5, 8, 22, 0, 4);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (6, 8, 22, 0, 4);

-- Availabilities for Employee E --
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (0, 12, 22, 0, 5);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (1, 12, 22, 0, 5);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (2, 12, 22, 0, 5);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (6, 8, 22, 0, 5);

-- Availabilities for Employee F --
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (0, 12, 22, 0, 6);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (2, 12, 22, 0, 6);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (4, 12, 22, 0, 6);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (6, 12, 22, 0, 6);

-- Availabilities for Employee G --
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (0, 8, 10, 0, 7);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (1, 8, 10, 0, 7);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (2, 12, 22, 0, 7);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (6, 8, 12, 0, 7);

-- Availabilities for Employee H --
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (0, 9, 19, 0, 8);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (1, 9, 19, 0, 8);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (2, 9, 19, 0, 8);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (5, 9, 19, 0, 8);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (6, 9, 19, 0, 8);

-- Availabilities for Employee I --
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (4, 8, 22, 0, 9);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (5, 8, 22, 0, 9);
INSERT INTO time_periods (day, start_hour, end_hour, type, employee_id)
values (6, 8, 22, 0, 9);
