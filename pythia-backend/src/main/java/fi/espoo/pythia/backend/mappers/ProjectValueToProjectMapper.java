package fi.espoo.pythia.backend.mappers;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.transfer.ProjectValue;

public class ProjectValueToProjectMapper {
	
	
	public static Project projectValueToProject(ProjectValue pv) {
		
		Project p = new Project();
		p.setProjectId(pv.getProjectId());
		p.setHansuProjectId(pv.getHansuProjectId());
		p.setMainNo(pv.getMainNo());
		p.setName(pv.getName());
		p.setDescription(pv.getDescription());
		p.setPlans(new ArrayList<Plan>());
	
//		p.setCreatedAt(pv.getCreatedAt());
//		p.setCreatedBy(pv.getCreatedBy());
//		p.setUpdatedAt(pv.getUpdatedAt());
//		p.setUpdatedBy(pv.getUpdatedBy());
		
		return p;
	}
	

}
