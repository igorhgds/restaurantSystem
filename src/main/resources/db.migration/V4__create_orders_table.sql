CREATE TABLE orders (
        id SERIAL PRIMARY KEY,
        table_id BIGINT NOT NULL,
        waiter_id BIGINT NOT NULL,
        status VARCHAR(20) NOT NULL,
        created_at TIMESTAMP NOT NULL,
        CONSTRAINT fk_order_table FOREIGN KEY (table_id) REFERENCES tables(id),
        CONSTRAINT fk_order_waiter FOREIGN KEY (waiter_id) REFERENCES users(id)
);
