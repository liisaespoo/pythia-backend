package fi.espoo.pythia.backend.mappers;

import java.time.OffsetDateTime;

import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.transfer.ProjectValue;

public class ProjectValueToProject {
	
	private Long projectId;
	private String hansuProjectId;
	private String name;
	private String description;
	private OffsetDateTime createdAt;
	private String createdBy;
	private OffsetDateTime updatedAt;
	private String updatedBy;
	
	private Project p;
	
	
	public ProjectValueToProject() {
		
	}
	
	public Project getProject(ProjectValue pv) {

		mapProjectToProjectValue(pv);
		return this.p;

	}
	
	private void mapProjectToProjectValue(ProjectValue pv) {
		p.setProjectId(pv.getProjectId());
		p.setHansuProjectId(pv.getHansuProjectId());
		p.setName(pv.getName());
		p.setDescription(pv.getDescription());
		p.setCreatedAt(pv.getCreatedAt());
		p.setCreatedBy(pv.getCreatedBy());
		p.setUpdatedAt(pv.getUpdatedAt());
		p.setUpdatedBy(pv.getUpdatedBy());
	}
	

}
