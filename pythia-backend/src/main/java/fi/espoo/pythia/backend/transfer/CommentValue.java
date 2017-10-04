package fi.espoo.pythia.backend.transfer;

import java.io.Serializable;

public class CommentValue implements Serializable {

	private Long commentId;

	private Long planId;

	private String text;

	private boolean approved;

	public CommentValue(Long commentId, Long planId, String text, boolean approved) {
		
		this.commentId = commentId;
		this.planId = planId;
		this.text = text;
		this.approved = approved;
	}

	public CommentValue() {
		
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getText() {
		return text;
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

	
	

}
