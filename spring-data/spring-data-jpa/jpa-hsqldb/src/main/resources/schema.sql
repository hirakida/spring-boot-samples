CREATE TABLE IF NOT EXISTS user (
  id INT PRIMARY KEY IDENTITY,
  name VARCHAR(30) NOT NULL,
  created_at DATETIME NOT NULL
);
