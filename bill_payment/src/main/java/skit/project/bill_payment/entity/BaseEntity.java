package skit.project.bill_payment.entity;

import java.sql.Timestamp;

public class BaseEntity {
   
    private Timestamp addDate = new Timestamp(System.currentTimeMillis());
	private String addedBy;
	private Timestamp lastModifiedDate ;
	private String LastModifiedBy;
	

	
	public Timestamp getAddDate() {
		return addDate;
	}
	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Timestamp timestamp) {
		this.lastModifiedDate = timestamp;
	}
	public String getLastModifiedBy() {
		return LastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		LastModifiedBy = lastModifiedBy;
	}
	 


}
