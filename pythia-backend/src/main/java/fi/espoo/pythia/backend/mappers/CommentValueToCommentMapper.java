package fi.espoo.pythia.backend.mappers;


import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.repos.entities.Comment;
import fi.espoo.pythia.backend.transfer.CommentValue;

public class CommentValueToCommentMapper {

	public static Comment commentValueToComment(CommentValue cv, Plan plan) {
		Comment c = new Comment();

		c.setCommentId(cv.getCommentId());
		c.setPlan(plan);
		c.setText(cv.getText());
		c.setApproved(cv.isApproved());
		c.setUrl(cv.getUrl());

		return c;
	}
}
