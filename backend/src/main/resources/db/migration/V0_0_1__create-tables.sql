CREATE TABLE employees
(
    id            SERIAL  NOT NULL
        CONSTRAINT employees_pkey
            PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    position      VARCHAR(255) NOT NULL,
    salary        INTEGER NOT NULL,
    supervisor_id INTEGER REFERENCES employees
);

ALTER TABLE employees OWNER TO company_manager;



CREATE TABLE teams
(
    id                 SERIAL NOT NULL
        CONSTRAINT teams_pkey
            PRIMARY KEY,
    name                VARCHAR(255) NOT NULL UNIQUE,
    product_owner_id    INTEGER NOT NULL REFERENCES employees,
    project_manager_id  INTEGER NOT NULL REFERENCES employees,
    scrum_master_id     INTEGER NOT NULL REFERENCES employees
);

ALTER TABLE teams OWNER TO company_manager;



CREATE TABLE employees_teams
(
    employee_id INTEGER NOT NULL REFERENCES employees,
    team_id     INTEGER NOT NULL REFERENCES teams,

    CONSTRAINT employees_teams_pkey
        PRIMARY KEY (employee_id, team_id)
);

ALTER TABLE employees_teams OWNER TO company_manager;



