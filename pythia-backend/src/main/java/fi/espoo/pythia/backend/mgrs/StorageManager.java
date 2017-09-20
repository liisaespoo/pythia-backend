package fi.espoo.pythia.backend.mgrs;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.espoo.pythia.backend.mappers.ProjectToProjectValueMapper;
import fi.espoo.pythia.backend.mappers.ProjectValueToProjectMapper;
import fi.espoo.pythia.backend.repos.PlanRepository;
import fi.espoo.pythia.backend.repos.ProjectRepository;

import fi.espoo.pythia.backend.repos.entities.Project;

import fi.espoo.pythia.backend.transfer.ProjectValue;

@Component
@Transactional
public class StorageManager {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ProjectRepository projectRepository;
	
	
//	private Project projectDummy;
//	private Project projectDummy2;
//	private ProjectValue projectDummyValue1;
//	private ProjectValue projectDummyValue2;
	
	
	
//	
//	public ArrayList<ProjectValue> getProjects() {
//		
//		ArrayList<Project> prjList = new ArrayList();
//		projectDummy = new Project();
//		
//		prjList.add(projectDummy);
//		
//		ArrayList<ProjectValue> prjValList = new ArrayList();
//		
//		// for -loop for prjList
//		ProjectToProjectValue p2pvmapping = new ProjectToProjectValue();
//		
//		for (Project p : prjList) {
//			
//			// map each project to projectValue 
//			ProjectValue pval = p2pvmapping.getProjectValue(p);
//			prjValList.add(pval);
//		}
//		
//		// return projectValue -ArrayList 
//		
//		return prjValList;
//	}
	
	
	/**
	 * Returns list of projects from database. DONE!!!
	 * 
	 * @return list of projects
	 */
	public ArrayList<ProjectValue> getProjects() {
		
		ArrayList<Project> prjList = (ArrayList<Project>) projectRepository.findAll();
		ArrayList<ProjectValue> prjValList = new ArrayList();
		
		// for -loop for prjList
		
		
		for (Project p : prjList) {
			
			// map each project to projectValue 
			ProjectValue pval = ProjectToProjectValueMapper.projectToProjectValue(p);
			prjValList.add(pval);
		}
		
		// return projectValue -ArrayList 
		
		return prjValList;
	}
	
	

	
	/**
	 * Return project object for given id from database. If project is not found for
	 * id, returns null. DONE
	 */
	public ProjectValue getProject(Long projectId) {
		
		List<ProjectValue> projects = getProjects();
		for (ProjectValue p : projects) {
			if (p.getProjectId().equals(projectId)) {				
				return p;
			}
		}
		return null;
	}
	

	
	
	/**
	 * 
	 * Update the project object for given id in database. If project does
	 * not exists, return null DONE!!!
	 * 
	 * @param projectId
	 * @param project
	 * @return
	 */
	public ProjectValue update(Long projectId, ProjectValue projectV) {
		
		List<ProjectValue> projects = getProjects();
		
		// find projectvalue object with id
		for (ProjectValue p : projects) {
			
			if (p.getProjectId().equals(projectId)) {
				
				// map projectvalue to project
				
				Project prj = ProjectValueToProjectMapper.projectValueToProject(projectV);
				
				// remove current entity with projectId
				projectRepository.delete(projectId);
				
				// add new entity
				projectRepository.save(prj);
				
				return projectV;
			}
		}

		return null;
	}
	

	
	/**
	 * Create new project in database. DONE!!!!
	 * 
	 */
	public ProjectValue createProject(ProjectValue projectV) {

		//map projectV to project
		Project prj = ProjectValueToProjectMapper.projectValueToProject(projectV);
		
		// timestamp with time at db or microservice level
//		prj.setCreatedAt(null);
		Project savedProject = projectRepository.save(prj);

		ProjectValue savedProjectValue = ProjectToProjectValueMapper.projectToProjectValue(savedProject);
		// planRepository.save(5L);

		// planRepository.
		return savedProjectValue;

	}
	
}
