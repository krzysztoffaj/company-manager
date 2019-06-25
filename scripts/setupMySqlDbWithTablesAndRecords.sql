DROP DATABASE IF EXISTS company_manager;

CREATE DATABASE company_manager;

USE company_manager;

CREATE TABLE employee
(
  employee_id   INT NOT NULL AUTO_INCREMENT,
  first_name    NVARCHAR(255) NOT NULL,
  last_name     NVARCHAR(255) NOT NULL,
  position      ENUM('PO','PM','Scrummaster','DevOps','Developer','Tester','Analyst') NOT NULL,
  salary        DOUBLE(12,2) NOT NULL,
  boss_id       INT NOT NULL,
  
  PRIMARY KEY (employee_id)
);

CREATE TABLE team
(
  team_id   INT NOT NULL,
  name      NVARCHAR(255) NOT NULL,
  
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
