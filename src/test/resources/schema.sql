CREATE TABLE IF NOT EXISTS users (
  id       INT IDENTITY PRIMARY KEY,
  name     VARCHAR,
  login    VARCHAR,
  password VARCHAR,
  role     VARCHAR,
  email    VARCHAR
);
