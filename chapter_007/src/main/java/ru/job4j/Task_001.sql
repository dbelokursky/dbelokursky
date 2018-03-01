-- UML diagram link https://www.lucidchart.com/invitations/accept/04e9613d-e236-47d8-9e3c-88ddf7cd7400

CREATE DATABASE tracker_db;

--add tables

CREATE TABLE users (
  id       SERIAL PRIMARY KEY,
  login    VARCHAR(200),
  password VARCHAR(200)
);

CREATE TABLE attachments (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE category (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE comments (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE item (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE rights (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE roles (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE state (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

--add tables relations

ALTER TABLE users
  ADD COLUMN role_id INTEGER REFERENCES roles (id);

ALTER TABLE rigths
  RENAME TO rights;

ALTER TABLE roles
  ADD COLUMN right_id INTEGER REFERENCES rights (id);

ALTER TABLE item
  ADD COLUMN user_id INTEGER REFERENCES users (id);

ALTER TABLE item
  ADD COLUMN comment_id INTEGER REFERENCES comments (id);

ALTER TABLE item
  ADD COLUMN right_id INTEGER REFERENCES rights (id);

ALTER TABLE item
  ADD COLUMN user_id INTEGER REFERENCES users (id);

ALTER TABLE item
  ADD COLUMN attachment_id INTEGER REFERENCES attachments (id);

ALTER TABLE item
  ADD COLUMN state_id INTEGER REFERENCES state (id);

--initialization

INSERT INTO users (login, password) VALUES ('user1', 'password1');

INSERT INTO roles (name) VALUES ('role1');

INSERT INTO rights (name) VALUES ('right1');

INSERT INTO attachments (name) VALUES ('attachment1');

INSERT INTO state (name) VALUES ('state1');

INSERT INTO category (name) VALUES ('category1');

INSERT INTO comments (name, content) VALUES ('comment1', 'comment1Text');


