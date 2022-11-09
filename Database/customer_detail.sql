use bill_payment;
 show create table customer_detail;
 insert into customer_detail(first_name,Last_name,gender,email,user_password,user_name)
 values ('ewfukdsd','esfdjaiesdf','f','sfdssefd@gamai.com','wtesdgxdfbkjbn','sfsdgrffdsstasdfl');
 select * from customer_detail;
 
 CREATE TABLE `customer_detail` (
   `customer_id`  int NOT NULL AUTO_INCREMENT,
   `first_name` varchar(255) DEFAULT NULL,
   `last_name` varchar(255) DEFAULT NULL,
   `gender` char(1) DEFAULT NULL,
   `email` varchar(255) DEFAULT NULL,
   `phone_no` bigint NOT NULL ,
   `orgnisation_name` varchar(255) Not NULL,
   `user_password` varchar(255) DEFAULT NULL,
   `custo_token` varchar(255) DEFAULT NULL,
   `is_deleted` tinyint(1) DEFAULT false,
   `add_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `added_by` varchar(255) DEFAULT NULL,
   `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `last_modified_by` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`customer_id`),
   UNIQUE KEY `email` (`phone_no`,`is_deleted`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 
 ALTER TABLE customer_detail
 RENAME COLUMN is_deleted to is_delete;
 