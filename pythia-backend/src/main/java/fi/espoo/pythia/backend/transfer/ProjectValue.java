//package fi.espoo.pythia.backend.transfer;
//
//import java.io.Serializable;
//import java.time.OffsetDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonView;
//
//import fi.espoo.pythia.backend.repos.entities.Plan;
//import fi.espoo.pythia.backend.repos.entities.Project;
//import fi.espoo.pythia.backend.repos.entities.SisterProject;
//
//public class ProjectValue implements Serializable {
//
//	// public interface WithoutPasswordView {
//	// };
//	//
//	// public interface WithPasswordView extends WithoutPasswordView {
//	// };
//
//	private Long projectId;
//	private String hansuProjectId;
//	private String name;
//	private short mainNo;
//	private String description;
//	private boolean completed;
//	// removed ArrayList definition
//	private List<PlanValue> plans;
//	private List<SisterProjectValue> sisterProjects;
//	// private OffsetDateTime createdAt;
//	// private String createdBy;
//	// private OffsetDateTime updatedAt;
//	// private String updatedBy;
//
//	public ProjectValue() {
//	}
//
//	public ProjectValue(Long projectId, String hansuProjectId, String name, short mainNo, String description, boolean completed, 
//			List<PlanValue> plans) {
//		this.projectId = projectId;
//		this.hansuProjectId = hansuProjectId;
//		this.name = name;
//		this.mainNo = mainNo;
//		this.description = description;
//		this.completed = completed;
//		this.plans = plans;
//	}
//
//	public Long getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(Long projectId) {
//		this.projectId = projectId;
//	}
//
//	public String getHansuProjectId() {
//		return hansuProjectId;
//	}
//
//	public void setHansuProjectId(String hansuProjectId) {
//		this.hansuProjectId = hansuProjectId;
//	}
//
//	public short getMainNo() {
//		return mainNo;
//	}
//
//	public void setMainNo(short mainNo) {
//		this.mainNo = mainNo;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	
//	public boolean getCompleted() {
//		return completed;
//	}
//	
//	public void setCompleted(boolean completed) {
//		this.completed = completed;
//	}
//
//	public List<PlanValue> getPlans() {
//		return plans;
//	}
//
//	public void setPlans(List<PlanValue> plans) {
//		this.plans = plans;
//	}
//
//	public List<SisterProjectValue> getSisterProjects() {
//		return sisterProjects;
//	}
//
//	public void setSisterProjects(List<SisterProjectValue> sisterProjects) {
//		this.sisterProjects = sisterProjects;
//	}
//
//	
//
//
//
//	
//	// public String getCreatedBy() {
//	// return createdBy;
//	// }
//	//
//	// public void setCreatedBy(String createdBy) {
//	// this.createdBy = createdBy;
//	// }
//	//
//	// public String getUpdatedBy() {
//	// return updatedBy;
//	// }
//	//
//	// public void setUpdatedBy(String updatedBy) {
//	// this.updatedBy = updatedBy;
//	// }
//
//}
