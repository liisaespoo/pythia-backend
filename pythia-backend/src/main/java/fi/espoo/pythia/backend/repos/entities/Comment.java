package fi.espoo.pythia.backend.repos.entities;

import java.io.Serializable;
//import java.time.OffsetDateTime;

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
@Table(name = "Comment")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
	@SequenceGenerator(name = "comment_generator", sequenceName = "comm_serial, allocationSize = 1")
	@Column(name = "comment_id", updatable = false, nullable = false)
	private Long commentId;
	
	@ManyToOne
	@JoinColumn(name  = "plan_id, nullable=false")
	private Plan plan;

	@Column(name = "text")
	private String text;
	
	@Column(name = "approved")
	private boolean approved;
	
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
	
	public boolean getApproved() {
		return this.approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}

