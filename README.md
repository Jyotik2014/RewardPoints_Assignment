##<b>Customer Reward Point Calculation</b>

1.1 Customer Reward Point Calculation have customer and transaction CRUD apis along with Reward calculation APIS</br>
1.2 Classes in application</br>
   a. Main Class RewardApplication<br>
    - Controllers: <br>
      - CutomerController<br>
      - TransactionController<br>
      - RewardPointsController<br> 
   b. Service interface and class    
      - CutomerService and CutomerServiceImpl (CRUD for Customers)<br>
      - TransactionService and TransactionServiceImpl (CRUD for transactions)<br>
      - RewardPointsService and RewardPointsServiceImpl (have logic to calculate reward points)<br>
   c. Entity (have one to Many relation between Customer and transaction<br>
      - Customer<br>
      - Transaction  
   d. Repository<br> 
      - CustomerRepository<br>
      - TransactionRepository<br>
1.3 Configuration Files</br>
    - application.properties (DB details/credentials and application configurations)</br>
    - schema.sql(table creation schema for Customers and transactions)</br>
    - data.sql (Data used for teting)</br>
	
2. Used h2 DB , created two tables Customers and  Transactions where customer id used as foreign key. </br> 

3. I have created below 4 Apis ,</br>
http://localhost:8080/reward-points/totalReward/{custId} : This will return Total rewards for given customer</br>
http://localhost:8080/reward-points/monthlyReward/{custId}/{yearMonth} : This will return Monthly rewards for given customer</br>
http://localhost:8080/reward-points/totalRewardAll : This will return Total rewards for all customers</br>
http://localhost:8080/reward-points/monthlyRewardAll/{yearMonth} : This will return Monthly rewards for all customers</br>

<b>##Sample URL:</b></br>
http://localhost:8080/reward-points/totalReward/1</br>
http://localhost:8080/reward-points/monthlyReward/2/2024-07</br>
http://localhost:8080/reward-points/totalRewardAll</br>
http://localhost:8080/reward-points/monthlyRewardAll/2024-07</br>

Basic validations applied for Cust id , transaction id and yearMonth</br>

4. Junit testcases written for controller using TestRestTemplate</br>
Please Run code coverage after running application as DB conflicts may occur as using in memory he DB which is initialized after running application
<br>

5. Data Used for testing</br>
Schema:</br>
----- create Customers and transitions table schema -------------</br>
DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
	id INT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);
</br>
DROP TABLE IF EXISTS transactions;
CREATE TABLE transactions (
	id INT PRIMARY KEY,
	customer_id INT,
    transaction_date Date,
    amount DECIMAL(10,2),
    FOREIGN KEY(customer_id) REFERENCES customers(id)  
);
<br>Data:
----Inserting Data to Customers---
INSERT INTO customers (id,name,email) VALUES (1,'Ram','ram@abc.com');<br>
INSERT INTO customers (id,name,email) VALUES (2,'shyam','shyam@abc.com');<br>
INSERT INTO customers (id,name,email) VALUES (3,'jaya','jaya@abc.com');<br>
</br>
-----Inserting Data to transactions---
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (1,1,'2024-09-15',100);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (2,2,'2024-07-15',70);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (3,3,'2024-08-15',120);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (4,1,'2024-08-15',150);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (5,2,'2024-08-15',200);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (6,3,'2024-09-15',250);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (7,1,'2024-07-15',120);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (8,2,'2024-08-15',300);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (9,3,'2024-09-15',170);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (10,1,'2024-07-15',160);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (11,2,'2024-08-15',180);<br>
INSERT INTO transactions (id,customer_id,transaction_date,amount) VALUES (12,3,'2024-09-15',200);<br>
</br>
<b>#API Response for all customers total rewards<b><br>
<b>http://localhost:8080/reward-points/totalRewardAll</b><br>
{
  "shyam": 930,
  "jaya": 880,
  "Ram": 460
}
<br></br>
<b>#API Response for all customers Monthly rewards<b><br>
<b>http://localhost:8080/reward-points/monthlyRewardAll/2024-07</b><br>
{
  "shyam:2024-07": 20,
  "Ram:2024-07": 260,
  "jaya:2024-07": 0
}
<br><br>
<b>http://localhost:8080/reward-points/monthlyRewardAll/2024-17</b><br>
Please provide Valid year Month YYYY-MM eg.2024-07
<br><br>
<b>http://localhost:8080/reward-points/totalReward/A</b><br>
Arugument passed is not Valid for Customer ID use number and for year and Month use YYYY-MM format
<br><br>
<b>http://localhost:8080/reward-points/monthlyReward/1/2024-0A</b><br>
Please provide Valid year Month YYYY-MM eg.2024-07
<br><br>

<b><http://localhost:8080/reward-points/monthlyRewardAll/A024-07</b><br>
Please provide Valid year Month YYYY-MM eg.2024-07
<br>
<b>http://localhost:8080/reward-points/monthlyReward/A/2024-09</b><br>
Arugument passed is not Valid for Customer ID use number and for year and Month use YYYY-MM format
<br>
<br>
7. <b>Customer and Transactions CRUD APIS<b> <br>
http://localhost:8080/addCustomer<br>
http://localhost:8080/customers<br>
http://localhost:8080/updateCustomer/{custId}<br>
http://localhost:8080//deleteCustomers/{custId}
<br><br>
http://localhost:8080/addTransaction<br>
http://localhost:8080/transactions<br>
http://localhost:8080/updateTransaction/{transactionId}/{custId}<br>
http://localhost:8080//deleteTransactions/{id}<br>

API responses:<br>
<b>http://localhost:8080/customers<b><br>
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
]<br>
<b>http://localhost:8080/transactions<b><br>
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

