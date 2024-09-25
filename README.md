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
http://localhost:8080/reward-points/totalReward/{custId} : This will return Total rewards for given customer
http://localhost:8080/reward-points/monthlyReward/{custId}/{yearMonth} : This will return Monthly rewards for given customer
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

http://localhost:8080/reward-points/totalReward/A
Arugument passed is not Valid for Customer ID use number and for year and Month use YYYY-MM format

http://localhost:8080/reward-points/monthlyReward/1/2024-0A
Invalid customer ID or YearMonth format,for Customer ID use number and for year and Month use YYYY-MM format

6. Customer and Transactions CRUD APIS
http://localhost:8080/addCustomer
http://localhost:8080/customers
http://localhost:8080/updateCustomer/{custId}
http://localhost:8080//deleteCustomers/{custId}

http://localhost:8080/addTransaction
http://localhost:8080/transactions
http://localhost:8080/updateTransaction/{transactionId}/{custId}
http://localhost:8080//deleteTransactions/{id}

API responses:
http://localhost:8080/customers
[
  {
    "id": 1,
    "name": "Ram",
    "email": "ram@abc.com"
  },
  {
    "id": 2,
    "name": "shyam",
    "email": "shyam@abc.com"
  },
  {
    "id": 3,
    "name": "jaya",
    "email": "jaya@abc.com"
  }
]
http://localhost:8080/transactions
[
  {
    "id": 1,
    "customer": {
      "id": 1,
      "name": "Ram",
      "email": "ram@abc.com"
    },
    "transactionDate": "2024-09-14",
    "amount": 100
  },
  {
    "id": 2,
    "customer": {
      "id": 2,
      "name": "shyam",
      "email": "shyam@abc.com"
    },
    "transactionDate": "2024-07-14",
    "amount": 70
  },
  {
    "id": 3,
    "customer": {
      "id": 3,
      "name": "jaya",
      "email": "jaya@abc.com"
    },
    "transactionDate": "2024-08-14",
    "amount": 120
  },
  {
    "id": 4,
    "customer": {
      "id": 1,
      "name": "Ram",
      "email": "ram@abc.com"
    },
    "transactionDate": "2024-08-14",
    "amount": 150
  },
  {
    "id": 5,
    "customer": {
      "id": 2,
      "name": "shyam",
      "email": "shyam@abc.com"
    },
    "transactionDate": "2024-08-14",
    "amount": 200
  },
  {
    "id": 6,
    "customer": {
      "id": 3,
      "name": "jaya",
      "email": "jaya@abc.com"
    },
    "transactionDate": "2024-09-14",
    "amount": 250
  },
  {
    "id": 7,
    "customer": {
      "id": 1,
      "name": "Ram",
      "email": "ram@abc.com"
    },
    "transactionDate": "2024-07-14",
    "amount": 120
  },
  {
    "id": 8,
    "customer": {
      "id": 2,
      "name": "shyam",
      "email": "shyam@abc.com"
    },
    "transactionDate": "2024-08-14",
    "amount": 300
  },
  {
    "id": 9,
    "customer": {
      "id": 3,
      "name": "jaya",
      "email": "jaya@abc.com"
    },
    "transactionDate": "2024-09-14",
    "amount": 170
  },
  {
    "id": 10,
    "customer": {
      "id": 1,
      "name": "Ram",
      "email": "ram@abc.com"
    },
    "transactionDate": "2024-07-14",
    "amount": 160
  },
  {
    "id": 11,
    "customer": {
      "id": 2,
      "name": "shyam",
      "email": "shyam@abc.com"
    },
    "transactionDate": "2024-08-14",
    "amount": 180
  },
  {
    "id": 12,
    "customer": {
      "id": 3,
      "name": "jaya",
      "email": "jaya@abc.com"
    },
    "transactionDate": "2024-09-14",
    "amount": 200
  }
]

