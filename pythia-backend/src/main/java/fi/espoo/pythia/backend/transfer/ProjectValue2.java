package fi.espoo.pythia.backend.transfer;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import fi.espoo.pythia.backend.repos.entities.LatestPlans;

public class ProjectValue2 implements Serializable {

	// public interface WithoutPasswordView {
	// };
	//
	// public interface WithPasswordView extends WithoutPasswordView {
	// };

	private Long projectId;
	private String hansuProjectId;
	private String name;
	private short mainNo;
	private String description;
	private boolean completed;
	// removed ArrayList definition
	//private List<PlanValue> plans;
	private List<LatestPlansValue> latestplans;
	private List<Long> sisterProjects;
	// private Date createdAt;
	private OffsetDateTime createdAt;

	private String createdBy;
	private OffsetDateTime updatedAt;
	private String updatedBy;

	public ProjectValue2() {
	}



	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getHansuProjectId() {
		return hansuProjectId;
	}

	public void setHansuProjectId(String hansuProjectId) {
		this.hansuProjectId = hansuProjectId;
	}

	public short getMainNo() {
		return mainNo;
	}

	public void setMainNo(short mainNo) {
		this.mainNo = mainNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<Long> getSisterProjects() {
		return sisterProjects;
	}

	public void setSisterProjects(List<Long> sisterProjects) {
		this.sisterProjects = sisterProjects;
	}

	public List<LatestPlansValue> getLatestplans() {
		return latestplans;
	}

	public void setLatestplans(List<LatestPlansValue> latestplans) {
		this.latestplans = latestplans;
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
