package fi.espoo.pythia.backend.mappers;

import fi.espoo.pythia.backend.repos.entities.Comment;
import fi.espoo.pythia.backend.repos.entities.LatestPlans;
import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.transfer.CommentValue;

public class CommentToCommentValueMapper {
	
	public static CommentValue commentToCommentValue(Comment c, Plan plan) {
		CommentValue cv = new CommentValue();
		
		cv.setCommentId(c.getCommentId());
		cv.setPlanId(plan.getPlanId()); 
		cv.setText(c.getText());
		cv.setApproved(c.isApproved());
		cv.setUrl(c.getUrl());
		cv.setCreatedAt(c.getCreatedAt());
		cv.setCreatedBy(c.getCreatedBy());
		cv.setUpdatedAt(c.getUpdatedAt());
		cv.setUpdatedBy(c.getUpdatedBy());


		return cv;
	}
	
	public static CommentValue commentToCommentValue(Comment c, LatestPlans plan) {
		CommentValue cv = new CommentValue();
		
		cv.setCommentId(c.getCommentId());
		cv.setPlanId(plan.getPlanId()); 
		cv.setText(c.getText());
		cv.setApproved(c.isApproved());
		cv.setUrl(c.getUrl());
		cv.setCreatedAt(c.getCreatedAt());
		cv.setCreatedBy(c.getCreatedBy());
		cv.setUpdatedAt(c.getUpdatedAt());
		cv.setUpdatedBy(c.getUpdatedBy());


		return cv;
	}

}
