package skit.project.bill_payment.DTO;

public class BillDTO extends BaseDTO {
    private int billId;
    private int customerId;
    private int year;
    private String month;
    private boolean status;
    private int amount;
    private int telephoneAmount;
    private int newspaperAmount;
    private java.sql.Date billGenrateDate;
    private java.sql.Date billPaymentDate;
    private boolean isDelete;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTelephoneAmount() {
        return telephoneAmount;
    }

    public void setTelephoneAmount(int telephoneAmount) {
        this.telephoneAmount = telephoneAmount;
    }

    public int getNewspaperAmount() {
        return newspaperAmount;
    }

    public void setNewspaperAmount(int newspaperAmount) {
        this.newspaperAmount = newspaperAmount;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public boolean isTatus() {
        return status;
    }

    public void setTatus(boolean tatus) {
        this.status = tatus;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public java.sql.Date getBillGenrateDate() {
        return billGenrateDate;
    }

    public void setBillGenrateDate(java.sql.Date billGenrateDate) {
        this.billGenrateDate = billGenrateDate;
    }

    public java.sql.Date getBillPaymentDate() {
        return billPaymentDate;
    }

    public void setBillPaymentDate(java.sql.Date billPaymentDate) {
        this.billPaymentDate = billPaymentDate;
    }

}
