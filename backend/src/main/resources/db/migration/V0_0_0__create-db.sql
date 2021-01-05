DO
$do$
    BEGIN
        IF NOT EXISTS(SELECT FROM pg_catalog.pg_roles WHERE rolname = 'company_manager')
        THEN
            CREATE USER company_manager WITH PASSWORD 'test1234' VALID UNTIL 'infinity';
            ALTER USER company_manager WITH SUPERUSER;
        END IF;
    END
$do$;

DO
$do$
    BEGIN
        IF NOT EXISTS(SELECT FROM pg_database WHERE datname = 'company_manager')
        THEN
            CREATE DATABASE company_manager OWNER company_manager;
        END IF;
    END
$do$;

-- CREATE SCHEMA IF NOT EXISTS public AUTHORIZATION company_manager;
-- ALTER SCHEMA public OWNER TO company_manager;
