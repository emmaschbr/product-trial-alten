INSERT INTO product (code, name, description, image, category, price, quantity, internal_reference, shell_id,
                     inventory_status, rating, created_at, updated_at)
VALUES ('P001', 'Laptop', 'High performance laptop', 'laptop.jpg', 'Electronics', 999.99, 10, 'REF001', 1, 'INSTOCK', 5,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO product (code, name, description, image, category, price, quantity, internal_reference, shell_id,
                     inventory_status,
                     rating, created_at, updated_at)
VALUES ('P002', 'Smartphone', 'Latest smartphone', 'phone.jpg', 'Electronics', 699.99, 15, 'REF002', 2, 'INSTOCK', 4,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO message (email, message, created_at, updated_at)
VALUES ('test@test.tt', 'Hello contact nedded', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);