package fi.espoo.pythia.backend.mappers;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.transfer.PlanValue;
import fi.espoo.pythia.backend.transfer.ProjectValue;

public class ProjectToProjectValueMapper {

	
	public static ProjectValue projectToProjectValue(Project p) {
		
		ProjectValue pv = new ProjectValue();
		pv.setProjectId(p.getProjectId());
		pv.setHansuProjectId(p.getHansuProjectId());
		pv.setMainNo(p.getMainNo());
		pv.setName(p.getName());
		pv.setDescription(p.getDescription());
		
		// not working	
		List<PlanValue> planvs = new ArrayList();
		for(Plan pp : p.getPlans()){
			System.out.println("Planid:"+pp.getPlanId());
			planvs.add(PlanToPlanValueMapper.planToPlanValue(pp, p));
		}
			pv.setPlans(planvs);
		
//		pv.setCreatedAt(p.getCreatedAt());
//		pv.setCreatedBy(p.getCreatedBy());
//		pv.setUpdatedAt(p.getUpdatedAt());
//		pv.setUpdatedBy(p.getUpdatedBy());
		
		return pv;
	}
	
}
