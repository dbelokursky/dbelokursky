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
  sold          BOOLEAN,
  owner_id INTEGER REFERENCES owner(owner_id)
);

CREATE TABLE image(
image_id SERIAL PRIMARY KEY,
car_id INTEGER REFERENCES car(car_id),
name VARCHAR,
path VARCHAR
);

CREATE TABLE owner(
owner_id SERIAL PRIMARY KEY,
login VARCHAR(300) UNIQUE,
password VARCHAR(300)
);



INSERT INTO transmission (name) VALUES ('manual');
INSERT INTO transmission (name) VALUES ('automatic');
INSERT INTO transmission (name) VALUES ('CVT');

INSERT INTO engine (name) VALUES ('gas');
INSERT INTO engine (name) VALUES ('diesel');
INSERT INTO engine (name) VALUES ('electric');

INSERT INTO suspension (name) VALUES ('depended');
INSERT INTO suspension (name) VALUES ('independent');

INSERT INTO owner (login, password) VALUES ('owner1', 'password1');
INSERT INTO owner (login, password) VALUES ('owner2', 'password2');
INSERT INTO owner (login, password) VALUES ('owner3', 'password3');

INSERT INTO car (brand, model, transmission_id, engine_id, suspension_id, sold, owner_id)
VALUES ('BMW', 'X5', 1, 2, 1, false, 1);
INSERT INTO car (brand, model, transmission_id, engine_id, suspension_id, sold, owner_id)
VALUES ('Opel', 'Astra', 2, 2, 1, true, 2);
INSERT INTO car (brand, model, transmission_id, engine_id, suspension_id, sold, owner_id)
VALUES ('Volkswagen', 'Golf', 2, 1, 1, false, 2);

ALTER TABLE car ADD COLUMN owner_id INTEGER REFERENCES owner(owner_id);
ALTER TABLE owner ADD UNIQUE (login);

