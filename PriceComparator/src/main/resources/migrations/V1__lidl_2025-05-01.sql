INSERT INTO CATEGORY (category_id, name) VALUES
(1, 'Alimente'),
(2, 'Băuturi'),
(3, 'Produse de uz casnic'),
(17, 'Pește');


INSERT INTO RETAILER (retailer_id, name) VALUES
(1, 'LIDL'),
(2, 'KAUFLAND'),
(3, 'PROFI');

INSERT INTO CATEGORY (category_id, name, parent_category_id) VALUES
(4, 'lactate', 1),
(5, 'ouă', 1),
(6, 'panificație', 1),
(7, 'legume și fructe', 1),
(8, 'carne', 1),
(9, 'paste făinoase', 1),
(10, 'alimente de bază', 1),
(11, 'gustări', 1),
(12, 'cafea', 1),
(13, 'produse de menaj', 3),
(14, 'îngrijire personală', 3),
(15, 'condimente', 1),
(16, 'băuturi', 2);

--lidl_2025-05-01
INSERT INTO PRODUCT (product_id, name, brand, package_quantity, package_unit, category_id) VALUES
(1, 'lapte zuzu', 'Zuzu', 1, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'lactate')),
(2, 'iaurt grecesc', 'Lidl', 0.4, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'lactate')),
(3, 'ouă mărimea M', 'Lidl', 10, 'buc', (SELECT category_id FROM CATEGORY WHERE name = 'ouă')),
(4, 'brânză telemea', 'Pilos', 0.3, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'lactate')),
(5, 'pâine albă', 'Lidl', 500, 'g', (SELECT category_id FROM CATEGORY WHERE name = 'panificație')),
(6, 'roșii cherry', 'Lidl', 250, 'g', (SELECT category_id FROM CATEGORY WHERE name = 'legume și fructe')),
(7, 'piept pui', 'Lidl', 1, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'carne')),
(8, 'spaghetti nr.5', 'Barilla', 500, 'g', (SELECT category_id FROM CATEGORY WHERE name = 'paste făinoase')),
(9, 'zahăr tos', 'Lidl', 1, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'alimente de bază')),
(10, 'apă plată', 'Aqua Carpatica', 2, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'băuturi')),
(11, 'banane', 'Lidl', 1, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'legume și fructe')),
(12, 'ulei floarea-soarelui', 'Bunica', 1, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'alimente de bază')),
(13, 'biscuiți cu unt', 'Deluxe', 0.2, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'gustări')),
(14, 'cafea măcinată', 'Davidoff', 0.25, 'kg', (SELECT category_id FROM CATEGORY WHERE name = 'cafea')),
(15, 'detergent lichid', 'Persil', 2.5, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'produse de menaj')),
(16, 'șampon păr gras', 'Head & Shoulders', 400, 'ml', (SELECT category_id FROM CATEGORY WHERE name = 'îngrijire personală')),
(17, 'hârtie igienică 3 straturi', 'Zewa', 10, 'role', (SELECT category_id FROM CATEGORY WHERE name = 'produse de menaj')),
(18, 'piper negru măcinat', 'Kotanyi', 50, 'g', (SELECT category_id FROM CATEGORY WHERE name = 'condimente')),
(19, 'vin alb demisec', 'Jidvei', 0.75, 'l', (SELECT category_id FROM CATEGORY WHERE name = 'băuturi')),
(20, 'ciocolată neagră 70%', 'Fin Carre', 100, 'g', (SELECT category_id FROM CATEGORY WHERE name = 'gustări'));


INSERT INTO PRICE_HISTORY (price_history_id, date_price, price, currency, product_id, retailer_id) VALUES
(1, '2025-05-01', 9.90, 'RON', 1, 1),
(2, '2025-05-01', 11.50, 'RON', 2, 1),
(3, '2025-05-01', 13.20, 'RON', 3, 1),
(4, '2025-05-01', 12.80, 'RON', 4, 1),
(5, '2025-05-01', 3.50, 'RON', 5, 1),
(6, '2025-05-01', 6.80, 'RON', 6, 1),
(7, '2025-05-01', 28.50, 'RON', 7, 1),
(8, '2025-05-01', 5.80, 'RON', 8, 1),
(9, '2025-05-01', 4.40, 'RON', 9, 1),
(10, '2025-05-01', 5.20, 'RON', 10, 1),
(11, '2025-05-01', 6.10, 'RON', 11, 1),
(12, '2025-05-01', 9.20, 'RON', 12, 1),
(13, '2025-05-01', 7.10, 'RON', 13, 1),
(14, '2025-05-01', 22.40, 'RON', 14, 1),
(15, '2025-05-01', 49.90, 'RON', 15, 1),
(16, '2025-05-01', 17.80, 'RON', 16, 1),
(17, '2025-05-01', 18.90, 'RON', 17, 1),
(18, '2025-05-01', 6.00, 'RON', 18, 1),
(19, '2025-05-01', 23.50, 'RON', 19, 1),
(20, '2025-05-01', 3.90, 'RON', 20, 1);

INSERT INTO DISCOUNT_HISTORY (discount_history_id, start_date, end_date, percentage_of_discount, discount_created_date, price_history_id) VALUES
(1, '2025-05-01', '2025-05-07', 10, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 1 AND retailer_id = 1 AND date_price = '2025-05-01')),
(2, '2025-05-01', '2025-05-07', 15, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 4 AND retailer_id = 1 AND date_price = '2025-05-01')),
(3, '2025-05-03', '2025-05-09', 20, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 8 AND retailer_id = 1 AND date_price = '2025-05-01')),
(4, '2025-05-04', '2025-05-10', 12, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 14 AND retailer_id = 1 AND date_price = '2025-05-01')),
(5, '2025-05-02', '2025-05-08', 5,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 5 AND retailer_id = 1 AND date_price = '2025-05-01')),
(6, '2025-05-02', '2025-05-08', 8,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 6 AND retailer_id = 1 AND date_price = '2025-05-01')),
(7, '2025-05-06', '2025-05-12', 25, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 15 AND retailer_id = 1 AND date_price = '2025-05-01')),
(8, '2025-05-05', '2025-05-11', 15, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 16 AND retailer_id = 1 AND date_price = '2025-05-01')),
(9, '2025-05-01', '2025-05-07', 10, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 19 AND retailer_id = 1 AND date_price = '2025-05-01')),
(10,'2025-05-03', '2025-05-09', 18, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 20 AND retailer_id = 1 AND date_price = '2025-05-01')),
(11,'2025-05-01', '2025-05-07', 5,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 10 AND retailer_id = 1 AND date_price = '2025-05-01')),
(12,'2025-05-04', '2025-05-10', 7,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 3 AND retailer_id = 1 AND date_price = '2025-05-01')),
(13,'2025-05-02', '2025-05-08', 6,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 7 AND retailer_id = 1 AND date_price = '2025-05-01')),
(14,'2025-05-05', '2025-05-11', 10, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 12 AND retailer_id = 1 AND date_price = '2025-05-01')),
(15,'2025-05-06', '2025-05-12', 12, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 18 AND retailer_id = 1 AND date_price = '2025-05-01')),
(16,'2025-05-04', '2025-05-10', 9,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 13 AND retailer_id = 1 AND date_price = '2025-05-01')),
(17,'2025-05-03', '2025-05-09', 14, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 17 AND retailer_id = 1 AND date_price = '2025-05-01')),
(18,'2025-05-06', '2025-05-12', 6,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 11 AND retailer_id = 1 AND date_price = '2025-05-01')),
(19,'2025-05-05', '2025-05-11', 11, '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 2 AND retailer_id = 1 AND date_price = '2025-05-01')),
(20,'2025-05-06', '2025-05-12', 5,  '2025-05-01', (SELECT price_history_id FROM PRICE_HISTORY WHERE product_id = 6 AND retailer_id = 1 AND date_price = '2025-05-01'));
