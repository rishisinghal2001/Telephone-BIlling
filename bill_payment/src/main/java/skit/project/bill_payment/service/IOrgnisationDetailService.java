package skit.project.bill_payment.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import skit.project.bill_payment.DTO.CustomerDTO;
import skit.project.bill_payment.DTO.OrgnisationDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.OrgnisationEntity;

public interface IOrgnisationDetailService {

    public UserDetails loadUserByUsername(String orgnisation) throws UsernameNotFoundException;
    public Page<OrgnisationDTO> getAllOrgnisations(int start,int pageSize);
    public OrgnisationEntity saveOrgisation(OrgnisationDTO  orgnisation);
	public OrgnisationDTO getOrgnisationById(String  email);
	public String orgnisationValidation(String email) throws DuplicateEntryException ;
	public void deleteOrgnisation(String email);
	public OrgnisationEntity updateOrgnisation(String email, String orgnName, String orgnPass );
    public OrgnisationEntity changeorgnisationPassword(String email , String password);
    public Page<CustomerDTO> getAllCustomerOfAOrgnisation(String orgnisation,int start,int pageSize);
    public int getOrgnisationIdByEmail(String email);
}