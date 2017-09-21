package fi.espoo.pythia.backend.mappers;

import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.transfer.PlanValue;

public class PlanToPlanValueMapper {

	public static PlanValue planToPlanValue(Plan p, Project project) {
		
		//get project_id 
		

		PlanValue pv = new PlanValue();
		
		pv.setPlanId(pv.getPlanId());
		pv.setProjectId(project.getProjectId());
		pv.setMainNo(pv.getMainNo());
		pv.setSubNo(pv.getSubNo());
		pv.setVersion(pv.getVersion());
				
//		p.setCreatedAt(pv.getCreatedAt());
//		p.setCreatedBy(pv.getCreatedBy());
//		p.setUpdatedAt(pv.getUpdatedAt());
//		p.setUpdatedBy(pv.getUpdatedBy());
		
		return pv;
	}
	
}
