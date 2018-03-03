CREATE DATABASE task_003;

--psql \c task_003

--Создать структур данных в базе. Таблицы. Трансмиссия. Двигатель, Подвеска.

CREATE TABLE transmission (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE engine (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE suspension (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE car (
  id                SERIAL PRIMARY KEY,
  name              VARCHAR(200),
  transmission_type INTEGER REFERENCES transmission (id),
  engine_type       INTEGER REFERENCES engine (id),
  suspension_type   INTEGER REFERENCES suspension (id)
);

INSERT INTO transmission (name) VALUES ('manual');
INSERT INTO transmission (name) VALUES ('automatic');
INSERT INTO transmission (name) VALUES ('CVT');

INSERT INTO engine (name) VALUES ('gas');
INSERT INTO engine (name) VALUES ('diesel');
INSERT INTO engine (name) VALUES ('electric');

INSERT INTO suspension (name) VALUES ('depended');
INSERT INTO suspension (name) VALUES ('independent');

INSERT INTO car (name, transmission_type, engine_type, suspension_type) VALUES ('car1', 1, 1, 1);
INSERT INTO car (name, transmission_type, engine_type, suspension_type) VALUES ('car2', 2, 2, 2);


SELECT
  car.name,
  transmission.name,
  engine.name,
  suspension.name
FROM car
  LEFT OUTER JOIN transmission ON car.transmission_type = transmission.id
  LEFT OUTER JOIN engine ON car.engine_type = engine.id
  LEFT OUTER JOIN suspension ON car.suspension_type = suspension.id;


SELECT transmission.name
FROM car
  RIGHT JOIN transmission ON car.transmission_type = transmission.id
WHERE car.name IS NULL
UNION

SELECT engine.name
FROM car
  RIGHT JOIN engine ON car.engine_type = engine.id
WHERE car.name IS NULL
UNION

SELECT suspension.name
FROM car
  RIGHT JOIN suspension ON car.engine_type = suspension.id
WHERE car.name IS NULL;
