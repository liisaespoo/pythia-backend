package fi.espoo.pythia.backend.transfer;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fi.espoo.pythia.backend.repos.entities.Project;

public class ProjectMappingValue {

	private Long mappingId;

	private Long projectId;

	private Long sisterProjectId;

	
	public ProjectMappingValue() {
		
	}

	public ProjectMappingValue(Long mappingId, Long projectId, Long sisterProjectId) {
		this.mappingId = mappingId;
		this.projectId = projectId;
		this.sisterProjectId = sisterProjectId;
	}

	public Long getMappingId() {
		return mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getSisterProjectId() {
		return sisterProjectId;
	}

	public void setSisterProjectId(Long sisterProjectId) {
		this.sisterProjectId = sisterProjectId;
	}
	
	

}
