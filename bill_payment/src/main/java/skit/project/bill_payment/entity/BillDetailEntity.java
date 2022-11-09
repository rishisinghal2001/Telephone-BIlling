package skit.project.bill_payment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity(name = "bill_detail")
@Table(name = "bill_detail")
@Where(clause = "is_delete = 'false'")
public class BillDetailEntity extends BaseEntity {
    @Id
    private int billDetailId;
    private int billId;
    private int priceId;
    private boolean isDelete;

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public int getBillDetailIid() {
        return billDetailId;
    }

    public void setBillDetailIid(int billDetailId) {
        this.billDetailId = billDetailId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

}
