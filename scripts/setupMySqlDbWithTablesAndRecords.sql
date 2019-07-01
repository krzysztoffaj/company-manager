DROP DATABASE IF EXISTS company_manager;

CREATE DATABASE company_manager;

USE company_manager;

CREATE TABLE employee
(
  employee_id       INT NOT NULL AUTO_INCREMENT,
  first_name        NVARCHAR(255) NOT NULL,
  last_name         NVARCHAR(255) NOT NULL,
  position          ENUM('PO','PM','Scrummaster','DevOps','Developer','Tester','Analyst') NOT NULL,
  salary            DOUBLE(12,2) NOT NULL,
  supervisor_id     INT,

  PRIMARY KEY (employee_id)
);

CREATE TABLE team
(
  team_id         INT NOT NULL AUTO_INCREMENT,
  name            NVARCHAR(255) NOT NULL,
  pm_id           INT,
  po_id           INT,
  scrummaster_id  INT,


  PRIMARY KEY (team_id)
);

CREATE TABLE employee_team
(
  employee_id   INT NOT NULL,
  team_id       INT NOT NULL,

  PRIMARY KEY (employee_id, team_id),

  FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
  FOREIGN KEY (team_id)     REFERENCES team (team_id)
);


-- Set up values for employee table

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Mack', 'Knife', 'PM', 100000.00, NULL);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Louis', 'Crosby', 'PM', 89500.99, NULL);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Anna', 'Frąckowiak', 'PM', 95000.75, NULL);


INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Walter', 'White', 'PO', 50000.50, 1);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Frank', 'Armstrong', 'PO', 48750.50, 2);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Jessica', 'Elliott', 'PO', 35000.95, 3);


INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Łukasz', 'Podbródek', 'Scrummaster', 15000.15, 1);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Bing', 'Martin', 'Scrummaster', 17000.00, 2);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Jose', 'Morales', 'Scrummaster', 12500.19, 3);


INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Nat', 'Darin', 'Developer', 15000.00, 3);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Joanna', 'Ciemna', 'Developer', 10000.10, 1);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Jarosław', 'Hirnyj', 'Developer', 12000.25, 2);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('John', 'Campbell', 'Developer', 7800.25, 1);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('John', 'Jones', 'Developer', 9500.00, 2);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Jacob', 'Kowalski', 'Developer', 11500.50, 3);


INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Jack', 'Hill', 'DevOps', 8000.80, 1);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Dean', 'King', 'DevOps', 120000.00, 2);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Aleksandra', 'Kłosińska', 'DevOps', 5222.00, 3);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Thomas', 'Martin', 'DevOps', 4850.00, 1);


INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Bobby', 'Sinatra', 'Tester', 5000.99, 1);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Hanna', 'Watson', 'Tester', 6000.74, 2);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Adam', 'Żmuda', 'Tester', 4500.00, 3);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Walter', 'Gallagher', 'Tester', 5000.00, 1);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('John', 'Ibelino', 'Tester', 4250.00, 2);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Anastazja', 'Gerić', 'Tester', 3900.10, 3);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Paul', 'Richards', 'Tester', 5222.22, 1);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Piotr', 'Krasiński', 'Tester', 4579.12, 2);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Anna', 'Szewczenko', 'Tester', 6350.95, 3);


INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Mack', 'Knife', 'Analyst', 6000.10, 1);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Daniel', 'Grochowski', 'Analyst', 4000.00, 2);

INSERT INTO employee (first_name, last_name, position, salary, supervisor_id)
VALUES ('Jennifer', 'Parker', 'Analyst', 3000.00, 3);



-- Set up values for team table

INSERT INTO team (name, pm_id, po_id, scrummaster_id)
VALUES ('Technical Architecture', 1, 4, 7);

INSERT INTO team (name, pm_id, po_id, scrummaster_id)
VALUES ('Mobile', 2, 6, 9);

INSERT INTO team (name, pm_id, po_id, scrummaster_id)
VALUES ('Customer Relationship Management', 3, 5, 8);



-- Set up values for team table

-- PM

INSERT INTO employee_team (employee_id, team_id)
VALUES (1, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (2, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (3, 3);


-- PO

INSERT INTO employee_team (employee_id, team_id)
VALUES (4, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (5, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (6, 3);


-- Scrummaster

INSERT INTO employee_team (employee_id, team_id)
VALUES (7, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (8, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (9, 3);


-- Developer

INSERT INTO employee_team (employee_id, team_id)
VALUES (10, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (10, 3);

INSERT INTO employee_team (employee_id, team_id)
VALUES (11, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (12, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (13, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (14, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (14, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (15, 3);


-- DevOps

INSERT INTO employee_team (employee_id, team_id)
VALUES (16, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (17, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (18, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (19, 3);


-- Tester

INSERT INTO employee_team (employee_id, team_id)
VALUES (20, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (20, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (21, 3);

INSERT INTO employee_team (employee_id, team_id)
VALUES (22, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (23, 3);

INSERT INTO employee_team (employee_id, team_id)
VALUES (24, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (25, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (25, 3);

INSERT INTO employee_team (employee_id, team_id)
VALUES (26, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (26, 3);

INSERT INTO employee_team (employee_id, team_id)
VALUES (27, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (27, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (28, 2);


-- Analyst

INSERT INTO employee_team (employee_id, team_id)
VALUES (29, 1);

INSERT INTO employee_team (employee_id, team_id)
VALUES (30, 2);

INSERT INTO employee_team (employee_id, team_id)
VALUES (31, 3);
