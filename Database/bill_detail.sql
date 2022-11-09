use bill_payment;
 show create table bill_detail;
 insert into bill_detail (first_name,Last_name,gender,email)
 values ('rishi','sinhal','m','344ygregsfdvsgrsfdafdhikushdf');
 select * from bill_detail;
 
 CREATE TABLE `bill_detail` (
   `bill_detail_id`  int NOT NULL AUTO_INCREMENT,
   `bill_id`  int NOT NULL, 
   `price_id`  int NOT NULL, 
   `is_deleted` tinyint(1) DEFAULT false,
   `add_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `added_by` varchar(255) DEFAULT NULL,
   `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `last_modified_by` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`bill_detail_id`),
   FOREIGN KEY (bill_id) REFERENCES bill(bill_id),
   FOREIGN KEY (price_id) REFERENCES price_detail(price_id)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 
  ALTER TABLE bill_detail
 RENAME COLUMN is_deleted to is_delete;