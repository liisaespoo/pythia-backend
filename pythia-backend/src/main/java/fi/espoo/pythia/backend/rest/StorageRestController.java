/**
 * input validation (kantaan ei saa mennä kuin valvottuja arvoja)
 * 
 * päätös on tehty
 * suunnitelma on hyväksytty
 * suunnitelma on muutettu
 * 
 * 
 */

package fi.espoo.pythia.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.espoo.pythia.backend.mgrs.StorageManager;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.transfer.PlanValue;
import fi.espoo.pythia.backend.transfer.ProjectValue;

@RestController
@RequestMapping("/pythia/v1")
public class StorageRestController {

	@Autowired
	private StorageManager storageManager;

	// -------------------------GET-------------------------------
	
	
	/**
	 * return all projects
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/projects/", produces = "application/json")
	public ResponseEntity<List<ProjectValue>> getProject() {

		List<ProjectValue> project = storageManager.getProjects();
		if (project == null) {

			return new ResponseEntity<List<ProjectValue>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<ProjectValue>>(project, HttpStatus.OK);
	}
	
	/**
	 * return a single project by id if found. Otherwise return null.
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/projects/{projectId}", produces = "application/json")
	public ResponseEntity<ProjectValue> getProject(@PathVariable("projectId") Long projectId) {

		ProjectValue project = storageManager.getProject(projectId);
		if (project == null) {

			return new ResponseEntity<ProjectValue>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProjectValue>(project, HttpStatus.OK);
	}
	
	/**
	 * return a single project by hansuprojectid if found. Otherwise return null.
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/projects/hansuprojectid/{hansuProjectId}", produces = "application/json")
	public ResponseEntity<ProjectValue> getHansuProject(@PathVariable("hansuProjectId") String hansuId) {

		ProjectValue project = storageManager.getProjectByHansuId(hansuId);
		if (project == null) {

			return new ResponseEntity<ProjectValue>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProjectValue>(project, HttpStatus.OK);
	}


	/**
	 * 
	 * return all plans by projectId
	 * @param projectId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/plans/projectId/{projectId}", produces = "application/json")
	public ResponseEntity<List<PlanValue>> getPlan(@PathVariable("projectId") Long projectId) {

		List<PlanValue> plan = storageManager.getPlans(projectId);
		if (plan == null) {

			return new ResponseEntity<List<PlanValue>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PlanValue>>(plan, HttpStatus.OK);
	}


	
	
	

	// --------------------------POST-------------------------------------

	/**
	 * create a new plan to the db and return the whole project with all attributes
	 * 
	 * @param projectValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/plans/", produces = "application/json", consumes = "application/json")
	public ResponseEntity<PlanValue> createPlan(@RequestBody PlanValue planV) {

		// Value object mapping
		PlanValue savedPlan = storageManager.createPlan(planV);

		return new ResponseEntity<PlanValue>(savedPlan, HttpStatus.OK);
	}

	/**
	 * create a new project to the db and return the whole project with all
	 * attributes
	 * 
	 * @param projectValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/projects/", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ProjectValue> createProject(@RequestBody ProjectValue project) {

		// Value object mapping
		ProjectValue savedProject = storageManager.createProject(project);

		return new ResponseEntity<ProjectValue>(savedProject, HttpStatus.OK);
	}

	// ---------------------------PUT--------------------------------

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PutMapping(value = "/plans/{planId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<PlanValue> updatePlan(@PathVariable("planId") Long planId, @RequestBody PlanValue planV) {

		if(planId.equals(planV.getPlanId())) {
			PlanValue updatedPlan = storageManager.updatePlan(planV);
			return new ResponseEntity<PlanValue>(updatedPlan, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<PlanValue>(planV, HttpStatus.CONFLICT);
		}
		

	}

	// ------------------------ NOT DONE --------------------------

	@PutMapping("/projects/{projectId}")
	public void updateProject() {

	}

	@DeleteMapping("/projects/{projectId}")
	public void deleteProject() {

	}
}
