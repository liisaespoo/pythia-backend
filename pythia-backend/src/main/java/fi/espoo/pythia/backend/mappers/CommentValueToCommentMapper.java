package fi.espoo.pythia.backend.mappers;


import fi.espoo.pythia.backend.repos.entities.Plan;

import java.time.OffsetDateTime;

import fi.espoo.pythia.backend.repos.entities.Comment;
import fi.espoo.pythia.backend.transfer.CommentValue;

public class CommentValueToCommentMapper {

	public static Comment commentValueToComment(CommentValue cv, Plan plan, boolean updating) {
		Comment c = new Comment();

		c.setCommentId(cv.getCommentId());
		c.setPlan(plan);
		c.setText(cv.getText());
//		c.setApproved(cv.isApproved());
//		c.setUrl(cv.getUrl());
//
//		if (updating == false) {
//			c.setCreatedAt(OffsetDateTime.now());
//		} else {
//			c.setCreatedAt(c.getCreatedAt());
//		}
//
//		
//		
//		c.setCreatedBy(cv.getCreatedBy());
//		c.setUpdatedAt(OffsetDateTime.now());
//		c.setUpdatedBy(cv.getUpdatedBy());

		return c;
	}
}
