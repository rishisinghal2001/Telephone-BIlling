package skit.project.bill_payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import skit.project.bill_payment.DTO.BillDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.BillEntity;
import skit.project.bill_payment.serviceImpl.BillService;

@RestController
public class BillController {
    

    @Autowired 
    @Qualifier("billservice")
    BillService billService;

  
    @GetMapping("/getbill")
    public ResponseEntity<BillDTO> getBill(@RequestParam("id") int id) {
       BillDTO bill = billService.getBillById(id);
       return new ResponseEntity<BillDTO>(bill, HttpStatus.OK);
    }
    
    
  
    @GetMapping("/getbills")
    public Page<BillDTO> findallbills(@RequestParam("start")int start,@RequestParam("pageSize") int pageSize){
        return billService.getAllBills(start,pageSize);
    }

    @PostMapping("/savebill")
    public ResponseEntity<BillEntity> saveBill(@RequestBody BillDTO bill) throws DuplicateEntryException {
        BillEntity billEntity = new BillEntity();
        try {
                System.out.println(billService.billValidation(bill.getCustomerId(),bill.getYear(), bill.getMonth()));
                billEntity = billService.saveBill(bill);
        }
        catch (DuplicateEntryException e) {
            System.out.println(e);
            throw e;
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<BillEntity>(billEntity,HttpStatus.OK) ; 
        
        
    }
    
   
   
    @DeleteMapping("/deletebill")
    public String deleteBill(@RequestParam("id") int id) {
           billService.deleteBill(id);
           return "Deleted Succesfully";
    }
    
}
