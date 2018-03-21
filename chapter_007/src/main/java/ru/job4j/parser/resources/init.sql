CREATE TABLE IF NOT EXISTS joboffers (
  id               SERIAL,
  name             VARCHAR(200),
  url              VARCHAR(400) PRIMARY KEY,
  last_update_time TIMESTAMP
);
