package fi.espoo.pythia.backend.repos.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "project")
public class Project implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proj_generator")
	@SequenceGenerator(name = "proj_generator", sequenceName = "proj_serial", allocationSize = 50)
	
	//ontoeToMany
	@OneToMany(mappedBy ="manyToOneProjectMapping")
	private List<Plan> listOfPlans = new ArrayList<Plan>();

	// bigint
	@Column(name = "project_id", updatable = false, nullable = false)
	private Long projectId;

	// varchar
	@Column(name = "hansu_project_id")
	private String hansuProjectId;

	// varchar
	@Column(name = "name")
	private String name;

	// varchar
	@Column(name = "description")
	private String description;

	// https://jdbc.postgresql.org/documentation/head/java8-date-time.html
	// timestamp with timezone
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private OffsetDateTime createdAt;

	// varchar
	@Column(name = "created_by")
	private String createdBy;

	// timestamp with timezone timestamptz
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private OffsetDateTime updatedAt;

	// varchar
	@Column(name = "updated_by")
	private String updatedBy;
	
	

	public Project() {

	}

	public Project(Long id, String hansuprojectid, String name, String description, Date createdAt, String createdBy,
			Date updatedAt, String updatedBy) {
		super();
		this.id = id;
		this.hansuProjectId = hansuProjectId;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHansuprojectid() {
		return hansuProjectId;
	}

	public void setHansuprojectid(String hansuProjectId) {
		this.hansuProjectId = hansuProjectId;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
