package skit.project.bill_payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import skit.project.bill_payment.DTO.PriceDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.PriceEntity;
import skit.project.bill_payment.serviceImpl.PriceDetailService;

@RestController
public class PriceController {

   
    @Autowired 
    @Qualifier("pricedetailservice")
    PriceDetailService priceDetailService;

  
    @GetMapping("/getprice")
    public ResponseEntity<PriceDTO> getprice(@RequestParam("id") int id) {
       PriceDTO price = priceDetailService.getPricesById(id);
       return new ResponseEntity<PriceDTO>(price, HttpStatus.OK);
    }
    
    
  
    @GetMapping("/getprices")
    public Page<PriceDTO> findallprices(@RequestParam("start")int start,@RequestParam("pageSize") int pageSize){
        return priceDetailService.getAllPrices(start,pageSize);
    }

    @PostMapping("/saveprice")
    public ResponseEntity<PriceEntity> savePrice(@RequestBody PriceDTO price) throws DuplicateEntryException {
        PriceEntity priceEntity = new PriceEntity();
        try {
                System.out.println(priceDetailService.priceValidation(price.getFeature()));
                priceEntity = priceDetailService.savePrices(price);
        }
        catch (DuplicateEntryException e) {
            System.out.println(e);
            throw e;
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<PriceEntity>(priceEntity,HttpStatus.OK) ; 
        
        
    }
    
   
   
    @DeleteMapping("/deleteprice")
    public String deleteprice(@RequestParam("id") int id) {
           priceDetailService.deletePrice(id);
           return "Deleted Succesfully";
    }
    
    @PutMapping("/changeprice")
    public ResponseEntity<PriceEntity> changeprice(@RequestParam("id") int id,@RequestParam("price") int price){
        PriceEntity priceEntity = new PriceEntity();
        try {
                priceEntity = priceDetailService.changePrice(id,price);
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<PriceEntity>(priceEntity,HttpStatus.OK) ;    
    }   
    
}
