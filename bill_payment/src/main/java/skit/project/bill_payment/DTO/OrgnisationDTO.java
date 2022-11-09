package skit.project.bill_payment.DTO;

public class OrgnisationDTO extends BaseDTO {

    private Integer orgnisationId;
    private String orgnisationName;
    private String email;
    private String orgnPassword;
    private String orgntoken;
    private boolean isDelete;

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getOrgntoken() {
        return orgntoken;
    }

    public void setOrgntoken(String orgntoken) {
        this.orgntoken = orgntoken;
    }

    public Integer getOrgnisationId() {
        return orgnisationId;
    }

    public void setOrgnisationId(Integer orgnisationId) {
        this.orgnisationId = orgnisationId;
    }

    public String getOrgnisationName() {
        return orgnisationName;
    }

    public void setOrgnisationName(String orgnisationName) {
        this.orgnisationName = orgnisationName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgnPassword() {
        return orgnPassword;
    }

    public void setOrgnPassword(String orgnPassword) {
        this.orgnPassword = orgnPassword;
    }

}
