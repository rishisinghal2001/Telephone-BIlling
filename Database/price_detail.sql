use bill_payment;
 show create table price_detail;
 insert into price_detail (first_name,Last_name,gender,email)
 values ('rishi','sinhal','m','344ygregsfdvsgrsfdafdhikushdf');
 select * from price_detail;
 
 CREATE TABLE `price_detail` (
   `price_id`  int NOT NULL AUTO_INCREMENT,
   `feature` varchar(255) not  NULL,
   `price` int not  NULL,
   `is_deleted` tinyint(1) DEFAULT false,
   `add_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `added_by` varchar(255) DEFAULT NULL,
   `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `last_modified_by` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`price_id`),
   UNIQUE KEY (`feature`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 
  ALTER TABLE price_detail
 RENAME COLUMN is_deleted to is_delete;