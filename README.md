Customer Reward Point Calculation

1.1 Customer Reward Point Calculation have customer and transaction CRUD apis along with Reward calculation APIS
1.2 Classes in application 
   a. Main Class RewardApplication
    - Controller  
      - CutomerController
      - TransactionController
      - RewardPointsController 
   b. Service interface and class    
      - CutomerService and CutomerServiceImpl (CRUD for Customers)
      - TransactionService and TransactionServiceImpl (CRUD for transactions)
      - RewardPointsService and RewardPointsServiceImpl (have logic to calculate reward points)
   c. Entity (have one to Many relation between Customer and transaction
      - Customer
      - Transaction  
   d. Repository 
      - CustomerRepository
      - TransactionRepository
1.3 Configuration Files
    - application.properties (DB details/credentials and application configurations)
    - schema.sql(table creation schema for Customers and transactions)
    - data.sql (Data used for teting)
	
2. Used h2 DB , created two tables Customers and  Transactions where customer id used as foreign key.  

3. I have created below 4 Apis ,
http://localhost:8080/reward-points/totalReward/{carId} : This will return Total rewards for given customer
http://localhost:8080/reward-points/monthlyReward/{carId}/{yearMonth} : This will return Monthly rewards for given customer
http://localhost:8080/reward-points/totalRewardAll : This will return Total rewards for all customers
http://localhost:8080/reward-points/monthlyRewardAll/{yearMonth} : This will return Monthly rewards for all customers

Sample URL:
http://localhost:8080/reward-points/totalReward/1
http://localhost:8080/reward-points/monthlyReward/2/2024-07
http://localhost:8080/reward-points/totalRewardAll
http://localhost:8080/reward-points/monthlyRewardAll/2024-07

Basic validations applied for Cust id , transaction id and yearMonth

4. Junit testcases written for controller using TestRestTemplate


5. Data Used for testing
Schema:
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
Data:
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

API Response for all customers total rewards
http://localhost:8080/reward-points/totalRewardAll
{
  "shyam": 930,
  "jaya": 880,
  "Ram": 460
}
API Response for all customers Monthly rewards
http://localhost:8080/reward-points/monthlyRewardAll/2024-07
{
  "shyam:2024-07": 20,
  "Ram:2024-07": 260,
  "jaya:2024-07": 0
}

http://localhost:8080/reward-points/monthlyRewardAll/2024-17
Please provide Valid year Month YYYY-MM eg.2024-07

