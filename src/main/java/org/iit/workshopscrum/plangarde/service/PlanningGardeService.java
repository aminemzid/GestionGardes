package org.iit.workshopscrum.plangarde.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iit.workshopscrum.plangarde.model.Doctor;
import org.iit.workshopscrum.plangarde.model.ImpossibleToPlan;
import org.iit.workshopscrum.plangarde.model.PlanningGarde;

public class PlanningGardeService {
	
	
	public PlanningGarde generatePlanningGarde (List<Doctor> doctors, Date startDate, Date endDate)  throws ImpossibleToPlan{
		
		// Result to be returned
		Map<Date, Doctor> planning = new HashMap<Date, Doctor>();
		PlanningGarde planningGarde = new PlanningGarde(planning);
		
		if (doctors.isEmpty()) {
			throw new ImpossibleToPlan();
		}
		
		if (doctors.size() == 1) {
			planning.put(startDate, doctors.get(0));
		}
		
		return planningGarde;
	}

}
