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

import skit.project.bill_payment.DTO.BillDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.common.OrikaObjectMapper;
import skit.project.bill_payment.entity.BillEntity;
import skit.project.bill_payment.repository.BillRepository;
import skit.project.bill_payment.service.IBillService;

@Service("billservice")
public class BillService implements IBillService {
    @Autowired
    @Qualifier("billrepository")
    BillRepository billRepository;

    @Autowired
    OrikaObjectMapper orikaObjectMapper;

    @Override
    public Page<BillDTO> getAllBills(int start, int pageSize) {
        Pageable pageable = PageRequest.of(start, pageSize);
        Page<BillEntity> pageBillEntity = billRepository.findAll(pageable);
        List<BillDTO> billList = new ArrayList<>();
        for (int i = 0; i < pageBillEntity.getContent().size(); i++) {
            BillDTO bill = new BillDTO();
            if (pageBillEntity.getContent().get(i).isDelete() == false) {
                bill = orikaObjectMapper.getMapper().map(pageBillEntity.getContent().get(i), BillDTO.class);
                billList.add(bill);
            }
        }
        System.out.println();
        return new PageImpl<>(billList, pageable, pageBillEntity.getTotalElements());
    }

    @Override
    public BillEntity saveBill(BillDTO bill) {
        BillEntity billEntity = new BillEntity();
        billEntity = orikaObjectMapper.getMapper().map(bill, BillEntity.class);

        System.out.println("Bill is saved sucessfully");

        return billRepository.save(billEntity);
    }

    @Override
    public BillDTO getBillById(int id) {
        BillDTO bill = new BillDTO();
        Optional<BillEntity> billOp = billRepository.findById(id);
        if (billOp.isPresent()) {
            BillEntity billEntity = billOp.get();

            if (billEntity.isDelete() == true) {
                System.out.println("Id doesnt exit");
            } else {
                bill = orikaObjectMapper.getMapper().map(billEntity, BillDTO.class);
            }
        } else {
            System.out.println("Bill not existed");
        }
        return bill;
    }

    @Override
    public String billValidation(int customerId, int year, String month) throws DuplicateEntryException {
        List<BillDTO> billList = new ArrayList<BillDTO>();
        billList = getAllBills(0, 199).getContent();
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getCustomerId() == customerId && billList.get(i).getMonth().equals(month)
                    && billList.get(i).getYear() == year)
                throw new DuplicateEntryException("Bill  Already Exists ");
        }
        return "Entry Saved Sucessfully";
    }

    @Override
    public void deleteBill(int id) {
        Optional<BillEntity> billOp = billRepository.findById(id);
        if (billOp.isPresent()) {
            Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
            BillEntity billEntity = billOp.get();
            billEntity.setDelete(true);
            billEntity.setLastModifiedDate(lastModifiedDate);
            billRepository.save(billEntity);
            System.out.println("Deletion Completed");
        } else {
            System.out.println("ID doesnt exit");
        }
    }

    @Override
    public Page<BillDTO> getAllBillByCustomerId(int customerId, int start, int pageSize) {
        Pageable pageable = PageRequest.of(start, pageSize);
        Page<BillEntity> pageBillEntity = billRepository.findAll(pageable);
        List<BillDTO> billList = new ArrayList<>();
        for (int i = 0; i < pageBillEntity.getContent().size(); i++) {
            BillDTO bill = new BillDTO();
            if (pageBillEntity.getContent().get(i).isDelete() == false && pageBillEntity.getContent().get(i).getCustomerId()==customerId ) {
                bill = orikaObjectMapper.getMapper().map(pageBillEntity.getContent().get(i), BillDTO.class);
                billList.add(bill);
            }
        }
        System.out.println();
        return new PageImpl<>(billList, pageable, pageBillEntity.getTotalElements());
    }

}
