CREATE EXTENSION "uuid-ossp";

create table roles(
    id serial PRIMARY key,
    name varchar(10) not null
);

INSERT INTO roles(name) VALUES ('User');
INSERT INTO roles(name) VALUES ('Admin');

create table users (
    uuid UUID primary key default uuid_generate_v4(),
    login varchar(50) not null,
    password varchar(72) not null,
    role_id integer not null,

    constraint role_id_fk foreign key (role_id) references roles(id)
);

create table hardware (
    uuid UUID primary key default uuid_generate_v4(),
    serial_number integer NOT NULL,
    manufaturer varchar(72) NOT NULL,
    name varchar(72) NOT NULL,
    description varchar(72),
    manufacture_date DATE NOT NULL DEFAULT CURRENT_DATE,
    instalation_date DATE,
    instalation_address varchar(72),
    owner_phone_number integer,
    owner_email varchar(320)
);

create table maintenance_dates(
    hardware_uuid UUID NOT NULL,
    maintenance_date date not null,

    constraint hardware_uuid_fk foreign key (hardware_uuid) references hardware(uuid)
);