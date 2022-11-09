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
import org.springframework.stereotype.Service;

import skit.project.bill_payment.DTO.PriceDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.common.OrikaObjectMapper;
import skit.project.bill_payment.entity.PriceEntity;
import skit.project.bill_payment.repository.PriceDetailRepository;
import skit.project.bill_payment.service.IPriceDetailService;

@Service("pricedetailservice")
public class PriceDetailService implements IPriceDetailService {
    
    
    @Autowired
    @Qualifier("pricedetailrepository")
    PriceDetailRepository priceDetailRepository;
    
    @Autowired
    OrikaObjectMapper orikaObjectMapper;


    @Override
    public Page<PriceDTO> getAllPrices(int start,int pageSize) {
        Pageable pageable =  PageRequest.of(start, pageSize);
        Page<PriceEntity> pagePriceDetailEntity = priceDetailRepository.findAll(pageable);
        List<PriceDTO> priceList = new ArrayList<>();
        for (int i = 0; i < pagePriceDetailEntity.getContent().size() ; i++) {
           PriceDTO price = new PriceDTO();
           if(pagePriceDetailEntity.getContent().get(i).isDelete()==false) {
               price=orikaObjectMapper.getMapper().map(pagePriceDetailEntity.getContent().get(i), PriceDTO.class );   
               priceList.add(price);                     
           }
        }
        System.out.println();
        return new PageImpl<>(priceList, pageable, pagePriceDetailEntity.getTotalElements());
    }

    @Override
    public PriceEntity savePrices(PriceDTO  price) {
        PriceEntity priceEntity = new PriceEntity();
                priceEntity = orikaObjectMapper.getMapper().map(price, PriceEntity.class);
                
                
                System.out.println("Price is saved sucessfully");
                
                
                return priceDetailRepository.save(priceEntity);
    }

    @Override
    public PriceDTO getPricesById(int  id){
        PriceDTO price = new PriceDTO();
        Optional <PriceEntity> priceOp = priceDetailRepository.findById(id);
        if (priceOp.isPresent()) {
            PriceEntity priceEntity = priceOp.get();
            
            if(priceEntity.isDelete()== true) {
               System.out.println("Id doesnt exit");    
               }
            else {
                price= orikaObjectMapper.getMapper().map(priceEntity,PriceDTO.class);
                }
            }
        else
        {
            System.out.println("Price is not existed");
        }
        return price;
    }

    @Override
    public String priceValidation(String feature) throws DuplicateEntryException {
        List <PriceDTO> priceList = new ArrayList<PriceDTO>();
        priceList = getAllPrices(0,199).getContent();
        for(int i=0;i<priceList.size();i++) {
            if(priceList.get(i).getFeature().equals(feature))
                throw new DuplicateEntryException("feature Already Exists ");
        }
        return "Entry Saved Sucessfully";   
    }

    @Override
    public void deletePrice(int id) {
        Optional <PriceEntity> priceOp = priceDetailRepository.findById(id);
        if (priceOp.isPresent()) {
            Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
            PriceEntity priceEntity = priceOp.get();
            priceEntity.setDelete(true);
            priceEntity.setLastModifiedDate(lastModifiedDate);
            priceDetailRepository.save(priceEntity);
            System.out.println("Deletion Completed"); 
        }
        else {
            System.out.println("ID doesnt exit");
        }
    }
    
    @Override
    public PriceEntity changePrice(int id  ,  int price) {
        PriceDTO priceDTO = getPricesById(id);
        PriceEntity priceEntity = new PriceEntity();
        if(priceDTO==null)
            System.out.println("Wrong Price ID");
        else {
            priceEntity = orikaObjectMapper.getMapper().map(priceDTO,PriceEntity.class);
            Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
            priceEntity.setPrice(price);
            priceEntity.setLastModifiedDate(lastModifiedDate);
            priceDetailRepository.save(priceEntity);
            System.out.println("Sucessfully Changed");
        }
        
        return priceEntity;
    }
    
    
    
}
