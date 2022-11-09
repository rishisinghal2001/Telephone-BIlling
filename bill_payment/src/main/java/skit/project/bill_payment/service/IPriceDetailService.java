package skit.project.bill_payment.service;

import org.springframework.data.domain.Page;

import skit.project.bill_payment.DTO.PriceDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.PriceEntity;

public interface IPriceDetailService {
    
    public Page<PriceDTO> getAllPrices(int start, int pageSize);
    public PriceEntity savePrices(PriceDTO  price);
	public PriceDTO getPricesById(int  id);
	public String priceValidation(String feature) throws DuplicateEntryException ;
	public void deletePrice(int id);
    public PriceEntity changePrice(int id  , int  price);

}
