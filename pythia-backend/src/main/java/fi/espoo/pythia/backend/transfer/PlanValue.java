package fi.espoo.pythia.backend.transfer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fi.espoo.pythia.backend.repos.entities.Project;

public class PlanValue  implements Serializable {

	private Long planId;

	
	private Long projectId;


	private short mainNo;


	private short subNo;


	private String version;


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


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
}
