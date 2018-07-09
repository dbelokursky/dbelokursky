CREATE DATABASE cars_sale;

CREATE TABLE transmission (
  transmission_id SERIAL PRIMARY KEY,
  name            VARCHAR(200) NOT NULL
);

CREATE TABLE engine (
  engine_id SERIAL PRIMARY KEY,
  name      VARCHAR(200) NOT NULL
);

CREATE TABLE suspension (
  suspension_id SERIAL PRIMARY KEY,
  name          VARCHAR(200) NOT NULL
);

CREATE TABLE car (
  car_id          SERIAL PRIMARY KEY,
  brand           VARCHAR(200),
  model           VARCHAR(200),
  transmission_id INTEGER REFERENCES transmission (transmission_id),
  engine_id       INTEGER REFERENCES engine (engine_id),
  suspension_id   INTEGER REFERENCES suspension (suspension_id),
  sold          BOOLEAN
);

INSERT INTO transmission (name) VALUES ('manual');
INSERT INTO transmission (name) VALUES ('automatic');
INSERT INTO transmission (name) VALUES ('CVT');

INSERT INTO engine (name) VALUES ('gas');
INSERT INTO engine (name) VALUES ('diesel');
INSERT INTO engine (name) VALUES ('electric');

INSERT INTO suspension (name) VALUES ('depended');
INSERT INTO suspension (name) VALUES ('independent');

INSERT INTO car (brand, model, transmission_id, engine_id, suspension_id, sold)
VALUES ('BMW', 'X5', 1, 2, 1, false);
INSERT INTO car (brand, model, transmission_id, engine_id, suspension_id, sold)
VALUES ('Opel', 'Astra', 2, 2, 1, true);
INSERT INTO car (brand, model, transmission_id, engine_id, suspension_id, sold)
VALUES ('Volkswagen', 'Golf', 2, 1, 1, false);
