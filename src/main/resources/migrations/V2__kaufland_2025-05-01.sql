INSERT INTO PRODUCT (product_id, name, brand, package_quantity, package_unit, category_id) VALUES
(21, 'lapte zuzu', 'Zuzu', 1, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'lactate')),
(22, 'iaurt grecesc', 'Olympus', 0.4, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'lactate')),
(23, 'ouă mărimea M', 'Ferma Veche', 10, 'buc', (SELECT category_id FROM CATEGORY WHERE name = 'ouă')),
(24, 'brânză telemea', 'Hochland', 0.3, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'lactate')),
(25, 'pâine albă', 'K-Classic', 500, 'g', (SELECT category_id FROM CATEGORY WHERE name = 'panificație')),
(26, 'roșii cherry', 'K-Bio', 250, 'g', (SELECT category_id FROM CATEGORY WHERE name = 'legume și fructe')),
(27, 'piept pui', 'Agricola', 1, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'carne')),
(28, 'spaghetti nr.5', 'Barilla', 500, 'g', (SELECT category_id FROM CATEGORY WHERE name = 'paste făinoase')),
(29, 'zahăr tos', 'Mărgăritar', 1, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'alimente de bază')),
(30, 'apă plată', 'Dorna', 2, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'băuturi')),
(31, 'banane', 'Generic', 1, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'legume și fructe')),
(32, 'ulei floarea-soarelui', 'Floriol', 1, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'alimente de bază')),
(33, 'biscuiți cu unt', 'Milka', 0.2, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'gustări')),
(34, 'cafea măcinată', 'Jacobs', 0.25, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'cafea')),
(35, 'detergent lichid', 'Ariel', 2.5, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'produse de menaj')),
(36, 'șampon păr gras', 'Elseve', 400, 'ml', (SELECT category_id FROM CATEGORY WHERE name = 'îngrijire personală')),
(37, 'hârtie igienică 3 straturi', 'Pufina', 10, 'role', (SELECT category_id FROM CATEGORY WHERE name = 'produse de menaj')),
(38, 'piper negru măcinat', 'Fuchs', 50, 'g', (SELECT category_id FROM CATEGORY WHERE name = 'condimente')),
(39, 'vin alb demisec', 'Recas', 0.75, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'băuturi')),
(40, 'ciocolată neagră 70%', 'Heidi', 100, 'g', (SELECT category_id FROM CATEGORY WHERE name = 'gustări')),
(41, 'cartofi albi', 'Generic', 1, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'legume și fructe')),
(42, 'ceapă galbenă', 'Generic', 1, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'legume și fructe')),
(43, 'morcovi', 'Generic', 0.5, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'legume și fructe')),
(44, 'suc portocale', 'Cappy', 1, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'băuturi')),
(45, 'cașcaval', 'Delaco', 0.25, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'lactate'));

INSERT INTO PRICE_HISTORY (price_history_id, date_price, price, currency, product_id, retailer_id) VALUES
(21, '2025-05-01', 10.10, 'RON', 21, 2),
(22, '2025-05-01', 11.80, 'RON', 22, 2),
(23, '2025-05-01', 13.50, 'RON', 23, 2),
(24, '2025-05-01', 13.10, 'RON', 24, 2),
(25, '2025-05-01', 3.60, 'RON', 25, 2),
(26, '2025-05-01', 7.00, 'RON', 26, 2),
(27, '2025-05-01', 27.90, 'RON', 27, 2),
(28, '2025-05-01', 5.90, 'RON', 28, 2),
(29, '2025-05-01', 4.50, 'RON', 29, 2),
(30, '2025-05-01', 5.30, 'RON', 30, 2),
(31, '2025-05-01', 6.20, 'RON', 31, 2),
(32, '2025-05-01', 9.50, 'RON', 32, 2),
(33, '2025-05-01', 7.50, 'RON', 33, 2),
(34, '2025-05-01', 23.00, 'RON', 34, 2),
(35, '2025-05-01', 50.50, 'RON', 35, 2),
(36, '2025-05-01', 18.00, 'RON', 36, 2),
(37, '2025-05-01', 19.20, 'RON', 37, 2),
(38, '2025-05-01', 6.10, 'RON', 38, 2),
(39, '2025-05-01', 24.00, 'RON', 39, 2),
(40, '2025-05-01', 4.10, 'RON', 40, 2),
(41, '2025-05-01', 3.20, 'RON', 41, 2),
(42, '2025-05-01', 2.90, 'RON', 42, 2),
(43, '2025-05-01', 2.50, 'RON', 43, 2),
(44, '2025-05-01', 7.80, 'RON', 44, 2),
(45, '2025-05-01', 15.00, 'RON', 45, 2);

INSERT INTO DISCOUNT_HISTORY (discount_history_id, start_date, end_date, percentage_of_discount, discount_created_date, price_history_id) VALUES
(21, '2025-05-01', '2025-05-07', 10, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 22 AND retailer_id = 2 AND date_price = '2025-05-01')),
(22, '2025-05-01', '2025-05-07', 10, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 24 AND retailer_id = 2 AND date_price = '2025-05-01')),
(23, '2025-05-02', '2025-05-08', 12, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 27 AND retailer_id = 2 AND date_price = '2025-05-01')),
(24, '2025-05-03', '2025-05-09', 18, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 28 AND retailer_id = 2 AND date_price = '2025-05-01')),
(25, '2025-05-04', '2025-05-10', 10, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 34 AND retailer_id = 2 AND date_price = '2025-05-01')),
(26, '2025-05-01', '2025-05-07', 22, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 35 AND retailer_id = 2 AND date_price = '2025-05-01')),
(27, '2025-05-02', '2025-05-08', 12, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 36 AND retailer_id = 2 AND date_price = '2025-05-01')),
(28, '2025-05-03', '2025-05-09', 8,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 39 AND retailer_id = 2 AND date_price = '2025-05-01')),
(29, '2025-05-04', '2025-05-10', 15, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 40 AND retailer_id = 2 AND date_price = '2025-05-01')),
(30, '2025-05-01', '2025-05-07', 5,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 41 AND retailer_id = 2 AND date_price = '2025-05-01')),
(31, '2025-05-02', '2025-05-08', 7,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 44 AND retailer_id = 2 AND date_price = '2025-05-01')),
(32, '2025-05-03', '2025-05-09', 10, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 45 AND retailer_id = 2 AND date_price = '2025-05-01'));