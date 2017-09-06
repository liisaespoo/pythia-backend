package fi.espoo.pythia.backend.repos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//t
@Entity
@Table(name = "project")
public class Project implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
	@SequenceGenerator(name = "book_generator", sequenceName = "proj_serial", allocationSize = 50)

	// bigint
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	// varchar
	@Column(name = "hansuprojectid")
	private String hansuprojectid;

	// varchar
	@Column(name = "name")
	private String name;

	// varchar
	@Column(name = "description")
	private String description;

	// timestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	// varchar
	@Column(name = "created_by")
	private String createdBy;

	// timestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	// varchar
	@Column(name = "updated_by")
	private String updatedBy;

	public Project() {

	}

	public Project(Long id, String hansuprojectid, String name, String description, Date createdAt, String createdBy,
			Date updatedAt, String updatedBy) {
		super();
		this.id = id;
		this.hansuprojectid = hansuprojectid;
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
		return hansuprojectid;
	}

	public void setHansuprojectid(String hansuprojectid) {
		this.hansuprojectid = hansuprojectid;
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

}
