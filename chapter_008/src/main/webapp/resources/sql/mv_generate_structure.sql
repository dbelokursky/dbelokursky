CREATE DATABASE music_venue;

CREATE TABLE IF NOT EXISTS role (
  id   SERIAL PRIMARY KEY,
  name varchar(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS address (
  id      SERIAL PRIMARY KEY,
  country VARCHAR(20) NOT NULL,
  city    VARCHAR(20) NOT NULL,
  street  VARCHAR(20) NOT NULL,
  unit    VARCHAR(20) NOT NULL,
  zip     INTEGER     NOT NULL
);

CREATE TABLE mv_user (
  id         SERIAL PRIMARY KEY,
  login      VARCHAR(50)                     NOT NULL UNIQUE,
  password   VARCHAR(50)                     NOT NULL,
  role_id    INTEGER REFERENCES role (id)    NOT NULL,
  address_id INTEGER REFERENCES address (id) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS music_type (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_music (
  user_id  INTEGER REFERENCES mv_user (id)    NOT NULL,
  music_id INTEGER REFERENCES music_type (id) NOT NULL,
  UNIQUE (user_id, music_id)
);








