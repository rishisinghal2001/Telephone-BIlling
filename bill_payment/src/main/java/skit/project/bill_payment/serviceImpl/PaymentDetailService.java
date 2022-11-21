package skit.project.bill_payment.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import skit.project.bill_payment.DTO.BillDTO;
import skit.project.bill_payment.DTO.PaymentDTO;
import skit.project.bill_payment.common.OrikaObjectMapper;
import skit.project.bill_payment.entity.PaymentEntity;
import skit.project.bill_payment.repository.PaymentRepository;
import skit.project.bill_payment.service.IPaymentDetailService;


@Service("paymentdetailservice")
public class PaymentDetailService implements IPaymentDetailService{
    
    @Autowired
    @Qualifier("paymentrepository")
    PaymentRepository paymentRepository;
    
    @Autowired
    BillService billService;
    
    @Autowired
    OrikaObjectMapper orikaObjectMapper;


    @Override
    public Page<PaymentDTO> getAllPayments(int start,int pageSize) {
        Pageable pageable =  PageRequest.of(start, pageSize);
        Page<PaymentEntity> pagePaymentEntity = paymentRepository.findAll(pageable);
        List<PaymentDTO> paymentList = new ArrayList<>();
        for (int i = 0; i < pagePaymentEntity.getContent().size() ; i++) {
           PaymentDTO payment = new PaymentDTO();
           if(pagePaymentEntity.getContent().get(i).isDelete()==false) {
               payment=orikaObjectMapper.getMapper().map(pagePaymentEntity.getContent().get(i), PaymentDTO.class );   
               paymentList.add(payment);                     
           }
        }
        System.out.println();
        return new PageImpl<>(paymentList, pageable, pagePaymentEntity.getTotalElements());
    }

    @Override
    public PaymentEntity savePayment(PaymentDTO  payment) {
        PaymentEntity paymentEntity = new PaymentEntity();
                paymentEntity = orikaObjectMapper.getMapper().map(payment, PaymentEntity.class);
                
                BillDTO bill= billService.getBillById(payment.getBillId());
                
                bill.setStatus(true);
                
                billService.saveBill(bill);  
                
                System.out.println("Payment is saved sucessfully");
                
                
                return paymentRepository.save(paymentEntity);
    }

    @Override
    public PaymentDTO getPaymentById(int  id){
        PaymentDTO payment = new PaymentDTO();
        Optional <PaymentEntity> paymentOp = paymentRepository.findById(id);
        if (paymentOp.isPresent()) {
            PaymentEntity paymentEntity = paymentOp.get();
            
            if(paymentEntity.isDelete()== true) {
               System.out.println("Id doesnt exit");    
               }
            else {
                payment= orikaObjectMapper.getMapper().map(paymentEntity,PaymentDTO.class);
                }
            }
        else
        {
            System.out.println("Payment is not existed");
        }
        return payment;
    }

    
    @Override
    public PaymentDTO getPaymentByBillId(int id) {
        List<PaymentEntity> paymentEntity = paymentRepository.findAll();
        for (int i = 0; i < paymentEntity.size() ; i++) {
           PaymentDTO payment = new PaymentDTO();
           if(paymentEntity.get(i).isDelete()==false && paymentEntity.get(i).getBillId()==id) {
               payment=orikaObjectMapper.getMapper().map(paymentEntity.get(i), PaymentDTO.class );   
               return payment;                     
           }
        }
        System.out.println();
        return null;
    }

    @Override
    public Page<PaymentDTO> getAllPaymentsByCustomerId(int customerId , int start, int pageSize) {
        Pageable pageable =  PageRequest.of(start, pageSize);
        Page<PaymentEntity> pagePaymentEntity = paymentRepository.findAll(pageable);
        List<PaymentDTO> paymentList = new ArrayList<>();
        for (int i = 0; i < pagePaymentEntity.getContent().size() ; i++) {
           PaymentDTO payment = new PaymentDTO();
           if(pagePaymentEntity.getContent().get(i).isDelete()==false && pagePaymentEntity.getContent().get(i).getCustomerId()==customerId) {
               payment=orikaObjectMapper.getMapper().map(pagePaymentEntity.getContent().get(i), PaymentDTO.class );   
               paymentList.add(payment);                     
           }
        }
        System.out.println();
        return new PageImpl<>(paymentList, pageable, pagePaymentEntity.getTotalElements());
    }

}
