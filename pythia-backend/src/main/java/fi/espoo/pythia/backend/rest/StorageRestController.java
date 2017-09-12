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

import fi.espoo.pythia.backend.transfer.ProjectValue;

@RestController
@RequestMapping("/pythia/v1")
public class StorageRestController {	
	
	@Autowired
	private StorageManager storageManager;

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
			return new ResponseEntity<ProjectValue>(project,HttpStatus.OK);		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/test/", produces = "application/json")
	public ResponseEntity<List> getProject() {
		
		List<ProjectValue> project = storageManager.getProjects();
		if (project == null) {
			
			return new ResponseEntity<List>(HttpStatus.NOT_FOUND);
		}		
			return new ResponseEntity<List>(project,HttpStatus.OK);		
	}
	
	
	//------------------------ NOT DONE --------------------------
	
	/**
	 * create a new project to the db and return the whole project
	 * with all attributes
	 * @param projectValue
	 * @return
	 */
	@PostMapping(value = "/projects/", produces = "application/json", consumes = "application/json")
	public ResponseEntity createProject(@RequestBody ProjectValue project) {
	
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
