package fi.espoo.pythia.backend.transfer;

import java.io.Serializable;

//import javax.persistence.Column;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//import fi.espoo.pythia.backend.repos.entities.Project;

public class CommentValue implements Serializable {
	
	private Long commentId;
	
	private Long planId;
	
	private String text;
	
	private boolean approved;
	
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
