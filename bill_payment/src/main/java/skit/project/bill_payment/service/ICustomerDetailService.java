package skit.project.bill_payment.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import skit.project.bill_payment.DTO.CustomerDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.CustomerEntity;


public interface ICustomerDetailService {
 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    public Page<CustomerDTO> getAllCustomers(int start, int pageSize);
    public CustomerEntity saveCustomer(CustomerDTO user);
	public CustomerDTO getCustomerById(int  id);
	public String customerValidation(String email) throws DuplicateEntryException ;
	public void deleteCustomer(int id);
	public CustomerEntity updateCustomer(int id, String fName, String lName, char gender, String password );
	public CustomerEntity changeCustomerPassword(int id , String password);
	
}
