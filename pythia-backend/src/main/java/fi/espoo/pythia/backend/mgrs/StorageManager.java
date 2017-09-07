package fi.espoo.pythia.backend.mgrs;

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

	public void createProject(String projectName) {
		Project project = new Project();
		project.setCreatedAt(null);
		
		Project savedProject = projectRepository.save(project);
		
		// planRepository.save(5L);

		//planRepository.
		
	}

}
