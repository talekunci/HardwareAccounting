create table user_roles(
    user_uuid UUID not null,
    role_id integer not null,

    constraint user_uuid_fk foreign key (user_uuid) references users(uuid),
    constraint role_id_fk foreign key (role_id) references roles(id)
);

alter table users
        drop column role_id;