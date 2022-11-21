package skit.project.bill_payment.service;

import org.springframework.data.domain.Page;

import skit.project.bill_payment.DTO.PaymentDTO;
import skit.project.bill_payment.entity.PaymentEntity;

public interface IPaymentDetailService {

    public Page<PaymentDTO> getAllPayments(int start, int pageSize);
    public PaymentEntity savePayment(PaymentDTO  payment);
    public PaymentDTO getPaymentById(int  id);
    public PaymentDTO getPaymentByBillId(int  id);
    public Page<PaymentDTO> getAllPaymentsByCustomerId(int customerId , int start, int pageSize);
        
}
