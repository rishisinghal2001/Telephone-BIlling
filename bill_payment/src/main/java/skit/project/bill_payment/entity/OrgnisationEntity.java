package skit.project.bill_payment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Where;

@Entity(name = "orgnisation_detail")
@Table(name = "orgnisation_detail")
@Where(clause = "is_delete = 'false'")
public class OrgnisationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orgnisationId;
    private String orgnisationName;
    private String email;
    private String orgnPassword;
    private String token;

    private boolean isDelete;

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getOrgntoken() {
        return token;
    }

    public void setOrgntoken(String orgntoken) {
        this.token = orgntoken;
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
