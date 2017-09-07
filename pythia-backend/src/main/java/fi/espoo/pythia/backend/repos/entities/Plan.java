/**
 * http://www.service-architecture.com/articles/database/mapping_sql_and_java_data_types.html
 */
package fi.espoo.pythia.backend.repos.entities;

import java.io.Serializable;
import java.util.Date;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "plan")
public class Plan implements Serializable {	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_generator")
	@SequenceGenerator(name = "plan_generator", sequenceName = "plan_serial", allocationSize = 50)
	
	//manytoone 
	//project_id is the <<fk>> of table Plan
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project manyToOneProjectMapping;
	
	//bigint
	@Column(name = "plan_id", updatable = false, nullable = false)
	private Long planId;
	
	//bigint
	@Column(name = "project_id")
	private Long projectId;
	
	//smallint
	@Column(name = "main_no")
	private short mainNo;
	
	//smallint
	@Column(name = "sub_no")
	private short subNo;
	
	//varchar
	@Column(name = "version")
	private String version;
	
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
		Plan other = (Plan) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
