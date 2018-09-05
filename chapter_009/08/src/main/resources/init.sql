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

CREATE TABLE owner(
owner_id SERIAL PRIMARY KEY,
login VARCHAR(300) UNIQUE,
password VARCHAR(300),
enabled BOOLEAN
);

CREATE TABLE car (
  car_id          SERIAL PRIMARY KEY,
  brand           VARCHAR(200),
  model           VARCHAR(200),
  transmission_id INTEGER REFERENCES transmission (transmission_id),
  engine_id       INTEGER REFERENCES engine (engine_id),
  suspension_id   INTEGER REFERENCES suspension (suspension_id),
  sold          BOOLEAN,
  owner_id INTEGER REFERENCES owner(owner_id),
  publication_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE image(
image_id SERIAL PRIMARY KEY,
car_id INTEGER REFERENCES car(car_id),
name VARCHAR,
path VARCHAR
);

CREATE TABLE role(
role_id SERIAL PRIMARY KEY,
name VARCHAR(20) UNIQUE
);

CREATE TABLE owner_role(
owner_id INTEGER REFERENCES owner(owner_id),
role_id INTEGER REFERENCES role(role_id),
UNIQUE (owner_id, role_id)
);


-- Insert data
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');


INSERT INTO transmission (name) VALUES ('manual');
INSERT INTO transmission (name) VALUES ('automatic');
INSERT INTO transmission (name) VALUES ('CVT');

INSERT INTO engine (name) VALUES ('gas');
INSERT INTO engine (name) VALUES ('diesel');
INSERT INTO engine (name) VALUES ('electric');

INSERT INTO suspension (name) VALUES ('depended');
INSERT INTO suspension (name) VALUES ('independent');
-- owner1 with password1, owner2 with password2 etc.
INSERT INTO owner (login, password, enabled) VALUES ('owner1', '$2a$10$eoIqcMQxa9oubfEa9wv.GOTpOaIudEQmCTr4F.jLzkerJuumAyu6C', TRUE);
INSERT INTO owner (login, password, enabled) VALUES ('owner2', '$2a$10$DpIj6lvOCFCrd461pfo9r.AP5koi6sN1g2SDJzLHYvHRzGjCFD6A.', TRUE);
INSERT INTO owner (login, password, enabled) VALUES ('owner3', '$2a$10$uoyWPgbo/ujJljySz.rUX.5qZBIFkGri9yuZJExWvJfHDZ/45nhnu', TRUE);

-- owner1 with role USER and owner2 role USER and ADMIN
INSERT INTO owner_role(owner_id, role_id) VALUES (1,1);
INSERT INTO owner_role(owner_id, role_id) VALUES (2,1);
INSERT INTO owner_role(owner_id, role_id) VALUES (2,2);


INSERT INTO car (brand, model, transmission_id, engine_id, suspension_id, sold, owner_id)
VALUES ('BMW', 'X5', 1, 2, 1, false, 1);
INSERT INTO car (brand, model, transmission_id, engine_id, suspension_id, sold, owner_id)
VALUES ('Opel', 'Astra', 2, 2, 1, true, 2);
INSERT INTO car (brand, model, transmission_id, engine_id, suspension_id, sold, owner_id)
VALUES ('Volkswagen', 'Golf', 2, 1, 1, false, 2);

