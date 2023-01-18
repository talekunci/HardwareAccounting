create table user_roles(
    user_uuid UUID NOT NULL,
    role_id INTEGER NOT NULL,

    FOREIGN KEY (user_uuid) REFERENCES users(uuid),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

ALTER TABLE users
        DROP COLUMN role_id;