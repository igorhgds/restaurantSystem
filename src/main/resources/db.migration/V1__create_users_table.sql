CREATE TABLE users (
       userId SERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       email VARCHAR(255) NOT NULL UNIQUE,
       password VARCHAR(255) NOT NULL,
       role VARCHAR(50) NOT NULL
);
