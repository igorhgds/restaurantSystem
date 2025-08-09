CREATE TABLE tables
(
    table_id      UUID        PRIMARY KEY,
    table_number INTEGER     NOT NULL UNIQUE,
    table_status VARCHAR(20) NOT NULL,
    created_at   TIMESTAMP   NOT NULL,
    waiter_id    UUID      NOT NULL,
    CONSTRAINT fk_waiter FOREIGN KEY (waiter_id) REFERENCES users (userId)
);
