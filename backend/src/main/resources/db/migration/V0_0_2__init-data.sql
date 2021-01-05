INSERT INTO employees (first_name, last_name, position, salary, supervisor_id)
VALUES  ('John', 'White', 'PRODUCT_OWNER', 50000, NULL),
        ('James', 'Armstrong', 'PRODUCT_OWNER', 48750, NULL),
        ('Jessica', 'Elliott', 'PRODUCT_OWNER', 35000, NULL),

        ('William', 'King', 'PROJECT_MANAGER', 100000, 1),
        ('Louis', 'Woods', 'PROJECT_MANAGER', 89500, 2),
        ('Anna', 'Lakes', 'PROJECT_MANAGER', 95000, 3),

        ('Lukas', 'Mountains', 'SCRUM_MASTER', 15000, 4),
        ('Martin', 'Johnson', 'SCRUM_MASTER', 17000, 5),
        ('Jennifer', 'Morales', 'SCRUM_MASTER', 12500, 6),

        ('Natalie', 'Jones', 'DEVELOPER', 15000, 1),
        ('Jerry', 'Circ', 'DEVELOPER', 10000, 2),
        ('Johnathan', 'Smith', 'DEVELOPER', 12000, 5),
        ('John', 'Washington', 'DEVELOPER', 7800, 7),
        ('Evelyn', 'Parker', 'DEVELOPER', 9500, 10),
        ('Jacob', 'Blacksmith', 'DEVELOPER', 11500, 11),

        ('Jack', 'Hill', 'DEVOPS_ENGINEER', 8000, 7),
        ('Dean', 'King', 'DEVOPS_ENGINEER', 12000, 8),
        ('Alexandra', 'Thomson', 'DEVOPS_ENGINEER', 5222, 12),
        ('Thomas', 'Wolf', 'DEVOPS_ENGINEER', 4850, 13),

        ('Bruce', 'Edwards', 'TESTER', 5000, 1),
        ('Hanna', 'Watson', 'TESTER', 6000, 2),
        ('Adam', 'Wergton', 'TESTER', 4500, 3),
        ('Walter', 'Gallagher', 'TESTER', 5000, 4),
        ('John', 'Ibelino', 'TESTER', 4250, 6),
        ('Andrew', 'Gerić', 'TESTER', 3900, 3),
        ('Paul', 'Richards', 'TESTER', 5225, 7),
        ('Christopher', 'Steward', 'TESTER', 4580, 10),
        ('Anna', 'Ferguson', 'TESTER', 6350, 15),

        ('Jessica', 'Oceans', 'ANALYST', 6000, 1),
        ('Daniel', 'van Dyke', 'ANALYST', 4000, 2),
        ('Jennifer', 'Parker', 'ANALYST', 3000, 6);



INSERT INTO teams (name, product_owner_id, project_manager_id, scrum_master_id)
VALUES  ('Technical Architecture', 1, 4, 7),
        ('Mobile', 2, 5, 8),
        ('Customer Relationship Management', 3, 6, 9);



INSERT INTO teams_employees (team_id, employee_id)
VALUES  (1, 1),
        (2, 2),
        (3, 3),

        (1, 4),
        (2, 5),
        (3, 6),

        (1, 7),
        (3, 8),
        (2, 9),

        (1, 10),
        (3, 10),
        (2, 11),
        (2, 12),
        (1, 13),
        (1, 14),
        (2, 14),
        (3, 15),

        (1, 16),
        (1, 17),
        (2, 18),
        (3, 19),

        (1, 20),
        (2, 20),
        (3, 21),
        (1, 22),
        (3, 23),
        (2, 24),
        (1, 25),
        (3, 25),
        (2, 26),
        (3, 26),
        (1, 27),
        (2, 27),
        (2, 28),

        (1, 29),
        (2, 30),
        (3, 31);
