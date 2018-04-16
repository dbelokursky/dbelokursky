CREATE DATABASE tracker_db;

CREATE TABLE users (
  id serial,
  name varchar(200),
  role varchar(20)
);

CREATE TABLE roles (
  id serial,
  name varchar(20),
  rights varchar(20)
);

CREATE TABLE rights (
  id serial,
  name varchar(20)
);

CREATE TABLE item (
  id serial,
  user_id int,
  comments text,
  attachments text,
  state varchar(20),
  category varchar(20)
);