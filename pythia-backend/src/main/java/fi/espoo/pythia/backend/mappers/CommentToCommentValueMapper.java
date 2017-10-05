package fi.espoo.pythia.backend.mappers;

import fi.espoo.pythia.backend.repos.entities.Comment;
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
		return cv;
	}

}
