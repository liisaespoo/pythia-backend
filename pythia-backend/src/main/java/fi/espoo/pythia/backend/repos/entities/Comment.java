/**
 * comment_id bigint NOT NULL,
  text character varying,
  plan_id bigint,
  url character varying,
  approved boolean,
  created_at timestamp with time zone,
  created_by character varying,
  updated_at timestamp with time zone,
  updated_by character varying,
 */
package fi.espoo.pythia.backend.repos.entities;

import java.io.Serializable;
//import java.time.OffsetDateTime;
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
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
	@SequenceGenerator(name = "comment_generator", sequenceName = "comm_serial, allocationSize = 1")
	@Column(name = "comment_id", updatable = false, nullable = false)
	private Long commentId;
	
	@ManyToOne
	@JoinColumn(name  = "plan_id")
	private Plan plan;	

	@Column(name = "text")
	private String text;
	
	@Column(name = "approved")
	private boolean approved;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "created_at")
	private OffsetDateTime createdAt;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_at")
	private OffsetDateTime updatedAt;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	public Comment() {
		
	}
	
	@JsonIgnore
	public Plan getPlan() {
		return plan;
	}
	
	@JsonIgnore
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	public Long getCommentId() {
		return commentId;
	}
	
	public void setCommentId(Long commentId ) {
		this.commentId = commentId;
	}
	
	public String getText() {
		return this.text;
		
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}

