DROP TABLE IF EXISTS ORDER_ITEMS;
DROP TABLE IF EXISTS ORDERS;
DROP TABLE IF EXISTS PRODUCTS;

CREATE TABLE PRODUCTS (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	description TEXT,
    manufacturer VARCHAR(50),
    cost DECIMAL(6,2),
    created_date DATE,
    created_by VARCHAR(50),
    modified_date DATE,
    modified_by VARCHAR(50)
 );

CREATE TABLE ORDERS (
	id SERIAL PRIMARY KEY,
	notes VARCHAR(500),
    expected_delivery_date DATE,
    actual_delivery_date DATE,
    created_date DATE,
    created_by VARCHAR(50),
    modified_date DATE,
    modified_by VARCHAR(50)
 );

CREATE TABLE ORDER_ITEMS (
	id SERIAL PRIMARY KEY,
	order_id INT,
	product_id INT,
	quantity INT,
	created_date DATE,
    created_by VARCHAR(50),
    modified_date DATE,
    modified_by VARCHAR(50),
 	FOREIGN KEY (order_id) REFERENCES ORDERS(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES PRODUCTS(id) ON DELETE CASCADE
);