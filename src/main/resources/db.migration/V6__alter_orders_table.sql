ALTER TABLE orders
ALTER COLUMN total_price TYPE NUMERIC(10,2)
USING total_price::NUMERIC(10,2);