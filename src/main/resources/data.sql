----Inserting Data to Customers---
INSERT INTO customers (id,name,email) VALUES (1,'Ram','ram@abc.com');
INSERT INTO customers (id,name,email) VALUES (2,'shyam','shyam@abc.com');
INSERT INTO customers (id,name,email) VALUES (3,'jaya','jaya@abc.com');

-----Inserting Data to transactions---
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (1,1,'2024-09-15',100);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (2,2,'2024-07-15',70);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (3,3,'2024-08-15',120);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (4,1,'2024-08-15',150);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (5,2,'2024-08-15',200);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (6,3,'2024-09-15',250);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (7,1,'2024-07-15',120);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (8,2,'2024-08-15',300);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (9,3,'2024-09-15',170);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (10,1,'2024-07-15',160);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (11,2,'2024-08-15',180);
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (12,3,'2024-09-15',200);