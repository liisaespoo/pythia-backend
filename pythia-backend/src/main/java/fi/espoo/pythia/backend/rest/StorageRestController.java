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
 *  When posting multipart file add size by tweaking both 
 *  1)src/main/resources application.propeties and 
 *  2)PythiaBackendApplication.class Tomcat setMaxPostSize Bean  
 *   
 */

package fi.espoo.pythia.backend.rest;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fi.espoo.pythia.backend.mgrs.S3Manager;
import fi.espoo.pythia.backend.mgrs.SESManager;
import fi.espoo.pythia.backend.mgrs.StorageManager;
import fi.espoo.pythia.backend.repos.entities.Ptext;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.repos.entities.ProjectUpdate;
import fi.espoo.pythia.backend.transfer.PtextValue;
import fi.espoo.pythia.backend.transfer.LatestPlansValue;
import fi.espoo.pythia.backend.transfer.PlanValue;
import fi.espoo.pythia.backend.transfer.ProjectUpdateValue;
import fi.espoo.pythia.backend.transfer.ProjectValue2;

@RestController
@RequestMapping("/pythia/v1")
public class StorageRestController {

	@Autowired
	private StorageManager storageManager;

	@Autowired
	private S3Manager s3Manager;

	// -------------------------GET-------------------------------

	/**
	 * return all projects
	 * 
	 * @return
	 */
	@GetMapping(value = "/projects/", produces = "application/json")
	public ResponseEntity<List<ProjectValue2>> getProjects() {

		try {
			List<ProjectValue2> project = storageManager.getProjects2();
			return new ResponseEntity<List<ProjectValue2>>(project, HttpStatus.OK);
		} catch (java.lang.NullPointerException e) {
			return new ResponseEntity<List<ProjectValue2>>(HttpStatus.NOT_FOUND);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<List<ProjectValue2>>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * return a single project by id if found. Otherwise return null.
	 */
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

	/**
	 * return a single project by hansuprojectid if found. Otherwise return
	 * null.
	 */
	@GetMapping(value = "/projects/hansuprojectid/{hansuProjectId}", produces = "application/json")
	public ResponseEntity<ProjectValue2> getHansuProject(@PathVariable("hansuProjectId") String hansuId) {

		try {
			ProjectValue2 project = storageManager.getProjectByHansuId2(hansuId);
			// System.out.println("project:" + project);
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
	 * return plans by projectId
	 * 
	 * @param projectId
	 * @return
	 */
	@GetMapping(value = "/projects/{projectId}/plans/", produces = "application/json")
	public ResponseEntity<List<PlanValue>> getPlans(@PathVariable("projectId") Long projectId) {

		try {
			List<PlanValue> plan = storageManager.getPlans(projectId);
			return new ResponseEntity<List<PlanValue>>(plan, HttpStatus.OK);
		} catch (java.lang.NullPointerException e) {
			return new ResponseEntity<List<PlanValue>>(HttpStatus.NOT_FOUND);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<List<PlanValue>>(HttpStatus.FORBIDDEN);
		}

	}

	// /**
	// *
	// * return all comments by planId
	// *
	// * @param projectId
	// * @return
	// */
	// @GetMapping(value = "/projects/{projectId}/plans/{planId}/comments/",
	// produces = "application/json")
	// public ResponseEntity<List<CommentValue>>
	// getComments(@PathVariable("planId") Long planId) {
	//
	// try {
	// List<CommentValue> commList = storageManager.getComments(planId);
	// return new ResponseEntity<List<CommentValue>>(commList, HttpStatus.OK);
	// } catch (java.lang.NullPointerException e) {
	// return new ResponseEntity<List<CommentValue>>(HttpStatus.NOT_FOUND);
	// } catch (org.springframework.transaction.CannotCreateTransactionException
	// e) {
	// return new ResponseEntity<List<CommentValue>>(HttpStatus.FORBIDDEN);
	// }
	//
	// }

	/**
	 * 
	 * return all comments by planId
	 * 
	 * @param projectId
	 * @return
	 */
	@GetMapping(value = "/projects/{projectId}/plans/{planId}/comments/", produces = "application/json")
	public ResponseEntity<List<PtextValue>> getComments(@PathVariable("planId") Long planId) {

		try {
			List<PtextValue> commList = storageManager.getComments(planId);
			return new ResponseEntity<List<PtextValue>>(commList, HttpStatus.OK);
		} catch (java.lang.NullPointerException e) {
			return new ResponseEntity<List<PtextValue>>(HttpStatus.NOT_FOUND);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<List<PtextValue>>(HttpStatus.FORBIDDEN);
		}

	}

	// --------------------------POST-------------------------------------

	/**
	 * create a new plan to the db and return the whole project with all
	 * attributes
	 * 
	 * 
	 * Checks if 1st version and if approved
	 * 
	 * If the 1st then version = 0 and approved = true
	 * 
	 * If not the 1st then increase version number by one
	 * 
	 * @param projectValue
	 * @return
	 */
	@PostMapping(value = "/projects/{projectId}/plans/", produces = "application/json", consumes = "application/json")
	public ResponseEntity<PlanValue> createPlan(@RequestBody PlanValue planV) {

		// TODO if id the return error
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
	@PostMapping(value = "/projects/{projectId}/plans/{planId}/comments/", produces = "application/json", consumes = "application/json")
	public ResponseEntity<PtextValue> createComment(@RequestBody PtextValue pTextVal, @PathVariable("planId") long id) {

		// TODO if id the return error
		try {
			PtextValue savedPtext = storageManager.createPtext(pTextVal, id);
			return new ResponseEntity<PtextValue>(savedPtext, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<PtextValue>(HttpStatus.FORBIDDEN);
		}

	}

	/**
	 * create a new project to the db and return the whole project with all
	 * attributes
	 * 
	 * @param projectValue
	 * @return
	 */
	@PostMapping(value = "/projects/", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ProjectValue2> createProject(@RequestBody ProjectUpdateValue projectUpVal) {

		// TODO if id the return error
		// Value object mapping
		try {
			ProjectUpdate pu = storageManager.createProject(projectUpVal);
			ProjectValue2 savedProject = storageManager.getProject2(pu.getProjectId());
			return new ResponseEntity<ProjectValue2>(savedProject, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<ProjectValue2>(HttpStatus.FORBIDDEN);
		}

	}

	//
	// //, produces = "application/json", consumes = "file"
	// @PostMapping(value = "/projects/{projectId}/plans/{planId}/files/",
	// produces = "application/json", consumes = "multipart/form-data")
	// public ResponseEntity<String> createPlanFile(@RequestPart("mfile")
	// MultipartFile mfile, @RequestPart("plan") @Valid ConnectionProperties
	// plan, @PathVariable("planId") long id) {
	//
	// System.out.println("id:"+id);
	// // Value object mapping
	// try {
	//
	//
	// PlanValue planV = storageManager.getPlan(id);
	//
	// if(planV == null){
	// return new ResponseEntity<String>("",HttpStatus.NOT_FOUND);
	// }
	//
	// String savedImageUrl =
	// s3Manager.createPlanMultipartFile("kirapythia-plans-bucket", mfile);
	//
	// // set PlanValue url
	// planV.setUrl(savedImageUrl);
	// //update Plan with url
	// storageManager.updatePlan(planV);
	//
	// if (savedImageUrl.isEmpty() || savedImageUrl == null) {
	// return new ResponseEntity<String>("",HttpStatus.NOT_FOUND);
	// }
	// return new ResponseEntity<String>(savedImageUrl, HttpStatus.OK);
	// } catch (org.springframework.transaction.CannotCreateTransactionException
	// e) {
	// return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	// } catch (IOException e) {
	// e.printStackTrace();
	// return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
	// }
	//
	// }
	//

	/**
	 * @PostMapping(value =
	 *                    "/projects/{projectId}/plans/{planId}/comments/{commentId}/files/"
	 *                    , produces = "application/json", consumes =
	 *                    "multipart/form-data") public ResponseEntity<String>
	 *                    createCommentFile(@RequestPart("mfile") MultipartFile
	 *                    mfile, @RequestPart("plan") @Valid
	 *                    ConnectionProperties properties)
	 * @param mfile
	 * @param id
	 * @return
	 * 
	 * When posting multipart file add size by teaking both src/main/resources application.propeties and PythiaBackendApplication.class Tomcat setMaxPostSize Bean  
	 * 
	 */
	// , produces = "application/json", consumes = "file"
	@PostMapping(value = "/projects/{projectId}/plans/{planId}/files/")
	public ResponseEntity<String> createPlanFile(@RequestParam("mfile") MultipartFile mfile,
			@PathVariable("planId") long id) {

		System.out.println("id:" + id);
		
		SESManager sesManager = new SESManager();
		// Value object mapping
		try {

			PlanValue planV = storageManager.getPlan(id);
		    ProjectValue2 p = storageManager.getProject2(planV.getProjectId());

			if (planV == null) {
				return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
			}

			String savedImageUrl = s3Manager.createPlanMultipartFile("kirapythia-plans-bucket", mfile);

			// set PlanValue url
			planV.setUrl(savedImageUrl);
			// update Plan with url
			storageManager.updatePlan(planV);

			if (savedImageUrl.isEmpty() || savedImageUrl == null) {
				return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
			}
			
			String project = p.getName();
			String projectId = p.getProjectId().toString();
			
			
			sesManager.newVersion(project, projectId, savedImageUrl);
			return new ResponseEntity<String>(savedImageUrl, HttpStatus.OK);
			
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
		}

	}

	// @PostMapping(value =
	// "/projects/{projectId}/plans/{planId}/comments/{commentId}/files/" ,
	// produces = "application/json", consumes = "multipart/form-data")
	// public ResponseEntity<String> createCommentFile(@RequestPart("mfile")
	// MultipartFile mfile, @RequestPart("plan") @Valid ConnectionProperties
	// properties) {
	//
	//
	// // Value object mapping
	// try {
	//
	//
	// PtextValue ptextVal = storageManager.getComment(id);
	//
	// if(ptextVal == null){
	// return new ResponseEntity<String>("",HttpStatus.NOT_FOUND);
	// }
	//
	// String savedImageUrl =
	// s3Manager.createPlanMultipartFile("kirapythia-comments-bucket", mfile);
	//
	// // set PlanValue url
	// ptextVal.setUrl(savedImageUrl);
	// //update Plan with url
	// storageManager.updatePtext(ptextVal);
	//
	// if (savedImageUrl.isEmpty() || savedImageUrl == null) {
	// return new ResponseEntity<String>("",HttpStatus.NOT_FOUND);
	// }
	// return new ResponseEntity<String>(savedImageUrl, HttpStatus.OK);
	// } catch (org.springframework.transaction.CannotCreateTransactionException
	// e) {
	// return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	// } catch (IOException e) {
	// e.printStackTrace();
	// return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
	// }
	//
	// }
	//

	@PostMapping(value = "/projects/{projectId}/plans/{planId}/comments/{commentId}/files/")
	public ResponseEntity<String> createCommentFile(@RequestParam("mfile") MultipartFile mfile,
			@PathVariable("commentId") long id) {

		// Value object mapping
		try {

			PtextValue ptextVal = storageManager.getComment(id);

			if (ptextVal == null) {
				return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
			}

			String savedImageUrl = s3Manager.createPlanMultipartFile("kirapythia-comments-bucket", mfile);

			// set PlanValue url
			ptextVal.setUrl(savedImageUrl);
			// update Plan with url
			storageManager.updatePtext(ptextVal,id);

			if (savedImageUrl.isEmpty() || savedImageUrl == null) {
				return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<String>(savedImageUrl, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
		}

	}

	// ---------------------------PUT--------------------------------

	/**
	 * update a plan of a project
	 */

	@PutMapping(value = "/projects/{projectId}/plans/{planId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<PlanValue> updatePlan(@RequestBody PlanValue planV) {

		try {
			PlanValue updatedPlan = storageManager.updatePlan(planV);
			PlanValue returnPlan = storageManager.getPlan(updatedPlan.getPlanId());
			return new ResponseEntity<PlanValue>(returnPlan, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<PlanValue>(HttpStatus.FORBIDDEN);
		}

	}

	/**
	 * update a project with List<Long> sisterProjectIds
	 */
	@PutMapping(value = "/projects/{projectId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ProjectValue2> updateProject(@RequestBody ProjectUpdateValue projectUpVal) {

		try {
			storageManager.updateProject(projectUpVal);
			ProjectValue2 updatedProject = storageManager.getProject2(projectUpVal.getProjectId());
			return new ResponseEntity<ProjectValue2>(updatedProject, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<ProjectValue2>(HttpStatus.FORBIDDEN);
		}
	}

	@PutMapping(value = "/projects/{projectId}/plans/{planId}/comments/{commentId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<PtextValue> updateComment(@RequestBody PtextValue pTextVal,
			@PathVariable("commentId") long id) {

		// TODO
		try {
			PtextValue savedPtext = storageManager.updatePtext(pTextVal, id);
			return new ResponseEntity<PtextValue>(savedPtext, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<PtextValue>(HttpStatus.FORBIDDEN);
		}

	}

	// ------------------------ NOT DONE --------------------------

	/**
	 * not done
	 */
	@DeleteMapping("/projects/{projectId}/plans/{planId}")
	public ResponseEntity<ProjectValue2> deletePlan(@PathVariable("projectId") long pid,
			@PathVariable("planId") long id) {
		try {
			storageManager.deletePlan(id);
			ProjectValue2 updatedProject = storageManager.getProject2(pid);
			return new ResponseEntity<ProjectValue2>(updatedProject, HttpStatus.OK);
		} catch (org.springframework.transaction.CannotCreateTransactionException e) {
			return new ResponseEntity<ProjectValue2>(HttpStatus.FORBIDDEN);
		}

	}
}
