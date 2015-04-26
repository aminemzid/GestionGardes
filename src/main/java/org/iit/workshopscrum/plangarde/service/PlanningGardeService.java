package org.iit.workshopscrum.plangarde.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.iit.workshopscrum.plangarde.model.Doctor;
import org.iit.workshopscrum.plangarde.model.ImpossibleToPlan;
import org.iit.workshopscrum.plangarde.model.PlanningGarde;

public class PlanningGardeService {

	public PlanningGarde generatePlanningGarde (List<Doctor> doctors, Date startDate, Date endDate)  throws ImpossibleToPlan{
		
		if (doctors == null || startDate == null || endDate == null) {
			throw new IllegalArgumentException("Paramètres incorrectes");
		}
		
		resetHourMinuteSecondeMilliseconde(startDate);
		resetHourMinuteSecondeMilliseconde(endDate);
		
		if (startDate.after(endDate)) {
			throw new IllegalArgumentException("Paramètres incorrectes");
		}
		
		if (doctors.isEmpty()) {
			throw new ImpossibleToPlan();
		}
		
		
		// Result to be returned
		Map<Date, Doctor> planning = new HashMap<Date, Doctor>();
		PlanningGarde planningGarde = new PlanningGarde(planning);
			
		
		
		return planningGarde;
	}

	private void resetHourMinuteSecondeMilliseconde(Date startDate) {
		DateUtils.setHours(startDate, 0);
		DateUtils.setMinutes(startDate, 0);
		DateUtils.setSeconds(startDate, 0);
		DateUtils.setMilliseconds(startDate, 0);
	}
}
