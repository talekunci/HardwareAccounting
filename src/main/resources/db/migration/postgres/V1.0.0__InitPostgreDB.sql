CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE res(
    id SERIAL PRIMARY KEY,
    name VARCHAR(15) NOT NULL
);

INSERT INTO roles(name) VALUES ('User');
INSERT INTO roles(name) VALUES ('Admin');

CREATE TABLE users (
    uuid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    login VARCHAR(50) NOT NULL,
    password VARCHAR(72) NOT NULL,
    role_id INTEGER DEFAULT 1,

    CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE hardware (
    uuid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    serial_number INTEGER NOT NULL,
    manufacturer VARCHAR(72) NOT NULL,
    name VARCHAR(72) NOT NULL,
    description VARCHAR(72),
    manufacture_date DATE NOT NULL DEFAULT CURRENT_DATE,
    installation_date DATE,
    installation_address VARCHAR(72),
    owner_phone_number INTEGER,
    owner_email VARCHAR(320)
);

create table maintenance_dates(
    uuid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    hardware_uuid UUID NOT NULL,
    maintenance_date DATE NOT NULL,

    CONSTRAINT hardware_uuid_fk FOREIGN KEY (hardware_uuid) REFERENCES hardware(uuid)
);