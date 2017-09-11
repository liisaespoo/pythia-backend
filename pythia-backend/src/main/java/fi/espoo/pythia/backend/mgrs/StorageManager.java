package fi.espoo.pythia.backend.mgrs;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.espoo.pythia.backend.repos.PlanRepository;
import fi.espoo.pythia.backend.repos.ProjectRepository;
import fi.espoo.pythia.backend.repos.entities.Project;

@Component
@Transactional
public class StorageManager {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ProjectRepository projectRepository;
	
	

	/**
	 * Create new project in database.
	 * 
	 */
	public Project createProject(Project project) {

		// timestamp with time at db or microservice level
		project.setCreatedAt(null);
		
		Project savedProject = projectRepository.save(project);

		// planRepository.save(5L);

		// planRepository.
		return project;

	}

	/**
	 * Returns list of projects from database.
	 * 
	 * @return list of projects
	 */
	public ArrayList<Project> getProjects() {
		
		return (ArrayList<Project>) projectRepository.findAll();

	}

	/**
	 * Return project object for given id from database. If project is not found for
	 * id, returns null.
	 */
	public Project getProject(Long projectId) {
		// TODO Auto-generated method stub

		List<Project> projects = getProjects();
		for (Project p : projects) {
			if (p.getProjectId().equals(projectId)) {
				return p;
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * Update the project object for given id in database. If project does
	 * not exists, return null
	 * 
	 * @param projectId
	 * @param project
	 * @return
	 */
	public Project update(Long projectId, Project project) {
		
		List<Project> projects = getProjects();
		
		// find project object with id
		for (Project p : projects) {
			
			if (p.getProjectId().equals(projectId)) {
				
				// remove current entity with projectId
				projectRepository.delete(projectId);
				
				// add new entity
				projectRepository.save(project);
				
				return project;
			}
		}

		return null;
	}
	
}
