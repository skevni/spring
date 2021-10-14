create table comments
(
    id         bigserial primary key,
    product_id bigint references products (id),
    user_id    bigint references users (id),
    comment    varchar(4000),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
)