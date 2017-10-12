package fi.espoo.pythia.backend.transfer;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class PtextValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long text_id;

	private Long planId;

	private String ptext;

	private boolean approved;
	
	private String url;
	
	private OffsetDateTime createdAt;
	
	private String createdBy;
	
	private OffsetDateTime updatedAt;
	
	private String updatedBy;



	public PtextValue() {
		
	}



	public Long getText_id() {
		return text_id;
	}



	public void setText_id(Long text_id) {
		this.text_id = text_id;
	}



	public Long getPlanId() {
		return planId;
	}



	public void setPlanId(Long planId) {
		this.planId = planId;
	}



	public String getPtext() {
		return ptext;
	}



	public void setPtext(String ptext) {
		this.ptext = ptext;
	}



	public boolean isApproved() {
		return approved;
	}



	public void setApproved(boolean approved) {
		this.approved = approved;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}




}
