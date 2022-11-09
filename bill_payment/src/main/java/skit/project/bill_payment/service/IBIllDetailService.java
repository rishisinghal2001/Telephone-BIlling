package skit.project.bill_payment.service;

import org.springframework.data.domain.Page;

import skit.project.bill_payment.DTO.BillDetailDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.BillDetailEntity;

public interface IBIllDetailService {
    
    public Page<BillDetailDTO> getAllBillDetails(int start,int pageSize);
    public BillDetailEntity saveBillDetail(BillDetailDTO user);
	public BillDetailDTO getBillDetailById(int  id);
	public String billDetailValidation(int billId ) throws DuplicateEntryException ;
	public void deleteBillDetail(int id);
}
