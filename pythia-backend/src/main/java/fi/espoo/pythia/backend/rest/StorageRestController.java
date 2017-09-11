package fi.espoo.pythia.backend.rest;

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

@RestController
@RequestMapping("/pythia/v1")
public class StorageRestController {	
	
	@Autowired
	private StorageManager storageManager;

	/**
	 * return a single project by id if found. Otherwise return null.
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/projects/{projectId}")
	@RequestMapping(produces = {"application/json"})
	public ResponseEntity<Project> getProject(@PathVariable("projectId") Long projectId) {
		
		Project project = storageManager.getProject(projectId);
		if (project == null) {
			
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
		
			return new ResponseEntity<Project>(project,HttpStatus.OK);
		
	}
	

	/**
	 * create a new project to the db and return the whole project
	 * with all attributes
	 * @param projectValue
	 * @return
	 */
	@PostMapping("/projects")
	@RequestMapping(produces = {"application/json"})
	public ResponseEntity createProject(@RequestBody Project project) {
	
		// Value object mapping
		storageManager.createProject(project);
		return new ResponseEntity(project, HttpStatus.OK);
	}

	
	
	@PutMapping("/projects/{projectId}")
	public void updateProject() {

	}

	@DeleteMapping("/projects/{projectId}")
	public void deleteProject() {

	}
}
