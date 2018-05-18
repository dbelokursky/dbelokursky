CREATE DATABASE music_venue;

CREATE TABLE IF NOT EXISTS user (
  id         SERIAL PRIMARY KEY,
  login      VARCHAR(50) NOT NULL UNIQUE,
  password   VARCHAR(50),
  role_id    INTEGER REFERENCES role (id),
  address_id INTEGER REFERENCES address (id)
);

CREATE TABLE IF NOT EXISTS role (
  id   SERIAL PRIMARY KEY,
  name varchar(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS music_type (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS address (
  id      SERIAL PRIMARY KEY,
  country VARCHAR(20),
  city    VARCHAR(20),
  street  VARCHAR(20),
  unit    VARCHAR(20),
  zip     INTEGER
);





