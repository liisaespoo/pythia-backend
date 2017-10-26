package fi.espoo.pythia.backend.mappers;

import fi.espoo.pythia.backend.transfer.LatestPlansValue;
import fi.espoo.pythia.backend.transfer.PlanValue;

public class PlanValue2LPValueMapper {

	public static LatestPlansValue planValue2LPValue(PlanValue pv){
		
		LatestPlansValue lpv = new LatestPlansValue();
		pv.setCommentValues(lpv.getCommentValues());
		pv.setCreatedAt(pv.getCreatedAt());
		pv.setCreatedBy(lpv.getCreatedBy());
		pv.setDeleted(lpv.isDeleted());
		pv.setMainNo(lpv.getMainNo());
		pv.setMaintenanceDuty(lpv.isMaintenanceDuty());
		pv.setPdfUrl(lpv.getPdfUrl());
		pv.setPlanId(lpv.getPlanId());
		pv.setProjectId(lpv.getProjectId());
		pv.setStatus(lpv.getStatus());
		pv.setSubNo(lpv.getSubNo());
		pv.setUpdatedAt(lpv.getUpdatedAt());
		pv.setUpdatedBy(lpv.getUpdatedBy());
		pv.setVersion(lpv.getVersion());
		pv.setXmlUrl(lpv.getXmlUrl());
		
		return lpv;
	}
}
