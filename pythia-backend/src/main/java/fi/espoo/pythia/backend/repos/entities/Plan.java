/**
 * http://www.service-architecture.com/articles/database/mapping_sql_and_java_data_types.html
 * http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#associations-many-to-many
 */
package fi.espoo.pythia.backend.repos.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "plan")
public class Plan implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//bigint
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_generator")
	@SequenceGenerator(name = "plan_generator", sequenceName = "plan_serial", allocationSize = 1)
	@Column(name = "plan_id", updatable = false, nullable = false)
	private Long planId;
	
	//bidirectional manytoone with Project
	// project object maps to class Project
	//project_id is the <<fk>> of table Plan
	//@JoinColumn(name = "project_id")
	@ManyToOne	
	private Project project;
	
//	//bigint
//	@Column(name = "project_id")
//	private Long projectId;
	
	//smallint
	@Column(name = "main_no")
	private short mainNo;
	
	//smallint
	@Column(name = "sub_no")
	private short subNo;
	
	//varchar
	@Column(name = "version")
	private String version;
	
//	// https://jdbc.postgresql.org/documentation/head/java8-date-time.html
//	// timestamp with timezone
//	//@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "created_at")
//	private OffsetDateTime createdAt;

//	// varchar
//	@Column(name = "created_by")
//	private String createdBy;

//	// timestamp with timezone timestamptz
//	//@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "updated_at")
//	private OffsetDateTime updatedAt;

//	// varchar
//	@Column(name = "updated_by")
//	private String updatedBy;
	

	public Plan(Long planId, Long projectId, short mainNo, short subNo, String version, 
			String createdBy,  String updatedBy) {
		super();
		this.planId = planId;
//		this.projectId = projectId;
		this.mainNo = mainNo;
		this.subNo = subNo;
		this.version = version;
//		this.createdAt = createdAt;
//		this.createdBy = createdBy;
//		this.updatedAt = updatedAt;
//		this.updatedBy = updatedBy;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

//	public Long getProjectId() {
//		return projectId;
//	}

//	public void setProjectId(Long projectId) {
//		this.projectId = projectId;
//	}

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

//	public OffsetDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(OffsetDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}

//	public OffsetDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(OffsetDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}

//	public String getUpdatedBy() {
//		return updatedBy;
//	}
//
//	public void setUpdatedBy(String updatedBy) {
//		this.updatedBy = updatedBy;
//	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((planId == null) ? 0 : planId.hashCode());
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
		Plan other = (Plan) obj;
		if (planId == null) {
			if (other.planId != null)
				return false;
		} else if (!planId.equals(other.planId))
			return false;
		return true;
	}
	
	
	

}
