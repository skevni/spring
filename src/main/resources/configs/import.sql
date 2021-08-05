-- DROP TABLE IF EXISTS products;
-- CREATE TABLE products(id bigserial PRIMARY KEY, title varchar(255), cost float8 );
INSERT INTO products(title, cost)
VALUES ('Shoes', 30.89),
       ('Shirt', 12.99),
       ('Jacket', 100.78),
       ('Coat', 150.99);