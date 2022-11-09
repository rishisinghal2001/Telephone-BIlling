use bill_payment;
 show create table admin_detail;
 insert into admin_detail (first_name,Last_name,gender,email)
 values ('rishi','sinhal','m','344ygregsfdvsgrsfdafdhikushdf');
 select * from admin_detail;
 
 CREATE TABLE `orgnisation_detail` (
   `orginsation_id`  int NOT NULL AUTO_INCREMENT,
   `orgnisation_name` varchar(255) DEFAULT NULL,
   `email` varchar(255) DEFAULT NULL,
   `orgn_password` varchar(255) DEFAULT NULL,
   `is_deleted` tinyint(1) DEFAULT false,
   `add_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `added_by` varchar(255) DEFAULT NULL,
   `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `last_modified_by` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`admin_id`),
   UNIQUE KEY `email` (`email`,`is_deleted`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 