 use bill_payment;
 show create table payment_detail;
 select * from payment_detail;
 
 
 CREATE TABLE `payment_detail` (
   `payment_id`  int NOT NULL AUTO_INCREMENT,
	`customer_id` int not  NULL,
    `bill_id` int not  NULL,
    `mode` varchar(255) not  NULL,
   `is_deleted` tinyint(1) DEFAULT false,
   `add_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `added_by` varchar(255) DEFAULT NULL,
   `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `last_modified_by` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`payment_id`),
   FOREIGN KEY (bill_id) REFERENCES bill(bill_id),
   FOREIGN KEY (customer_id) REFERENCES customer_detail(customer_id)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 ALTER TABLE payment_detail
ADD amount int;
ALTER TABLE payment_detail
 RENAME COLUMN is_deleted to is_delete;