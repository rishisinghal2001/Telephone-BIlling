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

import skit.project.bill_payment.DTO.BillDetailDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.common.OrikaObjectMapper;
import skit.project.bill_payment.entity.BillDetailEntity;
import skit.project.bill_payment.repository.BillDetailRepository;
import skit.project.bill_payment.service.IBIllDetailService;

@Service("billdetailservice")
public class BillDetailService implements IBIllDetailService {
    @Autowired
    @Qualifier("billdetailrepository")
    BillDetailRepository billDetailRepository;
    
    @Autowired
    OrikaObjectMapper orikaObjectMapper;

    
    @Override
    public Page<BillDetailDTO> getAllBillDetails(int start,int pageSize) {
        Pageable pageable =  PageRequest.of(start, pageSize);
        Page<BillDetailEntity> pageBillDetailEntity = billDetailRepository.findAll(pageable);
        List<BillDetailDTO> billDetailList = new ArrayList<>();
        for (int i = 0; i < pageBillDetailEntity.getContent().size() ; i++) {
           BillDetailDTO billDetail = new BillDetailDTO();
           if(pageBillDetailEntity.getContent().get(i).isDelete()==false) {
               billDetail=orikaObjectMapper.getMapper().map(pageBillDetailEntity.getContent().get(i), BillDetailDTO.class );   
               billDetailList.add(billDetail);                     
           }
        }
        System.out.println();
        return new PageImpl<>(billDetailList, pageable, pageBillDetailEntity.getTotalElements());
    }

    @Override
    public BillDetailEntity saveBillDetail(BillDetailDTO billDetail) {
        BillDetailEntity billDetailEntity = new BillDetailEntity();
                billDetailEntity = orikaObjectMapper.getMapper().map(billDetail, BillDetailEntity.class);
                
                
                System.out.println("Bill Detail is saved sucessfully");
                
                
                return billDetailRepository.save(billDetailEntity);
    }

    @Override
    public BillDetailDTO getBillDetailById(int id) {
        BillDetailDTO billDetail = new BillDetailDTO();
        Optional <BillDetailEntity> billDetailOp = billDetailRepository.findById(id);
        if (billDetailOp.isPresent()) {
            BillDetailEntity billDetailEntity = billDetailOp.get();
            
            if(billDetailEntity.isDelete()== true) {
               System.out.println("Id doesnt exit");    
               }
            else {
                billDetail= orikaObjectMapper.getMapper().map(billDetailEntity,BillDetailDTO.class);
                }
            }
        else
        {
            System.out.println("Bill Detail not existed");
        }
        return billDetail;
    }

    @Override
    public String billDetailValidation(int billId) throws DuplicateEntryException {
        List <BillDetailDTO> billDetailList = new ArrayList<BillDetailDTO>();
        billDetailList = getAllBillDetails(0,199).getContent();
        for(int i=0;i<billDetailList.size();i++) {
            if(billDetailList.get(i).getBillId()==billId)
                throw new DuplicateEntryException("Bill Detail Already Exists ");
        }
        return "Entry Saved Sucessfully";   
    }

    @Override
    public void deleteBillDetail(int id) {
        Optional <BillDetailEntity> billDetailOp = billDetailRepository.findById(id);
        if (billDetailOp.isPresent()) {
            Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
            BillDetailEntity billDetailEntity = billDetailOp.get();
            billDetailEntity.setDelete(true);
            billDetailEntity.setLastModifiedDate(lastModifiedDate);
            billDetailRepository.save(billDetailEntity);
            System.out.println("Deletion Completed"); 
        }
        else {
            System.out.println("ID doesnt exit");
        }
    }
    
    
    

}
