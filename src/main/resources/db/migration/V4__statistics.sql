create table statistics
(
    id           bigserial,
    service_name varchar,
    duration     bigint default 0,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp,
    primary key (id)
);