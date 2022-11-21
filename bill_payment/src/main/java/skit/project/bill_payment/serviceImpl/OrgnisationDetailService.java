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
import skit.project.bill_payment.entity.OrgnisationEntity;
import skit.project.bill_payment.repository.CustomerRepository;
import skit.project.bill_payment.repository.OrgnisationRepository;
import skit.project.bill_payment.service.IOrgnisationDetailService;

@Service("orgnisationdetailservice")
public class OrgnisationDetailService  implements UserDetailsService, IOrgnisationDetailService  {
    @Autowired
    @Qualifier("orgnisationrepository")
    OrgnisationRepository orgnisationRepository;
    
    @Autowired
    @Qualifier("customerrepository")
    CustomerRepository customerRepository;
    
    @Autowired
    OrikaObjectMapper orikaObjectMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List <OrgnisationDTO> orgnisationList = new ArrayList<OrgnisationDTO>();
        orgnisationList = getAllOrgnisations(0,199).getContent();
        for(int i=0;i<orgnisationList.size();i++) {
            if(orgnisationList.get(i).getEmail().equals(email))
                return new User(orgnisationList.get(i).getEmail(),orgnisationList.get(i).getOrgnPassword(), new ArrayList<>());
        }
        
        throw new UsernameNotFoundException("Orgnisation not found !!");
    }

    @Override
    public Page<OrgnisationDTO> getAllOrgnisations(int start,int pageSize) {
        Pageable pageable =  PageRequest.of(start, pageSize);
        Page<OrgnisationEntity> pageOrgnisationEntity = orgnisationRepository.findAll(pageable);
        List<OrgnisationDTO> orgnisationList = new ArrayList<>();
        for (int i = 0; i < pageOrgnisationEntity.getContent().size() ; i++) {
           OrgnisationDTO orgnisation = new OrgnisationDTO();
           if(pageOrgnisationEntity.getContent().get(i).isDelete()==false) {
               orgnisation=orikaObjectMapper.getMapper().map(pageOrgnisationEntity.getContent().get(i), OrgnisationDTO.class );   
               orgnisationList.add(orgnisation);                     
           }
        }
        System.out.println();
        return new PageImpl<>(orgnisationList, pageable, pageOrgnisationEntity.getTotalElements());
    }

    @Override
    public OrgnisationEntity saveOrgisation(OrgnisationDTO  orgnisation) {
        OrgnisationEntity orgnisationEntiry = new OrgnisationEntity();
                orgnisationEntiry = orikaObjectMapper.getMapper().map(orgnisation, OrgnisationEntity.class);
                
                
                System.out.println("Orgnisation is saved sucessfully");
                
                
                return orgnisationRepository.save(orgnisationEntiry);
    }

    @Override
    public OrgnisationDTO getOrgnisationById(String email) {
        OrgnisationDTO orgnisation = new OrgnisationDTO();
        Optional <OrgnisationEntity> OrgnisationOp = orgnisationRepository.findById(getOrgnisationIdByEmail(email));
        if (OrgnisationOp.isPresent()) {
            OrgnisationEntity OrgnisationEntity = OrgnisationOp.get();
            
            if(OrgnisationEntity.isDelete()== true) {
               System.out.println("Id doesnt exit");    
               }
            else {
                orgnisation= orikaObjectMapper.getMapper().map(OrgnisationEntity,OrgnisationDTO.class);
                }
            }
        else
        {
            System.out.println("Orgnisation not existed");
        }
        return orgnisation;
    }

    @Override
    public String orgnisationValidation(String email) throws DuplicateEntryException {
        List <OrgnisationDTO> orgnisationList = new ArrayList<OrgnisationDTO>();
        orgnisationList = getAllOrgnisations(0,199).getContent();
        for(int i=0;i<orgnisationList.size();i++) {
            if(orgnisationList.get(i).getEmail().equals(email))
                throw new DuplicateEntryException("Email Already Exists ");
        }
        return "Entry Saved Sucessfully";   
    }

    @Override
    public void deleteOrgnisation(String email) {
        Optional <OrgnisationEntity> orgnisationOp = orgnisationRepository.findById(getOrgnisationIdByEmail(email));
        if (orgnisationOp.isPresent()) {
            Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
            OrgnisationEntity orgnisationEntity = orgnisationOp.get();
            orgnisationEntity.setDelete(true);
            orgnisationEntity.setLastModifiedDate(lastModifiedDate);
            orgnisationRepository.save(orgnisationEntity);
            System.out.println("Deletion Completed"); 
        }
        else {
            System.out.println("ID doesnt exit");
        }
    }
    
    @Override
    public OrgnisationEntity updateOrgnisation(String email,String orgnName, String orgnPass ) {
        OrgnisationDTO OrgnisationDTO = getOrgnisationById(email);
        OrgnisationEntity orgnisationEntity = new OrgnisationEntity();
        if(OrgnisationDTO==null)
            System.out.println("Wrong Customer ID");
        else {
            orgnisationEntity = orikaObjectMapper.getMapper().map(OrgnisationDTO,OrgnisationEntity.class);
            Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
            orgnisationEntity.setOrgnisationName(orgnName);
            orgnisationEntity.setOrgnPassword(orgnPass);
            orgnisationEntity.setLastModifiedDate(lastModifiedDate);
            orgnisationRepository.save(orgnisationEntity);
            System.out.println("Sucessfully Changed");
        }
        
        return orgnisationEntity;
    }

    @Override
    public OrgnisationEntity changeorgnisationPassword(String email ,  String password) {
        OrgnisationDTO orgnisationDTO = getOrgnisationById(email);
        OrgnisationEntity orgnisationEntity = new OrgnisationEntity();
        if(orgnisationDTO==null)
            System.out.println("Wrong Customer ID");
        else {
            orgnisationEntity = orikaObjectMapper.getMapper().map(orgnisationDTO,OrgnisationEntity.class);
            Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
            orgnisationEntity.setOrgnPassword(password);
            orgnisationEntity.setLastModifiedDate(lastModifiedDate);
            orgnisationRepository.save(orgnisationEntity);
            System.out.println("Sucessfully Changed");
        }
        
        return orgnisationEntity;
    }

    @Override
    public Page<CustomerDTO> getAllCustomerOfAOrgnisation(String orgnisation, int start, int pageSize) {
        Pageable pageable =  PageRequest.of(start, pageSize);
        Page<CustomerEntity> pageCustomerEntity = customerRepository.findAll(pageable);
        List<CustomerDTO> customerList = new ArrayList<>();
        for (int i = 0; i < pageCustomerEntity.getContent().size() ; i++) {
           CustomerDTO customer = new CustomerDTO();
           if(pageCustomerEntity.getContent().get(i).isDelete()==false && pageCustomerEntity.getContent().get(i).getOrgnisationName().equals(orgnisation)) {
               customer=orikaObjectMapper.getMapper().map(pageCustomerEntity.getContent().get(i), CustomerDTO.class );   
               customerList.add(customer);                     
           }
        }
        System.out.println();
        return new PageImpl<>(customerList, pageable, pageCustomerEntity.getTotalElements());
    
    }

  
    @Override
    public int getOrgnisationIdByEmail(String email) {
        int id=0;
        List <OrgnisationDTO> orgnisationList = new ArrayList<OrgnisationDTO>();
        orgnisationList = getAllOrgnisations(0,199).getContent();
        for(int i=0;i<orgnisationList.size();i++) {
            if(orgnisationList.get(i).getEmail().equals(email))
                id=orgnisationList.get(i).getOrgnisationId();
        }
        return id; 
    }

  
    
}
