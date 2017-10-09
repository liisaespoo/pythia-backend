package fi.espoo.pythia.backend.transfer;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class PlanValue  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long planId;

	
	private Long projectId;


	private short mainNo;


	private short subNo;


	private short version;

	
	private String url;
	
	
	private boolean approved;
	
	
	private OffsetDateTime createdAt;
	
	private String createdBy;
	
	private OffsetDateTime updatedAt;
	
	private String updatedBy;
	
	
	public Long getPlanId() {
		return planId;
	}


	public void setPlanId(Long planId) {
		this.planId = planId;
	}


	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public short getMainNo() {
		return mainNo;
	}


	public void setMainNo(short mainNo) {
		this.mainNo = mainNo;
	}


	public short getSubNo() {
		return subNo;
	}


	public void setSubNo(short subNo) {
		this.subNo = subNo;
	}


	public short getVersion() {
		return version;
	}


	public void setVersion(short version) {
		this.version = version;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean getApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
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
