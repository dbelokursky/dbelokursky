CREATE DATABASE cars_sale;

CREATE TABLE transmission (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);

CREATE TABLE engine (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);

CREATE TABLE suspension (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL
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
