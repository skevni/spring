Create table products
(
    id    bigserial primary key,
    title varchar(255),
    price double precision
);

INSERT INTO products(title, price)
VALUES ('phone', 300),
       ('TV', 450),
       ('iron', 25),
       ('vacuum cleaner', 250),
       ('kitchen stove', 456),
       ('oven', 500),
       ('multicooker', 200),
       ('hairdryer', 30),
       ('dishwasher', 467),
       ('microwave', 100),
       ('freezer', 400),
       ('refrigerator', 459.99),
       ('monitor', 120.65),
       ('cable', 3)
