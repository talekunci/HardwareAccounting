INSERT INTO users(login, password)
VALUES ('user@email.com', '123456');
INSERT INTO users(login, password)
VALUES ('admin@email.com', 'qwerty');
INSERT INTO users(login, password)
VALUES ('another@email.com', 'password');

INSERT INTO user_roles(user_uuid, role_id)
VALUES ((SELECT uuid FROM users WHERE login = 'user@email.com'),
        (SELECT id FROM roles WHERE name = 'User'));

INSERT INTO user_roles(user_uuid, role_id)
VALUES ((SELECT uuid FROM users WHERE login = 'admin@email.com'),
        (SELECT id FROM roles WHERE name = 'User'));
INSERT INTO user_roles(user_uuid, role_id)
VALUES ((SELECT uuid FROM users WHERE login = 'admin@email.com'),
        (SELECT id FROM roles WHERE name = 'Admin'));

INSERT INTO user_roles(user_uuid, role_id)
VALUES ((SELECT uuid FROM users WHERE login = 'another@email.com'),
        (SELECT id FROM roles WHERE name = 'Admin'));