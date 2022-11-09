package skit.project.bill_payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import skit.project.bill_payment.DTO.CustomerDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.CustomerEntity;
import skit.project.bill_payment.helper.JwtUtil;
import skit.project.bill_payment.model.JwtRequest;
import skit.project.bill_payment.model.JwtResponse;
import skit.project.bill_payment.serviceImpl.CustomerDetailService;

@RestController
public class CustomerController {

	@Autowired
	AuthenticationManager authenticationManager;

    @Autowired 
	@Qualifier("customerdetailservice")
	CustomerDetailService customerDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/customerlogin")
	public ResponseEntity<?> genrateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getName(), jwtRequest.getPassword()));

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		} catch (Exception e) {
			throw new Exception("Invalid User");
		}

		UserDetails userDetails = this.customerDetailService.loadUserByUsername(jwtRequest.getName());

		String token = this.jwtUtil.generateToken(userDetails);

		System.out.println("JWT Token " + token);

		return ResponseEntity.ok(new JwtResponse(token));
	}


	@GetMapping("/welcome")
	public String welcomeString(){
      return "Hi this is welcome string and this is accessed through token genrated";
	}

	
	
   
    @GetMapping("/getcustomer")
    public ResponseEntity<CustomerDTO> getcustomer(@RequestParam("id") int id) {
       CustomerDTO customer = customerDetailService.getCustomerById(id);
       return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
    }
    
    
  
    @GetMapping("/getcustomers")
    public Page<CustomerDTO> findallcustomers(@RequestParam("start")int start,@RequestParam("pageSize") int pageSize){
        return customerDetailService.getAllCustomers(start,pageSize);
    }

    @PostMapping("/savecustomer")
    public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomerDTO customer) throws DuplicateEntryException {
        CustomerEntity customerEntity = new CustomerEntity();
        try {
                System.out.println(customerDetailService.customerValidation(customer.getEmail()));
                customerEntity = customerDetailService.saveCustomer(customer);
        }
        catch (DuplicateEntryException e) {
            System.out.println(e);
            throw e;
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<CustomerEntity>(customerEntity,HttpStatus.OK) ; 
        
        
    }
    
   
   
    @DeleteMapping("/deletecustomer")
    public String deleteCustomer(@RequestParam("id") int id) {
           customerDetailService.deleteCustomer(id);
           return "Deleted Succesfully";
    }
    
    @PutMapping("/updatecustomer")
    public ResponseEntity<CustomerEntity> updateCustomer(@RequestParam("id") int id,@RequestParam("fName") String fName,@RequestParam("lName") String lName,@RequestParam("gender") char gender,@RequestParam("password") String password){
        CustomerEntity customerEntity = new CustomerEntity();
        try {
                customerEntity = customerDetailService.updateCustomer(id,fName,lName,gender,password);
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<CustomerEntity>(customerEntity,HttpStatus.OK) ;    
    }
    
    @PutMapping("/changepassword")
    public ResponseEntity<CustomerEntity> updatePassword(@RequestParam("id") int id,@RequestParam("password") String password){
        CustomerEntity customerEntity = new CustomerEntity();
        try {
                customerEntity = customerDetailService.changeCustomerPassword(id,password);
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<CustomerEntity>(customerEntity,HttpStatus.OK) ;    
    }	
}


