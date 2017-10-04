package fi.espoo.pythia.backend.mgrs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import fi.espoo.pythia.backend.converters.FileConverter;
import fi.espoo.pythia.backend.encoders.EncoderBase64;
import fi.espoo.pythia.backend.mappers.PlanToPlanValueMapper;
import fi.espoo.pythia.backend.mappers.PlanValueToPlanMapper;
import fi.espoo.pythia.backend.mappers.PrjToPrjVal2;
import fi.espoo.pythia.backend.mappers.PrjVal2ToPrj;
import fi.espoo.pythia.backend.repos.PlanRepository;
import fi.espoo.pythia.backend.repos.ProjectRepository;
import fi.espoo.pythia.backend.repos.SisterProjectRepository;
import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.repos.entities.SisterProject;
import fi.espoo.pythia.backend.transfer.PlanValue;
import fi.espoo.pythia.backend.transfer.ProjectValue;
import fi.espoo.pythia.backend.transfer.ProjectValue2;

@Component
@Transactional
public class StorageManager {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private SisterProjectRepository sisterProjectRepository;
	// ---------------------GET------------------------------------


	/**
	 * NEW
	 * 
	 * @return list of projects
	 */
	public ArrayList<ProjectValue2> getProjects2() {

		ArrayList<Project> prjList = (ArrayList<Project>) projectRepository.findAll();
		ArrayList<ProjectValue2> prjValList = new ArrayList();

		// for -loop for prjList

		for (Project p : prjList) {
			// map each project to projectValue
			ProjectValue2 pval = PrjToPrjVal2.ProjectToProjectValue2(p);
			prjValList.add(pval);
		}
		// return projectValue -ArrayList
		return prjValList;
	}
	
	/**
	 * Return project object for given id from database. If project is not found
	 * for id, returns null. DONE
	 */
	public ProjectValue2 getProject2(Long projectId) {

		Project project = projectRepository.findByProjectId(projectId);
		ProjectValue2 pval = PrjToPrjVal2.ProjectToProjectValue2(project);
		return pval;

	}
	
	
	/**
	 * 
	 * @param hansuId
	 * @return
	 */
	public ProjectValue2 getProjectByHansuId2(String hansuId) {
		List<Project> prjList = projectRepository.findAll();
		for (Project p : prjList) {
			if (p.getHansuProjectId().equals(hansuId)) {
				ProjectValue2 pval = PrjToPrjVal2.ProjectToProjectValue2(p);
				return pval;
			}
		}

		return null;
	}
	
	
//	/**
//	 * 
//	 * @param hansuId
//	 * @return
//	 */
//	public ProjectValue getProjectByHansuId(String hansuId) {
//		List<Project> prjList = projectRepository.findAll();
//		for (Project p : prjList) {
//			if (p.getHansuProjectId().equals(hansuId)) {
//				ProjectValue pval = ProjectToProjectValueMapper.projectToProjectValue(p);
//				return pval;
//			}
//		}
//
//		return null;
//	}

	/**
	 * get all plans by projectId
	 * 
	 * @param projectId
	 * @return
	 */
	public List<PlanValue> getPlans(Long projectId) {

		Project project = projectRepository.findByProjectId(projectId);
		ProjectValue2 pval = PrjToPrjVal2.ProjectToProjectValue2(project);

		//List<PlanValue> planValues = new ArrayList();

		// for (Plan plan : pval.getPlans()) {
		// // map each plan to planValue
		// PlanValue planValue = PlanToPlanValueMapper.planToPlanValue(plan,
		// project);
		// planValues.add(planValue);
		// }
		return pval.getPlans();

	}

	public PlanValue getPlan(Long planId) {

		Plan plan = planRepository.findByPlanId(planId);
		Project project = plan.getProject();
		PlanValue pVal = PlanToPlanValueMapper.planToPlanValue(plan, project);

		return pVal;
	}
	
	//-------------------OLD GET-----------------------------
	
//	/**
//	 * Returns list of projects from database. DONE!!!
//	 * 
//	 * @return list of projects
//	 */
//	public ArrayList<ProjectValue2> getProjects() {
//
//		ArrayList<Project> prjList = (ArrayList<Project>) projectRepository.findAll();
//		ArrayList<ProjectValue2> prjValList = new ArrayList();
//
//		// for -loop for prjList
//
//		for (Project p : prjList) {
//			// map each project to projectValue
//			ProjectValue2 pval = PrjToPrjVal2.ProjectToProjectValue2(p);
//			prjValList.add(pval);
//		}
//		// return projectValue -ArrayList
//		return prjValList;
//	}

	
//	/**
//	 * Return project object for given id from database. If project is not found
//	 * for id, returns null. DONE
//	 */
//	public ProjectValue getProject(Long projectId) {
//
//		Project project = projectRepository.findByProjectId(projectId);
//		ProjectValue pval = ProjectToProjectValueMapper.projectToProjectValue(project);
//		return pval;
//
//	}
	

	// ---------------------POST-----------------------------------

//	/**
//	 * Create new project in database. DONE!!!!
//	 * 
//	 */
//	public ProjectValue createProject(ProjectValue projectV) {
//
//		// map projectV to project
//		Project project = projectRepository.findByProjectId(projectV.getProjectId());
//		Project prj = ProjectValueToProjectMapper.projectValueToProject(projectV, project);
//
//		// timestamp with time at db or microservice level
//		// prj.setCreatedAt(null);
//		Project savedProject = projectRepository.save(prj);
//
//		ProjectValue savedProjectValue = ProjectToProjectValueMapper.projectToProjectValue(savedProject);
//		// planRepository.save(5L);
//
//		// planRepository.
//		return savedProjectValue;
//	}

	public ProjectValue2 createProject2(ProjectValue2 projectV) {

		Project projectTemp = projectRepository.findByProjectId(projectV.getProjectId());
		Project project = PrjVal2ToPrj.projectValue2ToProject(projectV, projectTemp);
		Project updatedProject = projectRepository.save(project);
		ProjectValue2 updatedProjectValue2 = PrjToPrjVal2.ProjectToProjectValue2(updatedProject);
		return updatedProjectValue2;

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

	// ---------------------PUT------------------------------------

//	/**
//	 * 
//	 * @param projectV
//	 * @return
//	 */
//	public ProjectValue updateProject(ProjectValue projectV) {
//
//		Project projectTemp = projectRepository.findByProjectId(projectV.getProjectId());
//		Project project = ProjectValueToProjectMapper.projectValueToProjectUpdate(projectV, projectTemp);
//		Project updatedProject = projectRepository.save(project);
//
//		ProjectValue updatedProjectValue = ProjectToProjectValueMapper.projectToProjectValue(updatedProject);
//		return updatedProjectValue;
//
//	}

	public void updateProject2(ProjectValue2 projectV) {

		Project projectTemp = projectRepository.findByProjectId(projectV.getProjectId());
		Project project = PrjVal2ToPrj.projectValue2ToProject(projectV, projectTemp);
		// update basic project table
		projectRepository.save(project);

		// update sisterProjects table
		updateSisterProjects(projectV, project);

//		ProjectValue2 updatedProjectValue2 = PrjToPrjVal2
//				.ProjectToProjectValue2(projectRepository.findByProjectId(projectV.getProjectId()));
//		return updatedProjectValue2;
	}

	public void updateSisterProjects(ProjectValue2 pv, Project project) {

		// get latest by repo id
		Project p = projectRepository.findByProjectId(pv.getProjectId());
		// create empty sisterprojects list
		List<SisterProject> sProjects = new ArrayList<SisterProject>();

		// first delete all rows with this project
		sisterProjectRepository.deleteByProject(project);
		Long j = 1L;
		// add sisterprojects to the db
		for (Long id : pv.getSisterProjects()) {
			System.out.println("updatesisterProjectId:"+id);
			SisterProject sProject = (new SisterProject(j, project, id));

			sisterProjectRepository.save(sProject);
			j++;
		}
	}

	/**
	 * 
	 * @param planV
	 * @return
	 */

	public PlanValue updatePlan(PlanValue planV) {

		Long id = planV.getProjectId();
		Project project = projectRepository.findByProjectId(id);
		Plan plan = PlanValueToPlanMapper.planValueToPlan(planV, project);

		Plan updatedPlan = planRepository.save(plan);

		PlanValue updatedPlanValue = PlanToPlanValueMapper.planToPlanValue(updatedPlan, project);
		return updatedPlanValue;

	}

	public String createPlanFile(String key, String bucketName, String json64base) throws IOException {

		// tarkista ettei ole null
		if (json64base.isEmpty() || json64base == null) {
			return null;
		}

		AmazonS3 s3client = authenticate();
		// encode base64 to InputStream
		S3ObjectInputStream imageStream = EncoderBase64.base64String2InputStream(json64base);

		File file = FileConverter.inputStreamToVirtualFile(imageStream);

		String url = uploadObject(s3client, file, key, bucketName);
		return url;

	}

	private AmazonS3 authenticate() {
		String publicKey = "";
		String privateKey = "";

		Map<String, String> env = System.getenv();

		Iterator it = env.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey().equals("s3public")) {
				publicKey = (String) pair.getValue();
				System.out.println(pair.getKey() + " = " + pair.getValue());
			} else if (pair.getKey().equals("s3private")) {
				privateKey = (String) pair.getValue();
				System.out.println(pair.getKey() + " = " + pair.getValue());
			}
			System.out.println(pair.getKey() + " = " + pair.getValue());
			// it.remove(); // avoids a ConcurrentModificationException
		}

		// First, we need to create a client connection to access Amazon S3 web
		// service. We’ll use AmazonS3 interface for this purpose:

		// https://console.aws.amazon.com/iam/home?region=us-east-1#/users/s3pythia?section=security_credentials
		AWSCredentials credentials = new BasicAWSCredentials(publicKey, privateKey);

		// And then configure the client:
		// http://docs.aws.amazon.com/general/latest/gr/rande.html
		// EU Ireland Eu_WEST_1

		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.EU_WEST_1).build();

		return s3client;
	}

	/**
	 * upload inputStream to S3 and return the url of the object
	 * 
	 * @return
	 */
	private String uploadObject(AmazonS3 s3client, File file, String key, String bucketName) {

		s3client.putObject(bucketName, key, file);
		URL url = s3client.getUrl(bucketName, key);
		return url.toString();
	}

}
