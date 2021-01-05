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



INSERT INTO teams_employees (employee_id, team_id)
VALUES  (1, 1),
        (2, 2),
        (3, 3),

        (4, 1),
        (5, 2),
        (6, 3),

        (7, 1),
        (8, 3),
        (9, 2),

        (10, 1),
        (10, 3),
        (11, 2),
        (12, 2),
        (13, 1),
        (14, 1),
        (14, 2),
        (15, 3),

        (16, 1),
        (17, 1),
        (18, 2),
        (19, 3),

        (20, 1),
        (20, 2),
        (21, 3),
        (22, 1),
        (23, 3),
        (24, 2),
        (25, 1),
        (25, 3),
        (26, 2),
        (26, 3),
        (27, 1),
        (27, 2),
        (28, 2),

        (29, 1),
        (30, 2),
        (31, 3);
