package fi.espoo.pythia.backend.mappers;

import java.time.OffsetDateTime;

import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.transfer.ProjectValue;

public class ProjectToProjectValue {

	private ProjectValue pv;

	public ProjectToProjectValue() {
		pv = new ProjectValue();
	}



	public ProjectValue getProjectValue(Project p) {

		mapProjectToProjectValue(p);
		return this.pv;

	}

	

	
	private void mapProjectToProjectValue(Project p) {
		pv.setProjectId(p.getProjectId());
		pv.setHansuProjectId(p.getHansuProjectId());
		pv.setName(p.getName());
		pv.setDescription(p.getDescription());
//		pv.setCreatedAt(p.getCreatedAt());
		pv.setCreatedBy(p.getCreatedBy());
//		pv.setUpdatedAt(p.getUpdatedAt());
		pv.setUpdatedBy(p.getUpdatedBy());
	}
	
//	public void mapToProjectValue(Long projectId, String hansuProjectId, String name, String description,
//			OffsetDateTime createdAt, String createdBy, OffsetDateTime updatedAt, String updatedBy) {
//		pv.setProjectId(projectId);
//		pv.setHansuProjectId(hansuProjectId);
//		pv.setName(name);
//		pv.setDescription(description);
//		pv.setCreatedAt(createdAt);
//		pv.setCreatedBy(createdBy);
//		pv.setUpdatedAt(updatedAt);
//		pv.setUpdatedBy(updatedBy);
//	}
	
}
