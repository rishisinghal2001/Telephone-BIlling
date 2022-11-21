use bill_payment;
 show create table bill;
 insert into bill (first_name,Last_name,gender,email)
 values ('rishi','sinhal','m','344ygregsfdvsgrsfdafdhikushdf');
 select * from bill;
 
 CREATE TABLE `bill` (
   `bill_id`  int NOT NULL AUTO_INCREMENT,
   `customer_id`  int NOT NULL,
   `year` int not NULL,
   `month` varchar(255) not NULL,
   `status` tinyint(1) DEFAULT false,
   `amount`  int NOT NULL,
   `bill_genrate_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `bill_payment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `is_deleted` tinyint(1) DEFAULT false,
   `add_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `added_by` varchar(255) DEFAULT NULL,
   `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `last_modified_by` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`bill_id`),
   FOREIGN KEY (customer_id) REFERENCES customer_detail(customer_id)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 
 ALTER TABLE bill
 RENAME COLUMN is_deleted to is_delete;
 
 ALTER TABLE bill
ADD telephone_amount int;
ALTER TABLE bill
ADD newpaper_amount int;

ALTER TABLE bill
 RENAME COLUMN newpaper_amount to newspaper_amount;

alter table bill
modify column amount int;
