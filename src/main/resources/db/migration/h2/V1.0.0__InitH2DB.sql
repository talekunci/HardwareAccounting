create table roles(
     id integer auto_increment primary key,
    name VARCHAR(10) NOT NULL
);

INSERT INTO roles(name) VALUES ('User');
INSERT INTO roles(name) VALUES ('Admin');

create table users (
    uuid UUID default random_uuid() primary key,
    login varchar(50) not null,
    password varchar(72) not null,
    role_id bigint default 1,

    foreign key (role_id) references roles(id)
);

create table hardware (
    uuid UUID default random_uuid() primary key,
    serial_number bigint NOT NULL,
    manufacturer varchar(72) NOT NULL,
    name varchar(72) NOT NULL,
    description varchar(72),
    manufacture_date DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    installation_date DATE,
    installation_address varchar(72),
    owner_phone_number int,
    owner_email varchar(320)
);

create table maintenance_dates(
    uuid UUID default random_uuid() primary key,
    hardware_uuid UUID NOT NULL,
    maintenance_date date not null,

    foreign key (hardware_uuid) references hardware(uuid)
);