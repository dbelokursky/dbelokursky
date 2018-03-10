CREATE TABLE IF NOT EXISTS items (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(200),
  description TEXT
);

CREATE TABLE IF NOT EXISTS comments (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(200),
  description TEXT,
  item_id     INTEGER REFERENCES items (id)
);

DROP TABLE actions;

CREATE TABLE actions (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

INSERT INTO actions (name) VALUES ('1. Add the new item.');
INSERT INTO actions (name) VALUES ('2. Show items.');
INSERT INTO actions (name) VALUES ('3. Edit item.');
INSERT INTO actions (name) VALUES ('4. Delete item.');
INSERT INTO actions (name) VALUES ('5. Find item by id.');
INSERT INTO actions (name) VALUES ('6. Find item by name.');
