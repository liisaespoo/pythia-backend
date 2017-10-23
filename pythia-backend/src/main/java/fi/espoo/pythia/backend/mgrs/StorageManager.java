package fi.espoo.pythia.backend.mgrs;

import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import fi.espoo.pythia.backend.converters.FileConverter;
import fi.espoo.pythia.backend.mappers.LPToLPValueMapper;
import fi.espoo.pythia.backend.mappers.PlanToPlanValueMapper;
import fi.espoo.pythia.backend.mappers.PlanValueToPlanMapper;
import fi.espoo.pythia.backend.mappers.PrjToPrjVal2Mapper;
import fi.espoo.pythia.backend.mappers.PrjUpToPrjUpValMapper;
import fi.espoo.pythia.backend.mappers.PrjUpValToPrjUpMapper;
import fi.espoo.pythia.backend.mappers.PtextToPtextValueMapper;
import fi.espoo.pythia.backend.mappers.PtextValueToPtextMapper;
import fi.espoo.pythia.backend.repos.LatestPlansRepository;
import fi.espoo.pythia.backend.repos.PlanRepository;
import fi.espoo.pythia.backend.repos.ProjectRepository;
import fi.espoo.pythia.backend.repos.ProjectUpdateRepository;
import fi.espoo.pythia.backend.repos.PtextRepository;
import fi.espoo.pythia.backend.repos.SisterProjectRepository;
import fi.espoo.pythia.backend.repos.SisterProjectUpdateRepository;
import fi.espoo.pythia.backend.repos.entities.LatestPlans;
import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.repos.entities.Project;
import fi.espoo.pythia.backend.repos.entities.ProjectUpdate;
import fi.espoo.pythia.backend.repos.entities.Ptext;
import fi.espoo.pythia.backend.repos.entities.SisterProject;
import fi.espoo.pythia.backend.repos.entities.SisterProjectUpdate;
import fi.espoo.pythia.backend.repos.entities.Status;
import fi.espoo.pythia.backend.transfer.LatestPlansValue;
import fi.espoo.pythia.backend.transfer.PlanValue;
import fi.espoo.pythia.backend.transfer.ProjectUpdateValue;
import fi.espoo.pythia.backend.transfer.ProjectValue2;
import fi.espoo.pythia.backend.transfer.PtextValue;
import fi.espoo.pythia.backend.validators.PlanValidator;

@Component
@Transactional
public class StorageManager {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private SisterProjectRepository sisterProjectRepository;

	@Autowired
	private SisterProjectUpdateRepository sisterProjectUpdateRepository;

	@Autowired
	private PtextRepository ptextRepository;

	@Autowired
	private ProjectUpdateRepository projectUpdateRepository;

	@Autowired
	private LatestPlansRepository latestPlansRepository;
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
			ProjectValue2 pval = PrjToPrjVal2Mapper.ProjectToProjectValue2(p);
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
		ProjectValue2 pval = PrjToPrjVal2Mapper.ProjectToProjectValue2(project);
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
				ProjectValue2 pval = PrjToPrjVal2Mapper.ProjectToProjectValue2(p);
				return pval;
			}
		}

		return null;
	}

	/**
	 * get all plans by projectId
	 * 
	 * @param projectId
	 * @return
	 */
	public List<PlanValue> getPlans(Long projectId) {

		ProjectUpdate projectUpdate = projectUpdateRepository.findByProjectId(projectId);
		ProjectUpdateValue pval = PrjUpToPrjUpValMapper.ProjectUpdateToProjectUpdateValue(projectUpdate);

		return pval.getPlans();

	}

	public PlanValue getPlan(Long planId) {

		Plan plan = planRepository.findByPlanId(planId);
		ProjectUpdate project = plan.getProject();
		PlanValue pVal = PlanToPlanValueMapper.planToPlanValue(plan, project);

		return pVal;
	}

	public PtextValue getComment(long id) {
		Ptext comment = ptextRepository.findByTextId(id);
		Plan plan = comment.getPlan();
		PtextValue cVal = PtextToPtextValueMapper.ptextToPtextValue(comment, plan);

		return cVal;
	}

	/**
	 * get comments by planId
	 * 
	 * @param planId
	 * @return
	 */
	public List<PtextValue> getComments(Long planId) {

		Plan plan = planRepository.findByPlanId(planId);
		List<Ptext> comments = ptextRepository.findByPlan(plan);

		List<PtextValue> commentValues = new ArrayList<PtextValue>();
		for (Ptext c : comments) {
			PtextValue cv = PtextToPtextValueMapper.ptextToPtextValue(c, plan);
			commentValues.add(cv);
		}

		return commentValues;

	}



	// ---------------------POST-----------------------------------

	public ProjectUpdate createProject(ProjectUpdateValue projectV) {

		ProjectUpdate projectUpTemp = projectUpdateRepository.findByProjectId(projectV.getProjectId());
		ProjectUpdate projectUp = PrjUpValToPrjUpMapper.projectValue2ToProject(projectV, projectUpTemp, false);
		ProjectUpdate updatedProject = projectUpdateRepository.save(projectUp);
		updateSisterProjects(projectV, projectUp);

		return updatedProject;
	}

	/**
	 * Checks if 1st version and if approved
	 * 
	 * If the 1st then version = 0 and approved = true
	 * 
	 * If not the 1st then increase version number by one
	 * 
	 * @return PlanValue
	 */
//	public PlanValue createPlan2(PlanValue planV) {
//
//		Long projectId = planV.getProjectId();
//		// get project by projectid
//		ProjectUpdate projectUpdate = projectUpdateRepository.findByProjectId(projectId);
//		// map planV to plan
//		Plan mappedPlan = PlanValueToPlanMapper.planValueToPlan(planV, projectUpdate, false);
//
//		// get all plans with planV.projectId and planV.mainNo & planV.subNo
//
//		List<Plan> existingPlans = planRepository.findByProjectInAndMainNoInAndSubNoIn(projectUpdate, planV.getMainNo(),
//				planV.getSubNo());
//
//		short version = 0;
//		boolean approved = true;
//		// first version
//		if (existingPlans.isEmpty()) {
//			version = 0;
//			approved = true;
//
//		} else {
//			// sorting from min to max
//			Collections.sort(existingPlans);
//			// get existingPlans max version with projctId, mainno and subno
//			Plan max = existingPlans.get(existingPlans.size() - 1);
//			// max version
//			short maxVersion = max.getVersion();
//			version = (short) (maxVersion + 1);
//			approved = false;
//
//		}
//		mappedPlan.setVersion(version);
//		// mappedPlan.setApproved(approved);
//
//		Plan savedPlan = planRepository.save(mappedPlan);
//
//		// send mail TODO
//		PlanValue savedPlanValue = PlanToPlanValueMapper.planToPlanValue(savedPlan, projectUpdate);
//		// finally
//		return savedPlanValue;
//	}

	/**
	 * Checks if 1st version and if approved
	 * 
	 * If the 1st then version = 0 and approved = true
	 * 
	 * If not the 1st then increase version number by one
	 * 
	 * must be PDF
	 * 
	 * @param mfile
	 * @param projectId
	 * @return
	 */
	public PlanValue createPlan(MultipartFile mfile, long projectId) {

		// create plan
		File file;
		PlanValue p = new PlanValue();

		try {
			file = FileConverter.multipartFileToFile(mfile);
			String name = file.getName();
			if (name.endsWith(".pdf")) {
				PlanValidator validator = new PlanValidator();
				// create plan

				try {
					file = FileConverter.multipartFileToFile(mfile);
					short mainNo = validator.extractMainNoFromFileName(name);
					short subNo = validator.extractSubNoFromFileName(name);
					short version = 0;
					// String fromCharCode(int... codePoints) {
					Status status = Status.APPROVED;
					UserPrincipal owner = java.nio.file.Files.getOwner(file.toPath());
					String sowner = owner.toString();
					ProjectUpdate projectUpdate = projectUpdateRepository.findByProjectId(projectId);

					List<Plan> existingPlans = planRepository.findByProjectInAndMainNoInAndSubNoIn(projectUpdate,
							mainNo, subNo);

					if (existingPlans.isEmpty()) {
						version = 0;
						status = Status.APPROVED;

					} else {
						// sorting from min to max
						Collections.sort(existingPlans);
						// get existingPlans max version with projctId, mainno
						// and subno
						Plan max = existingPlans.get(existingPlans.size() - 1);
						// max version
						short maxVersion = max.getVersion();
						version = (short) (maxVersion + 1);
						status = Status.WAITING_FOR_APPROVAL;

					}

					Plan plan = new Plan(projectUpdate, new ArrayList<Ptext>(), mainNo, subNo, version, null, status,
							OffsetDateTime.now(), sowner, null, null, false);

					Plan savedPlan = planRepository.save(plan);
					PlanValue savedPlanValue = PlanToPlanValueMapper.planToPlanValue(savedPlan, projectUpdate);

					return savedPlanValue;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return null;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;

	}

	/**
	 * 
	 * @param pTextVal
	 * @param id
	 * @return
	 */
	public PtextValue createPtext(PtextValue pTextVal, Long id) {

		// Long planId = commV.getPlanId();
		Plan plan = planRepository.findByPlanId(id);
		pTextVal.setPlanId(id);
		Ptext pText = PtextValueToPtextMapper.commentValueToComment(pTextVal, plan, false, false);
		Ptext savedPtext = ptextRepository.save(pText);
		PtextValue savedCommValue = PtextToPtextValueMapper.ptextToPtextValue(savedPtext, plan);
		return savedCommValue;

	}

	// ---------------------PUT------------------------------------

	// /**
	// *
	// * @param projectV
	// * @return
	// */
	// public ProjectValue updateProject(ProjectValue projectV) {
	//
	// Project projectTemp =
	// projectRepository.findByProjectId(projectV.getProjectId());
	// Project project =
	// ProjectValueToProjectMapper.projectValueToProjectUpdate(projectV,
	// projectTemp);
	// Project updatedProject = projectRepository.save(project);
	//
	// ProjectValue updatedProjectValue =
	// ProjectToProjectValueMapper.projectToProjectValue(updatedProject);
	// return updatedProjectValue;
	//
	// }

	public void updateProject(ProjectUpdateValue projectV) {

		ProjectUpdate projectUpTemp = projectUpdateRepository.findByProjectId(projectV.getProjectId());
		ProjectUpdate projectUp = PrjUpValToPrjUpMapper.projectValue2ToProject(projectV, projectUpTemp, true);
		// update basic project table
		projectUpdateRepository.save(projectUp);

		// update sisterProjects table
		updateSisterProjects(projectV, projectUp);

		// ProjectValue2 updatedProjectValue2 = PrjToPrjVal2
		// .ProjectToProjectValue2(projectRepository.findByProjectId(projectV.getProjectId()));
		// return updatedProjectValue2;
	}

	public void updateSisterProjects(ProjectUpdateValue pv, ProjectUpdate projectUp) {

		// get latest by repo id
		ProjectUpdate p = projectUpdateRepository.findByProjectId(pv.getProjectId());
		// create empty sisterprojects list
		List<SisterProject> sProjects = new ArrayList<SisterProject>();

		// first delete all rows with this project
		sisterProjectUpdateRepository.deleteByProject(projectUp);
		Long j = 1L;
		// add sisterprojects to the db
		for (Long id : pv.getSisterProjects()) {
			System.out.println("updatesisterProjectId:" + id);
			SisterProjectUpdate sProjectUp = (new SisterProjectUpdate(j, projectUp, id));

			sisterProjectUpdateRepository.save(sProjectUp);
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
		// boolean approved = planV.isApproved();
		ProjectUpdate projectUp = projectUpdateRepository.findByProjectId(id);

		Plan plan = PlanValueToPlanMapper.planValueToPlan(planV, projectUp, true);

		Plan updatedPlan = planRepository.save(plan);
		PlanValue returnPlan = PlanToPlanValueMapper.planToPlanValue(updatedPlan, projectUp);
		return returnPlan;

	}

	public PtextValue updatePtext(PtextValue pTextVal, long id) {

		// TODO
		// VERIFY THAT path variable commentId long id and
		// pTextVal.pTextVal.getTextId() match

		Plan plan = planRepository.findByPlanId(pTextVal.getPlanId());
		Ptext pText = PtextValueToPtextMapper.commentValueToComment(pTextVal, plan, pTextVal.isApproved(), true);

		pText.setTextId(id);

		Ptext updatedPtext = ptextRepository.save(pText);

		PtextValue updatedPtextValue = PtextToPtextValueMapper.ptextToPtextValue(updatedPtext, plan);
		return updatedPtextValue;

	}

	// --------------------- DELETE --------------------------------

	/**
	 * update plan value deleted to true
	 * 
	 * @param id
	 */
	public LatestPlansValue deletePlan(long id) {
		// TODO Auto-generated method stub
		Plan plan = planRepository.findByPlanId(id);
		plan.setDeleted(true);

		Plan updatedPlan = planRepository.save(plan);

		LatestPlans latestPlans = latestPlansRepository.findByPlanId(updatedPlan.getPlanId());
		Project project = projectRepository.findByProjectId(id);

		LatestPlansValue updatedLAtestPlanValue = LPToLPValueMapper.lpTolpValue(latestPlans, project);
		return updatedLAtestPlanValue;

	}

}
