package fi.espoo.pythia.backend.mappers;

import java.util.ArrayList;
import java.util.List;

import fi.espoo.pythia.backend.repos.entities.Comment;
import fi.espoo.pythia.backend.repos.entities.LatestPlans;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.transfer.CommentValue;
import fi.espoo.pythia.backend.transfer.LatestPlansValue;

public class LPToLPValueMapper {

	public static LatestPlansValue lpTolpValue(LatestPlans p, Project project) {

		// get project_id
		LatestPlansValue pv = new LatestPlansValue();

		pv.setPlanId(p.getPlanId());
		pv.setProjectId(project.getProjectId());
		pv.setMainNo(p.getMainNo());
		pv.setSubNo(p.getSubNo());
		pv.setVersion(p.getVersion());
		pv.setUrl(p.getUrl());
		pv.setApproved(p.isApproved());

		List<CommentValue> commentValues = new ArrayList<CommentValue>();
		for (Comment c : p.getComments()) {
			commentValues.add(CommentToCommentValueMapper.commentToCommentValue(c, p));
		}
		pv.setCommentValues(commentValues);

		pv.setCreatedAt(p.getCreatedAt());
		pv.setCreatedBy(p.getCreatedBy());
		pv.setUpdatedAt(p.getUpdatedAt());
		pv.setUpdatedBy(p.getUpdatedBy());

		return pv;
	}
}
