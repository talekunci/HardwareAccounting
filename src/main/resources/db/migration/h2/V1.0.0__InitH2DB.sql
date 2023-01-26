create table roles(
     id INTEGER auto_increment PRIMARY KEY,
    name VARCHAR(10) NOT NULL
);

INSERT INTO roles(name) VALUES ('User');
INSERT INTO roles(name) VALUES ('Admin');

create table users (
    uuid UUID DEFAULT random_uuid() PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(72) NOT NULL,
    role_id BIGINT DEFAULT 1,

    FOREIGN KEY (role_id) REFERENCES roles(id)
);

create table hardware (
    uuid UUID DEFAULT random_uuid() PRIMARY KEY,
    serial_number BIGINT NOT NULL,
    manufacturer VARCHAR(72) NOT NULL,
    name VARCHAR(72) NOT NULL,
    description VARCHAR(72),
    manufacture_date DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    installation_date DATE,
    installation_address VARCHAR(72),
    owner_phone_number INT,
    owner_email VARCHAR(320)
);

create table maintenance_dates(
    uuid UUID DEFAULT random_uuid() PRIMARY KEY,
    hardware_uuid UUID NOT NULL,
    maintenance_date DATE NOT NULL,

    FOREIGN KEY (hardware_uuid) REFERENCES hardware(uuid)
);