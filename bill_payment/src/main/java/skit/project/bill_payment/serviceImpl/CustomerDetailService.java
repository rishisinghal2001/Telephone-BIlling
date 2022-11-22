package skit.project.bill_payment.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import skit.project.bill_payment.DTO.CustomerDTO;
import skit.project.bill_payment.DTO.OrgnisationDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.common.OrikaObjectMapper;
import skit.project.bill_payment.entity.CustomerEntity;
import skit.project.bill_payment.repository.CustomerRepository;
import skit.project.bill_payment.service.ICustomerDetailService;


@Service("customerdetailservice")
public class CustomerDetailService implements  UserDetailsService , ICustomerDetailService {

    @Autowired
    @Qualifier("customerrepository")
    CustomerRepository customerRepository;
    
    @Autowired
    @Qualifier("orgnisationdetailservice")
    OrgnisationDetailService orgnisationDetailService;
    
    @Autowired
    OrikaObjectMapper orikaObjectMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List <CustomerDTO> customerList = new ArrayList<CustomerDTO>();
        customerList = getAllCustomers(0,199).getContent();
        for(int i=0;i<customerList.size();i++) {
            if(customerList.get(i).getEmail().equals(email))
                return new User(customerList.get(i).getEmail(),customerList.get(i).getUserPassword(), new ArrayList<>());
        }
        
        List <OrgnisationDTO> orgnisationList = new ArrayList<OrgnisationDTO>();
        orgnisationList = orgnisationDetailService.getAllOrgnisations(0,199).getContent();
        for(int i=0;i<orgnisationList.size();i++) {
            if(orgnisationList.get(i).getEmail().equals(email))
                return new User(orgnisationList.get(i).getEmail(),orgnisationList.get(i).getOrgnPassword(), new ArrayList<>());
        }
        
        throw new UsernameNotFoundException("Customer not found !!");
        
    }

    @Override
    public Page<CustomerDTO> getAllCustomers(int start, int pageSize) {
        Pageable pageable =  PageRequest.of(start, pageSize);
        Page<CustomerEntity> pageCustomerEntity = customerRepository.findAll(pageable);
        List<CustomerDTO> customerList = new ArrayList<>();
        for (int i = 0; i < pageCustomerEntity.getContent().size() ; i++) {
           CustomerDTO customer = new CustomerDTO();
           if(pageCustomerEntity.getContent().get(i).isDelete()==false) {
               customer=orikaObjectMapper.getMapper().map(pageCustomerEntity.getContent().get(i), CustomerDTO.class );   
               customerList.add(customer);                     
           }
        }
        System.out.println();
        return new PageImpl<>(customerList, pageable, pageCustomerEntity.getTotalElements());
    }

    @Override
    public CustomerEntity saveCustomer(CustomerDTO customer) {
        CustomerEntity customerEntity = new CustomerEntity();
                customerEntity = orikaObjectMapper.getMapper().map(customer, CustomerEntity.class);
                
                
                System.out.println("Customer is saved sucessfully");
                
                
                return customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDTO getCustomerById(long phoneNo) {
        CustomerDTO customer = new CustomerDTO();
        Optional <CustomerEntity> customerOp = customerRepository.findById(getCustomerIdByPhoneNo(phoneNo));
        if (customerOp.isPresent()) {
            CustomerEntity customerEntity = customerOp.get();
            
            if(customerEntity.isDelete()== true) {
               System.out.println("Id doesnt exit");    
               }
            else {
                customer= orikaObjectMapper.getMapper().map(customerEntity,CustomerDTO.class);
                }
            }
        else
        {
            System.out.println("Customer not existed");
        }
        return customer;
    }

    @Override
    public String customerValidation(long phoneNo) throws DuplicateEntryException {
        List <CustomerDTO> customerList = new ArrayList<CustomerDTO>();
        customerList = getAllCustomers(0,199).getContent();
        for(int i=0;i<customerList.size();i++) {
            if(customerList.get(i).getPhoneNo()==phoneNo)
                throw new DuplicateEntryException("Phone no Already Exists ");
        }
        return "Entry Saved Sucessfully";   
    }

    @Override
    public void deleteCustomer(long phoneNo) {
        Optional <CustomerEntity> custoOp = customerRepository.findById(getCustomerIdByPhoneNo(phoneNo));
        if (custoOp.isPresent()) {
            Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
            CustomerEntity customerEntity = custoOp.get();
            customerEntity.setDelete(true);
            customerEntity.setLastModifiedDate(lastModifiedDate);
            customerRepository.save(customerEntity);
            System.out.println("Deletion Completed"); 
        }
        else {
            System.out.println("ID doesnt exit");
        }
    }

    @Override
    public CustomerEntity updateCustomer(long phoneNo,String fName, String lName,char gender,String orgnName,String password ) {
        CustomerDTO customerDTO = getCustomerById(getCustomerIdByPhoneNo(phoneNo));
        CustomerEntity customerEntity = new CustomerEntity();
        if(customerDTO==null)
            System.out.println("Wrong Customer ID");
        else {
            customerEntity = orikaObjectMapper.getMapper().map(customerDTO,CustomerEntity.class);
            Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
            customerEntity.setFirstName(fName);
            customerEntity.setLastName(lName);
            customerEntity.setGender(gender);
            customerEntity.setUserPassword(password);
            customerEntity.setOrgnisationName(orgnName);
            customerEntity.setLastModifiedDate(lastModifiedDate);
            customerRepository.save(customerEntity);
            System.out.println("Sucessfully Changed");
        }
        
        return customerEntity;
    }

    @Override
    public CustomerEntity changeCustomerPassword(long phoneNo ,  String password) {
        CustomerDTO customerDTO =getCustomerById(getCustomerIdByPhoneNo(phoneNo));
        CustomerEntity customerEntity = new CustomerEntity();
        if(customerDTO==null)
            System.out.println("Wrong Customer ID");
        else {
            customerEntity = orikaObjectMapper.getMapper().map(customerDTO,CustomerEntity.class);
            Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
            customerEntity.setUserPassword(password);
            customerEntity.setLastModifiedDate(lastModifiedDate);
            customerRepository.save(customerEntity);
            System.out.println("Sucessfully Changed");
        }
        
        return customerEntity;
    }

    @Override
    public int getCustomerIdByPhoneNo(long phoneNo) {
        int id=0;
        List <CustomerDTO> customerList = new ArrayList<CustomerDTO>();
        customerList = getAllCustomers(0,199).getContent();
        for(int i=0;i<customerList.size();i++) {
            if(customerList.get(i).getPhoneNo()==phoneNo)
               id=customerList.get(i).getCustomerId(); 
        }
        return id;   
   
    }
    
    @Override
    public long getPhoneNoByEmail(String email) {
        long phoneNo=0;
        List <CustomerDTO> customerList = new ArrayList<CustomerDTO>();
        customerList = getAllCustomers(0,199).getContent();
        for(int i=0;i<customerList.size();i++) {
            if(customerList.get(i).getEmail().equals(email))
               phoneNo=customerList.get(i).getPhoneNo(); 
        }
        return phoneNo;   
   
    }

    public Page<CustomerDTO> getAllCustomersByOrgnisationName(String name, int start, int pageSize) {
        Pageable pageable =  PageRequest.of(start, pageSize);
        Page<CustomerEntity> pageCustomerEntity = customerRepository.findAll(pageable);
        List<CustomerDTO> customerList = new ArrayList<>();
        for (int i = 0; i < pageCustomerEntity.getContent().size() ; i++) {
           CustomerDTO customer = new CustomerDTO();
           if(pageCustomerEntity.getContent().get(i).isDelete()==false && pageCustomerEntity.getContent().get(i).getOrgnisationName().equals(name)) {
               customer=orikaObjectMapper.getMapper().map(pageCustomerEntity.getContent().get(i), CustomerDTO.class );   
               customerList.add(customer);                     
           }
        }
        System.out.println();
        return new PageImpl<>(customerList, pageable, pageCustomerEntity.getTotalElements());
    
    }
    
    
    


}
