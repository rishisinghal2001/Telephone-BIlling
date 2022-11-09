package skit.project.bill_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skit.project.bill_payment.entity.CustomerEntity;

@Repository("customerrepository")
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
   
}
