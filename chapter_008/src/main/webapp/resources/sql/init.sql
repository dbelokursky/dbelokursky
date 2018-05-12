create table if not exists user_store (
  id          serial primary key,
  name        varchar(300),
  login       varchar(100),
  email       varchar(100),
  create_date TIMESTAMP,
  password    varchar(50),
  role        varchar(10),
  conutry     varchar(30),
  city        varchar(10)
);
