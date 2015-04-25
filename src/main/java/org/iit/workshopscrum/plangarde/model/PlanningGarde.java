package org.iit.workshopscrum.plangarde.model;

import java.util.Date;
import java.util.Map;

public class PlanningGarde {

	private Map<Date, Doctor> planning;

	public PlanningGarde(Map<Date, Doctor> planning) {
		super();
		this.planning = planning;
	}

	public Map<Date, Doctor> getPlanning() {
		return planning;
	}

	public void setPlanning(Map<Date, Doctor> planning) {
		this.planning = planning;
	}
	
}
