create sequence roles_id_seq;

create table roles(
    id bigint default roles_id_seq.nextval PRIMARY key,
    name VARCHAR(10) NOT NULL
);

INSERT INTO roles(name) VALUES ('User');
INSERT INTO roles(name) VALUES ('Admin');

create table users (
    uuid UUID primary key default default random_uuid(),
    login varchar(50) not null,
    password varchar(72) not null,
    role_id bigint default 1,

    foreign key (role_id) references roles(id)
);

create table hardware (
    uuid UUID primary key default random_uuid(),
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
    hardware_uuid UUID NOT NULL,
    maintenance_date date not null,

    foreign key (hardware_uuid) references hardware(uuid)
);