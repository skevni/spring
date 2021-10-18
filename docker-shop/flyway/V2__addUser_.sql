create table users
(
    id         bigserial,
    username   varchar(50)  not null unique,
    password   varchar(255) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

create table roles
(
    id         serial,
    name       varchar(100),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

create table users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table authorities
(
    id         bigserial,
    name       varchar(100) not null unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

create table roles_authorities
(
    role_id      int not null,
    authority_id int not null,
    primary key (role_id, authority_id),
    foreign key (role_id) references roles (id),
    foreign key (authority_id) references authorities (id)
);

-- filling in tables

insert into users(username, password, email)
VALUES ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@domain.su'),   --100
       ('admin', '$2y$04$to.PaR/wcSn/b0ieuGw3ZOq2sy9BPFhjS5c3nDLUCY8yzvEW7/9K.', 'admin@domain.su'), --200
       ('superadmin', '$2y$04$r76P41tjVWWBI8dAspu7AuqHpym86Brl1tlSJkX9eOdz.5Den4J.2', 'superadmin@domain.su'); -- 111;

insert into roles(name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_SEPERUSER'),
       ('ROLE_SUPERADMIN');

insert into users_roles(user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 4);

insert into authorities(name)
values ('Product_Read'),
       ('Product_Edit'),
       ('Product_Create'),
       ('Product_Delete');

insert into roles_authorities(role_id, authority_id)
values (1, 1),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 2),
       (4, 1),
       (4, 2),
       (4, 3),
       (4, 4);

