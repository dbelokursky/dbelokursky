CREATE DATABASE task_002;

--switch to the new database
--psql localhost task_002 postgres

CREATE TABLE product (
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(200),
  expired_date TIMESTAMP,
  price        MONEY
);

CREATE TABLE type (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

ALTER TABLE product
  ADD COLUMN type_id INTEGER REFERENCES type (id);

--initialization

INSERT INTO type (name) VALUES ('Сыр');
INSERT INTO type (name) VALUES ('Сырный продукт');
INSERT INTO type (name) VALUES ('Молочный продукт');
INSERT INTO type (name) VALUES ('Молоко');

INSERT INTO product (name, expired_date, price, type_id) VALUES ('Сыр Россия', '2018-02-12 04:05:06', 50, 1);
INSERT INTO product (name, expired_date, price, type_id) VALUES ('Сыр Гауда', '2018-02-11 04:05:06', 50, 1);
INSERT INTO product (name, expired_date, price, type_id) VALUES ('Сыр Пармезан', '2018-02-10 04:05:06', 500, 1);
INSERT INTO product (name, expired_date, price, type_id) VALUES ('Мороженное Nestle', '2018-02-10 04:05:06', 100, 3);
INSERT INTO product (name, expired_date, price, type_id) VALUES ('Мороженное Mars', '2018-02-10 04:05:06', 111, 3);
INSERT INTO product (name, expired_date, price, type_id) VALUES ('Мороженное Noname', '2018-02-10 04:05:06', 100, 3);

--1. Написать запрос получение всех продуктов с типом "СЫР"

SELECT *
FROM product
WHERE type_id = 1;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"

SELECT *
FROM product
WHERE upper(name) LIKE '%МОРОЖЕННОЕ%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.

INSERT INTO product (name, expired_date, price, type_id) VALUES ('Мороженное Noname_2', '2018-03-10 04:05:06', 67, 3);
INSERT INTO product (name, expired_date, price, type_id) VALUES ('Мороженное Noname_1', '2018-03-14 04:05:06', 77, 3);

SELECT *
FROM product
WHERE expired_date BETWEEN '2018-03-01' AND '2018-03-30';

--4. Написать запрос, который вывод самый дорогой продукт.

SELECT *
FROM product
WHERE price = (SELECT max(price)
               FROM product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.

SELECT count(id)
FROM product
WHERE type_id = 1;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

INSERT INTO product (name, expired_date, price, type_id) VALUES ('Молоко Noname_2', '2018-03-10 04:05:06', 60, 4);
INSERT INTO product (name, expired_date, price, type_id) VALUES ('Молоко Noname_1', '2018-03-14 04:05:06', 50, 4);

SELECT *
FROM product AS p
WHERE p.type_id IN (SELECT id
                    FROM type AS t
                    WHERE upper(t.name) LIKE 'СЫР' OR upper(t.name) LIKE 'МОЛОКО');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.

ALTER TABLE product
  ADD COLUMN count INT;

UPDATE product
SET count = 5
WHERE type_id = 1;

UPDATE product
SET count = 11
WHERE type_id = 3;

UPDATE product
SET count = 6
WHERE type_id = 4;

SELECT *
FROM product
WHERE count < 10;

SELECT (prod.name, t.name)
FROM product AS prod
  INNER JOIN type AS t ON prod.type_id = t.id;
