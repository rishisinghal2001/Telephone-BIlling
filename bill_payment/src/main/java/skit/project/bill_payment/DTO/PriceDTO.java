package skit.project.bill_payment.DTO;

public class PriceDTO extends BaseDTO {

    private int priceId;
    private String feature;

    private int price;

    private boolean isDelete;

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public int getPriceid() {
        return priceId;
    }

    public void setPriceid(int priceid) {
        this.priceId = priceid;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
