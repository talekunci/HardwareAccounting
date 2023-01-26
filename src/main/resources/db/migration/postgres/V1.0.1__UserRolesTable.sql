create table user_roles(
    user_uuid UUID NOT NULL,
    role_id INTEGER NOT NULL,

    CONSTRAINT user_uuid_fk FOREIGN KEY (user_uuid) REFERENCES users(uuid),
    CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES roles(id)
);

ALTER TABLE users
        DROP COLUMN role_id;