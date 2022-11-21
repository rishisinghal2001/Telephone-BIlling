package skit.project.bill_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import skit.project.bill_payment.entity.PaymentEnity;

public interface PaymentRepository extends JpaRepository<PaymentEnity, Integer> {

}
