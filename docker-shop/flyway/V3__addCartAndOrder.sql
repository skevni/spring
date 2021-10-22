create table cart
(
    id          serial,
    date        timestamp default current_timestamp,
    user_id     bigint,
    product_id  bigint           not null,
    price       double precision not null,
    total_price double precision not null,
    quantity    int              not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp,
    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (product_id) references products (id)
);

create table orders
(
    id          bigserial,
    order_date  timestamp default current_timestamp,
    user_id     bigint           not null,
    is_paid     boolean   default false,
    phone       varchar(50),
    address     varchar(255),
    total_price double precision not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp,
    foreign key (user_id) references users (id),
    primary key (id)
);

create table order_items
(
    id          bigserial,
    order_id    bigint           not null,
    product_id  bigint           not null,
    price       double precision not null,
    total_price double precision not null,
    quantity    int              not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp,
    foreign key (order_id) references orders (id),
    foreign key (product_id) references products (id),
    primary key (id)
);
