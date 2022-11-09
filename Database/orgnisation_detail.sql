use bill_payment;
 show create table orgnisation_detail;
 select * from orgnisation_detail;
 
 CREATE TABLE `orgnisation_detail` (
   `orgnisation_id`  int NOT NULL AUTO_INCREMENT,
   `orgnisation_name` varchar(255) DEFAULT NULL,
   `email` varchar(255) DEFAULT NULL,
   `orgn_password` varchar(255) DEFAULT NULL,
   `token` varchar(255) DEFAULT NULL,
   `is_deleted` tinyint(1) DEFAULT false,
   `add_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `added_by` varchar(255) DEFAULT NULL,
   `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `last_modified_by` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`orgnisation_id`),
   UNIQUE KEY `name` (`orgnisation_name`,`is_deleted`,`email`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
  ALTER TABLE orgnisation_detail
 RENAME COLUMN is_deleted to is_delete;