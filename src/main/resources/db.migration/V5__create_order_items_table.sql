CREATE TABLE order_items (
                             order_items_id SERIAL PRIMARY KEY,
                             order_id BIGINT NOT NULL,
                             dish_id BIGINT NOT NULL,
                             quantity INTEGER NOT NULL,
                             unit_price NUMERIC(10, 2) NOT NULL,
                             created_at TIMESTAMP NOT NULL,
                             CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES orders(orderId),
                             CONSTRAINT fk_item_dish FOREIGN KEY (dish_id) REFERENCES dishes(dishId)
);
