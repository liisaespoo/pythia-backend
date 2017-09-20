package fi.espoo.pythia.backend.transfer;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.repos.entities.Project;

public class ProjectValue implements Serializable {

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
	// removed ArrayList definition
	private List<Plan> plans;
	// private OffsetDateTime createdAt;
	// private String createdBy;
	// private OffsetDateTime updatedAt;
	// private String updatedBy;

	public ProjectValue() {
	}

	// public ProjectValue(String jsonValue) {
	// // TODO json to objects
	// }
	//
	//
	//
	// public ProjectValue(Long projectId, String hansuProjectId, short mainNo,
	// String name, String description, String createdBy,
	// String updatedBy) {
	//
	// this.projectId = projectId;
	// this.hansuProjectId = hansuProjectId;
	// this.name = name;
	// this.mainNo = mainNo;
	// this.description = description;
	//// this.createdBy = createdBy;
	//// this.updatedBy = updatedBy;
	// }
	//
	// // create the class with a Project Entity
	// public ProjectValue(Project project) {
	// this.projectId = project.getProjectId();
	// this.hansuProjectId = project.getHansuProjectId();
	// this.mainNo = project.getMainNo();
	// this.name = project.getName();
	// this.description = project.getDescription();
	//// this.createdAt = project.getCreatedAt();
	//// this.createdBy = project.getCreatedBy();
	//// this.updatedAt = project.getUpdatedAt();
	//// this.updatedBy = project.getUpdatedBy();
	// }

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

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

	// public String getCreatedBy() {
	// return createdBy;
	// }
	//
	// public void setCreatedBy(String createdBy) {
	// this.createdBy = createdBy;
	// }
	//
	// public String getUpdatedBy() {
	// return updatedBy;
	// }
	//
	// public void setUpdatedBy(String updatedBy) {
	// this.updatedBy = updatedBy;
	// }

}
