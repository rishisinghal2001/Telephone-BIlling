package skit.project.bill_payment.DTO;

public class CustomerDTO extends BaseDTO {

    private Integer customerId;
    private String firstName;
    private String lastName;
    private char gender;
    private String email;
    private long phoneNo;
    private String orgnisationName;
    private String userPassword;
    private String custoToken;
    private boolean isDelete;

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getOrgnisationName() {
        return orgnisationName;
    }

    public void setOrgnisationName(String orgnisationName) {
        this.orgnisationName = orgnisationName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCustoToken() {
        return custoToken;
    }

    public void setCustoToken(String token) {
        this.custoToken = token;
    }

}
