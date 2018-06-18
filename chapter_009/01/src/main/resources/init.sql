CREATE TABLE IF NOT EXISTS items (
  id          SERIAL PRIMARY KEY,
  description TEXT      NOT NULL,
  created     TIMESTAMP NOT NULL,
  done        BOOLEAN   NOT NULL
);


