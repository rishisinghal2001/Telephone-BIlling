package skit.project.bill_payment.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import skit.project.bill_payment.DTO.CustomerDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.CustomerEntity;


public interface ICustomerDetailService {
 
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    public Page<CustomerDTO> getAllCustomers(int start, int pageSize);
    public CustomerEntity saveCustomer(CustomerDTO user);
	public CustomerDTO getCustomerById(long phoneNo);
	public String customerValidation(long phoneno) throws DuplicateEntryException ;
	public void deleteCustomer(long phoneNo);
	public CustomerEntity updateCustomer(long phoneNo, String fName, String lName, char gender, String orgnName, String password  );
	public CustomerEntity changeCustomerPassword(long phoneNo , String password);
	public int getCustomerIdByPhoneNo(long phoneNo);
	public long getPhoneNoByEmail(String email);
	
}
