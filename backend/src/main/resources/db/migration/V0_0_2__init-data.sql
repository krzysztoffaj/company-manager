INSERT INTO employees (first_name, last_name, position, salary, supervisor_id)
VALUES  ('John', 'White', 'PO', 50000, NULL),
        ('James', 'Armstrong', 'PO', 48750, NULL),
        ('Jessica', 'Elliott', 'PO', 35000, NULL),

        ('William', 'King', 'PM', 100000, 1),
        ('Louis', 'Woods', 'PM', 89500, 2),
        ('Anna', 'Lakes', 'PM', 95000, 3),

        ('Lukas', 'Mountains', 'Scrum master', 150005, 4),
        ('Martin', 'Johnson', 'Scrum master', 17000, 5),
        ('Jennifer', 'Morales', 'Scrum master', 12500, 6),

        ('Natalie', 'Jones', 'Developer', 15000, 1),
        ('Jerry', 'Circ', 'Developer', 10000, 2),
        ('Johnathan', 'Smith', 'Developer', 12000, 5),
        ('John', 'Washington', 'Developer', 7800, 7),
        ('Evelyn', 'Parker', 'Developer', 9500, 10),
        ('Jacob', 'Blacksmith', 'Developer', 11500, 11),

        ('Jack', 'Hill', 'DevOps', 8000, 7),
        ('Dean', 'King', 'DevOps', 120000, 8),
        ('Alexandra', 'Thomson', 'DevOps', 5222, 12),
        ('Thomas', 'Wolf', 'DevOps', 4850, 13),

        ('Bruce', 'Edwards', 'Tester', 5000, 1),
        ('Hanna', 'Watson', 'Tester', 6000, 2),
        ('Adam', 'Wergton', 'Tester', 4500, 3),
        ('Walter', 'Gallagher', 'Tester', 5000, 4),
        ('John', 'Ibelino', 'Tester', 4250, 6),
        ('Andrew', 'Gerić', 'Tester', 3900, 3),
        ('Paul', 'Richards', 'Tester', 5225, 7),
        ('Christopher', 'Steward', 'Tester', 4580, 10),
        ('Anna', 'Ferguson', 'Tester', 6350, 15),

        ('Jessica', 'Oceans', 'Analyst', 6000, 1),
        ('Daniel', 'van Dyke', 'Analyst', 4000, 2),
        ('Jennifer', 'Parker', 'Analyst', 3000, 6);



INSERT INTO teams (name, product_owner_id, project_manager_id, scrum_master_id)
VALUES  ('Technical Architecture', 1, 4, 7),
        ('Mobile', 2, 5, 8),
        ('Customer Relationship Management', 3, 6, 9);



INSERT INTO employees_teams (employee_id, team_id)
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
