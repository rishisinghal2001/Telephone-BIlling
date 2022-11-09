package skit.project.bill_payment.DTO;

public class BillDetailDTO extends BaseDTO {
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
