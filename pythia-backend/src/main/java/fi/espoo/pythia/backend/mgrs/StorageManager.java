package fi.espoo.pythia.backend.mgrs;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.espoo.pythia.backend.mappers.PlanToPlanValueMapper;
import fi.espoo.pythia.backend.mappers.PlanValueToPlanMapper;
import fi.espoo.pythia.backend.mappers.ProjectToProjectValueMapper;
import fi.espoo.pythia.backend.mappers.ProjectValueToProjectMapper;
import fi.espoo.pythia.backend.repos.PlanRepository;
import fi.espoo.pythia.backend.repos.ProjectRepository;
import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.transfer.PlanValue;
import fi.espoo.pythia.backend.transfer.ProjectValue;

@Component
@Transactional
public class StorageManager {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ProjectRepository projectRepository;

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

		Project project = projectRepository.findByProjectId(projectId);
		ProjectValue pval = ProjectToProjectValueMapper.projectToProjectValue(project);
		return pval;

		// List<ProjectValue> projects = getProjects();
		// for (ProjectValue p : projects) {
		// if (p.getProjectId().equals(projectId)) {
		// return p;
		// }
		// }
		// return null;
	}

	/**
	 * 
	 * Update the project object for given id in database. If project does not
	 * exists, return null DONE!!!
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

		// map projectV to project
		Project prj = ProjectValueToProjectMapper.projectValueToProject(projectV);

		// timestamp with time at db or microservice level
		// prj.setCreatedAt(null);
		Project savedProject = projectRepository.save(prj);

		ProjectValue savedProjectValue = ProjectToProjectValueMapper.projectToProjectValue(savedProject);
		// planRepository.save(5L);

		// planRepository.
		return savedProjectValue;
	}

	/**
	 * 
	 * @return ProjectValue that the plan was added to
	 */
	public PlanValue createPlan(PlanValue planV) {

		Long projectId = planV.getProjectId();
		// get project by projectid
		Project project = projectRepository.findByProjectId(projectId);

		// map planV to plan
		Plan plan = PlanValueToPlanMapper.planValueToPlan(planV, project);
		Plan savedPlan = planRepository.save(plan);

		PlanValue savedPlanValue = PlanToPlanValueMapper.planToPlanValue(savedPlan, project);
		// finally
		return savedPlanValue;
	}

	public List<PlanValue> getPlans(Long projectId) {

		Project project = projectRepository.findByProjectId(projectId);
		ProjectValue pval = ProjectToProjectValueMapper.projectToProjectValue(project);

		List<PlanValue> planValues = new ArrayList();

		for (Plan plan : pval.getPlans()) {
			// map each plan to planValue
			PlanValue planValue = PlanToPlanValueMapper.planToPlanValue(plan, project);
			planValues.add(planValue);
		}
		return planValues;

	}

	public PlanValue updatePlan(PlanValue planV) {

		Long id = planV.getProjectId();
		Project project = projectRepository.findByProjectId(id);
		Plan plan = PlanValueToPlanMapper.planValueToPlan(planV, project);

		Plan updatedPlan = planRepository.save(plan);

		PlanValue updatedPlanValue = PlanToPlanValueMapper.planToPlanValue(updatedPlan, project);
		return updatedPlanValue;
		
	}

}
