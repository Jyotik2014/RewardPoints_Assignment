----- create Customers and transitions table schema -------------
DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
	id INT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);

DROP TABLE IF EXISTS transactions;
CREATE TABLE transactions (
	id INT PRIMARY KEY,
	customer_id INT,
    transaction_date Date,
    amount DECIMAL(10,2),
    FOREIGN KEY(customer_id) REFERENCES customers(id)  
);