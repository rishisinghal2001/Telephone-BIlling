package skit.project.bill_payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import skit.project.bill_payment.DTO.PaymentDTO;
import skit.project.bill_payment.entity.PaymentEntity;
import skit.project.bill_payment.serviceImpl.PaymentDetailService;

@RestController 
@CrossOrigin(origins="*")

public class PaymentController {


    @Autowired 
    @Qualifier("paymentdetailservice")
    PaymentDetailService paymentDetailService;

  
    @GetMapping("/getpaymentdetail")
    public ResponseEntity<PaymentDTO> getPaymentDetail(@RequestParam("id") int id) {
       PaymentDTO payment = paymentDetailService.getPaymentByBillId(id);
       return new ResponseEntity<PaymentDTO>(payment, HttpStatus.OK);
    }
    
    
  
    @PostMapping("/savepaymentdetail")
    public ResponseEntity<PaymentEntity> savePaymentDetail(@RequestBody PaymentDTO payment){
        PaymentEntity paymentEntity = new PaymentEntity();
        try {
                paymentEntity = paymentDetailService.savePayment(payment);
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<PaymentEntity>(paymentEntity,HttpStatus.OK) ; 
        
        
    }
    
   
   }
