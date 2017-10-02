package fi.espoo.pythia.backend.repos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "project_mapping")
public class ProjectMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "map_generator")
	@SequenceGenerator(name = "map_generator", sequenceName = "pmap_serial", allocationSize = 1)
	@Column(name = "mapping_id", updatable = false, nullable = false)
	private Long mappingId;

	// @Column(name = "project_id")
	// private Long projectId;

	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

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

	public Long getSisterProjectId() {
		return sisterProjectId;
	}

	public void setSisterProjectId(Long sisterProjectId) {
		this.sisterProjectId = sisterProjectId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
