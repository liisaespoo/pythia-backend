package fi.espoo.pythia.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.espoo.pythia.backend.mgrs.StorageManager;

@RestController
public class StorageRestController {

	@Autowired
	private StorageManager storageManager;
	
	@PostMapping
	public void createProject(String projectName ) {

		storageManager.createProject(projectName);
		
	}

	@GetMapping
	public void getProject() {

	}

	@PutMapping
	public void updateProject() {

	}

	@DeleteMapping
	public void deleteProject() {

	}
}
