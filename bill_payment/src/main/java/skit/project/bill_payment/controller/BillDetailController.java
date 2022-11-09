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

import skit.project.bill_payment.DTO.BillDetailDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.BillDetailEntity;
import skit.project.bill_payment.serviceImpl.BillDetailService;

@RestController
public class BillDetailController {
    

    @Autowired 
    @Qualifier("billdetailservice")
    BillDetailService billDetailService;

  
    @GetMapping("/getbilldetail")
    public ResponseEntity<BillDetailDTO> getBillDetail(@RequestParam("id") int id) {
       BillDetailDTO billDetail = billDetailService.getBillDetailById(id);
       return new ResponseEntity<BillDetailDTO>(billDetail, HttpStatus.OK);
    }
    
    
  
    @GetMapping("/getbilldetails")
    public Page<BillDetailDTO> findallbilldetails(@RequestParam("start")int start,@RequestParam("pageSize") int pageSize){
        return billDetailService.getAllBillDetails(start,pageSize);
    }

    @PostMapping("/savebilldetail")
    public ResponseEntity<BillDetailEntity> saveBillDetail(@RequestBody BillDetailDTO billDetail) throws DuplicateEntryException {
        BillDetailEntity billDetailEntity = new BillDetailEntity();
        try {
                System.out.println(billDetailService.billDetailValidation(billDetail.getBillId()));
                billDetailEntity = billDetailService.saveBillDetail(billDetail);
        }
        catch (DuplicateEntryException e) {
            System.out.println(e);
            throw e;
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<BillDetailEntity>(billDetailEntity,HttpStatus.OK) ; 
        
        
    }
    
   
   
    @DeleteMapping("/deletebilldetail")
    public String deleteBill(@RequestParam("id") int id) {
           billDetailService.deleteBillDetail(id);
           return "Deleted Succesfully";
    }
    
}
