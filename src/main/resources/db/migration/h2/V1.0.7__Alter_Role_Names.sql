ALTER TABLE roles
    ALTER COLUMN name SET DATA TYPE VARCHAR(15);

UPDATE roles
    SET name = CONCAT('ROLE_', name);
