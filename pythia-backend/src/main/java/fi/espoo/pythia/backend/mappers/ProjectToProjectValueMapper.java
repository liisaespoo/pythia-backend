package fi.espoo.pythia.backend.mappers;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.repos.entities.SisterProject;
import fi.espoo.pythia.backend.transfer.PlanValue;
import fi.espoo.pythia.backend.transfer.ProjectValue;
import fi.espoo.pythia.backend.transfer.SisterProjectValue;

public class ProjectToProjectValueMapper {

	
	public static ProjectValue projectToProjectValue(Project p) {
		
		ProjectValue pv = new ProjectValue();
		pv.setProjectId(p.getProjectId());
		pv.setHansuProjectId(p.getHansuProjectId());
		pv.setMainNo(p.getMainNo());
		pv.setName(p.getName());
		pv.setDescription(p.getDescription());
		pv.setCompleted(p.getCompleted());
		
		//
		List<PlanValue> planvs = new ArrayList();
		for(Plan pp : p.getPlans()){
			System.out.println("Planid:"+pp.getPlanId());
			planvs.add(PlanToPlanValueMapper.planToPlanValue(pp, p));
		}
			pv.setPlans(planvs);
		
			
		List<SisterProjectValue> sisterProjects = new ArrayList();	
		
		System.out.println("get sister projects:" + p.getSisterProjects());
		
		for(SisterProject pm : p.getSisterProjects()){
			sisterProjects.add(SisterProjectToSisterProjectValueMapper.sisterProjectToSisterProjectValue(pm,p));
		}
		
		pv.setSisterProjects(sisterProjects);
		
//		pv.setCreatedAt(p.getCreatedAt());
//		pv.setCreatedBy(p.getCreatedBy());
//		pv.setUpdatedAt(p.getUpdatedAt());
//		pv.setUpdatedBy(p.getUpdatedBy());
		
		return pv;
	}
	
}
