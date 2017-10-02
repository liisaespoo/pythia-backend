package fi.espoo.pythia.backend.repos.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class ProjectMapping {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "map_generator")
	@SequenceGenerator(name = "map_generator", sequenceName = "pmap_serial", allocationSize = 1)
	@Column(name = "mapping_id", updatable = false, nullable = false)
	private Long mappingId;
	
	@Column(name = "project_id")
	private Long projectId;
	
	@Column(name = "sister_project_id")
	private Long sisterProjectId;
	
	public ProjectMapping() {
		
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
