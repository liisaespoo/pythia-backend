package fi.espoo.pythia.backend.mgrs;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.espoo.pythia.backend.repos.PlanRepository;
import fi.espoo.pythia.backend.repos.ProjectRepository;

@Component
@Transactional
public class StorageManager {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ProjectRepository projectRepository;

	public void example() {
		planRepository.delete(5L);

		projectRepository.delete(5L);
	}

}
