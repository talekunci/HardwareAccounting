create table user_roles(
    user_uuid UUID not null,
    role_id integer not null,

    foreign key (user_uuid) references users(uuid),
    foreign key (role_id) references roles(id)
);

alter table users
        drop column role_id;