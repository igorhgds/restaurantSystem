CREATE TABLE tables (
                        tableId SERIAL PRIMARY KEY,
                        table_number INTEGER NOT NULL UNIQUE,
                        table_status VARCHAR(20) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        waiter_id BIGINT NOT NULL,
                        CONSTRAINT fk_waiter FOREIGN KEY (waiter_id) REFERENCES users(userId)
);
