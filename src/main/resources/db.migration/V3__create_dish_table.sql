CREATE TABLE dishes (
        dishId SERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL UNIQUE,
        description VARCHAR(500),
        price NUMERIC(10, 2) NOT NULL,
        created_at TIMESTAMP NOT NULL
);
