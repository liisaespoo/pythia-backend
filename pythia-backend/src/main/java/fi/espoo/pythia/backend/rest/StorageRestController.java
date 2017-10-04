/**
 * input validation (kantaan ei saa mennä kuin valvottuja arvoja)
 * 
 * päätös on tehty
 * suunnitelma on hyväksytty
 * suunnitelma on muutettu
 * 
 * 
 * 
 * http://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services
 * 
 * 
 */

package fi.espoo.pythia.backend.rest;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fi.espoo.pythia.backend.mappers.PrjVal2ToPrj;
import fi.espoo.pythia.backend.mgrs.S3Manager;
import fi.espoo.pythia.backend.mgrs.StorageManager;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.transfer.CommentValue;
import fi.espoo.pythia.backend.transfer.PlanValue;
import fi.espoo.pythia.backend.transfer.ProjectValue;
import fi.espoo.pythia.backend.transfer.ProjectValue2;
import fi.espoo.pythia.backend.transfer.SisterProjectValue;

@RestController
@RequestMapping("/pythia/v1")
public class StorageRestController {

	@Autowired
	private StorageManager storageManager;
	
	@Autowired
	private S3Manager s3Manager;

	// -------------------------GET-------------------------------

//	/**
//	 * return all projects
//	 * 
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@GetMapping(value = "/projects/", produces = "application/json")
//	public ResponseEntity<List<ProjectValue>> getProject() {
//
//		try {
//			List<ProjectValue> project = storageManager.getProjects();
//			return new ResponseEntity<List<ProjectValue>>(project, HttpStatus.OK);
//		} catch (java.lang.NullPointerException e) {
//			return new ResponseEntity<List<ProjectValue>>(HttpStatus.NOT_FOUND);
//		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
//			return new ResponseEntity<List<ProjectValue>>(HttpStatus.FORBIDDEN);
//		}
//	}

	
	/**
	 * return all projects
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/projects/", produces = "application/json")
	public ResponseEntity<List<ProjectValue2>> getProject() {

		try {
			List<ProjectValue2> project = storageManager.getProjects2();
			return new ResponseEntity<List<ProjectValue2>>(project, HttpStatus.OK);
		} catch (java.lang.NullPointerException e) {
			return new ResponseEntity<List<ProjectValue2>>(HttpStatus.NOT_FOUND);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<List<ProjectValue2>>(HttpStatus.FORBIDDEN);
		}
	}
	
	
//	
//	/**
//	 * return a single project by id if found. Otherwise return null.
//	 */
//	@SuppressWarnings("unchecked")
//	@GetMapping(value = "/projects/{projectId}", produces = "application/json")
//	public ResponseEntity<ProjectValue> getProject(@PathVariable("projectId") Long projectId) {
//
//		try {
//			ProjectValue project = storageManager.getProject(projectId);
//
//			return new ResponseEntity<ProjectValue>(project, HttpStatus.OK);
//		} catch (java.lang.NullPointerException e) {
//			return new ResponseEntity<ProjectValue>(new ProjectValue(), HttpStatus.NOT_FOUND);
//		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
//			return new ResponseEntity<ProjectValue>(HttpStatus.FORBIDDEN);
//		}
//	}
	
	
	
	/**
	 * return a single project by id if found. Otherwise return null.
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/projects/{projectId}", produces = "application/json")
	public ResponseEntity<ProjectValue2> getProject(@PathVariable("projectId") Long projectId) {

		try {
			ProjectValue2 project = storageManager.getProject2(projectId);

			return new ResponseEntity<ProjectValue2>(project, HttpStatus.OK);
		} catch (java.lang.NullPointerException e) {
			return new ResponseEntity<ProjectValue2>(new ProjectValue2(), HttpStatus.NOT_FOUND);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<ProjectValue2>(HttpStatus.FORBIDDEN);
		}
	}
	
	
	

//	/**
//	 * return a single project by hansuprojectid if found. Otherwise return
//	 * null.
//	 */
//	@SuppressWarnings("unchecked")
//	@GetMapping(value = "/projects/hansuprojectid/{hansuProjectId}", produces = "application/json")
//	public ResponseEntity<ProjectValue> getHansuProject(@PathVariable("hansuProjectId") String hansuId) {
//
//		try {
//			ProjectValue project = storageManager.getProjectByHansuId(hansuId);
//			System.out.println("project:" + project);
//			if (project == null) {
//				return new ResponseEntity<ProjectValue>(project, HttpStatus.NOT_FOUND);
//			}
//			return new ResponseEntity<ProjectValue>(project, HttpStatus.OK);
//		} catch (java.lang.NullPointerException e) {
//			return new ResponseEntity<ProjectValue>(new ProjectValue(), HttpStatus.NOT_FOUND);
//		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
//			return new ResponseEntity<ProjectValue>(HttpStatus.FORBIDDEN);
//		}
//	}

	
	/**
	 * return a single project by hansuprojectid if found. Otherwise return
	 * null.
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/projects/hansuprojectid/{hansuProjectId}", produces = "application/json")
	public ResponseEntity<ProjectValue2> getHansuProject(@PathVariable("hansuProjectId") String hansuId) {

		try {
			ProjectValue2 project = storageManager.getProjectByHansuId2(hansuId);
			//System.out.println("project:" + project);
			if (project == null) {
				return new ResponseEntity<ProjectValue2>(project, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ProjectValue2>(project, HttpStatus.OK);
		} catch (java.lang.NullPointerException e) {
			return new ResponseEntity<ProjectValue2>(new ProjectValue2(), HttpStatus.NOT_FOUND);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<ProjectValue2>(HttpStatus.FORBIDDEN);
		}
	}

	
	
	/**
	 * 
	 * return all plans by projectId
	 * 
	 * @param projectId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/projects/{projectId}/plans/", produces = "application/json")
	public ResponseEntity<List<PlanValue>> getPlan(@PathVariable("projectId") Long projectId) {

		try {
			List<PlanValue> plan = storageManager.getPlans(projectId);
			return new ResponseEntity<List<PlanValue>>(plan, HttpStatus.OK);
		} catch (java.lang.NullPointerException e) {
			return new ResponseEntity<List<PlanValue>>(HttpStatus.NOT_FOUND);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<List<PlanValue>>(HttpStatus.FORBIDDEN);
		}

	}

	// --------------------------POST-------------------------------------

	/**
	 * create a new plan to the db and return the whole project with all
	 * attributes
	 * 
	 * @param projectValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/projects/{projectId}/plans/", produces = "application/json", consumes = "application/json")
	public ResponseEntity<PlanValue> createPlan(@RequestBody PlanValue planV) {

		// catch exception database connection
		//
		// Value object mapping
		try {
			PlanValue savedPlan = storageManager.createPlan(planV);
			return new ResponseEntity<PlanValue>(savedPlan, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<PlanValue>(HttpStatus.FORBIDDEN);
		}

	}
	
	/**
	 * create a new plan to the db and return the whole project with all
	 * attributes
	 * 
	 * @param projectValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/projects/{projectId}/plans/{planId}/comments/", produces = "application/json", consumes = "application/json")
	public ResponseEntity<PlanValue> createComment(@RequestBody CommentValue commV) {

		// catch exception database connection
		//
		// Value object mapping
	return null;

	}

//	/**
//	 * create a new project to the db and return the whole project with all
//	 * attributes
//	 * 
//	 * @param projectValue
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@PostMapping(value = "/projects/", produces = "application/json", consumes = "application/json")
//	public ResponseEntity<ProjectValue> createProject(@RequestBody ProjectValue project) {
//
//		// Value object mapping
//		try {
//			ProjectValue savedProject = storageManager.createProject(project);
//			return new ResponseEntity<ProjectValue>(savedProject, HttpStatus.OK);
//		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
//			return new ResponseEntity<ProjectValue>(HttpStatus.FORBIDDEN);
//		}
//
//	}
	
	
	/**
	 * create a new project to the db and return the whole project with all
	 * attributes
	 * 
	 * @param projectValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/projects/", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ProjectValue2> createProject2(@RequestBody ProjectValue2 project) {

		// Value object mapping
		try {
			ProjectValue2 savedProject = storageManager.createProject2(project);
			return new ResponseEntity<ProjectValue2>(savedProject, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<ProjectValue2>(HttpStatus.FORBIDDEN);
		}

	}
	
	
//	@SuppressWarnings("unchecked")
//	@PostMapping(value = "/projects/{projectId}/plans/{planId}/files/", produces = "application/json", consumes = "application/json")
//	public ResponseEntity<String> createPlanFile(@RequestBody FileValue base64) {
//
//		System.out.println(base64);
//		
//		// Value object mapping
//		try {
//			String savedImage = s3Manager.createPlanFileBase64("1test", "kirapythia-example-bucket", base64);
//			if (savedImage.isEmpty() || savedImage == null) {
//				return new ResponseEntity<String>("",HttpStatus.NOT_FOUND);
//			}
//			return new ResponseEntity<String>(savedImage, HttpStatus.OK);
//		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
//			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
//		}
//
//	}
	
	
	//, produces = "application/json", consumes = "file"
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/projects/{projectId}/plans/{planId}/files/")
	public ResponseEntity<String> createPlanFile(@RequestParam("mfile") MultipartFile mfile,   @PathVariable("planId") long id) {
		
		System.out.println("id:"+id);
		// Value object mapping
		try {
			
			
			PlanValue planV = storageManager.getPlan(id);
			
			if(planV == null){
				return new ResponseEntity<String>("",HttpStatus.NOT_FOUND);
			}
			
			String savedImageUrl = s3Manager.createPlanMultipartFile("1test", "kirapythia-example-bucket", mfile);
			
			// set PlanValue url 
			planV.setUrl(savedImageUrl);
			//update Plan with url
			storageManager.updatePlan(planV);
	
			if (savedImageUrl.isEmpty() || savedImageUrl == null) {
				return new ResponseEntity<String>("",HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<String>(savedImageUrl, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
		}

	}
	
	
	

	// ---------------------------PUT--------------------------------

	/**
	 * update a plan of a project
	 */
	@SuppressWarnings("unchecked")
	@PutMapping(value = "/projects/{projectId}/plans/{planId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<PlanValue> updatePlan(@RequestBody PlanValue planV) {

		try {
			PlanValue updatedPlan = storageManager.updatePlan(planV);
			return new ResponseEntity<PlanValue>(updatedPlan, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<PlanValue>(HttpStatus.FORBIDDEN);
		}

	}

//	/**
//	 * update a project
//	 */
//	@SuppressWarnings("unchecked")
//	@PutMapping(value = "/projects/{projectId}", produces = "application/json", consumes = "application/json")
//	public ResponseEntity<ProjectValue> updateProject(@RequestBody ProjectValue projectV) {
//
//		try {
//			List<SisterProjectValue> spv = projectV.getSisterProjects();
//			storageManager.updateSisterProjects(spv);
//			ProjectValue updatedProject = storageManager.updateProject(projectV);
//			
//			return new ResponseEntity<ProjectValue>(updatedProject, HttpStatus.OK);
//		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
//			return new ResponseEntity<ProjectValue>(HttpStatus.FORBIDDEN);
//		}
//
//	}
	
	
	/**
	 * update a project with List<Long> sisterProjectIds
	 */
	@SuppressWarnings("unchecked")
	@PutMapping(value = "/projects/{projectId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ProjectValue2> updateProject2(@RequestBody ProjectValue2 projectV) {

		try {
		storageManager.updateProject2(projectV);
		ProjectValue2 updatedProject = storageManager.getProject2(projectV.getProjectId());
			return new ResponseEntity<ProjectValue2>(updatedProject, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<ProjectValue2>(HttpStatus.FORBIDDEN);
		}

	}

	// ------------------------ NOT DONE --------------------------

	/**
	 * not done
	 */
	@DeleteMapping("/projects/{projectId}")
	public void deleteProject() {

	}
}
