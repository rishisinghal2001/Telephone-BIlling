package skit.project.bill_payment.service;

import org.springframework.data.domain.Page;

import skit.project.bill_payment.DTO.BillDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.BillEntity;

public interface IBillService {
    
    public Page<BillDTO> getAllBills(int start,int pageSize);
    public BillEntity saveBill(BillDTO bill);
	public BillDTO getBillById(int  id);
	public String billValidation(int customerId , int year, String month) throws DuplicateEntryException ;
	public void deleteBill(int id);
}
