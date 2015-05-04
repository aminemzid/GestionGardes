package org.iit.workshopscrum.plangarde.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.iit.workshopscrum.plangarde.model.Doctor;
import org.iit.workshopscrum.plangarde.model.Garde;
import org.iit.workshopscrum.plangarde.model.ImpossibleToPlan;
import org.iit.workshopscrum.plangarde.model.ImpossibleToPlanEnum;
import org.iit.workshopscrum.plangarde.model.PlanningGarde;

public class PlanningGardeService {

	public PlanningGarde generatePlanningGarde(List<Doctor> doctors, Date startDate, Date endDate) throws ImpossibleToPlan {

		if (startDate == null || endDate == null) {
			throw new IllegalArgumentException("Wrong arguments startDate/endDate");
		}

		if (doctors == null || doctors.isEmpty()) {
			throw new ImpossibleToPlan(ImpossibleToPlanEnum.NEED_AT_LEAST_ONE_DOCTOR);
		}

		resetHourMinuteSecondeMilliseconde(startDate);
		resetHourMinuteSecondeMilliseconde(endDate);

		// startDate must be strictly less than endDate
		if (startDate.after(endDate)) {
			throw new IllegalArgumentException("Wrong arguments startDate/endDate");
		}

		// Result to be returned
		List<Garde> gardeList = new ArrayList<Garde>();

		Date currentDate = (Date) startDate.clone();
		while (currentDate.before(endDate)) {

			// Finds doctor having the minimum of gardes
			Doctor doctorWithMinGardes = findDoctorWithMinGarde(doctors, gardeList);

			Garde garde = new Garde(currentDate, doctorWithMinGardes);
			gardeList.add(garde);

			currentDate = DateUtils.addDays(currentDate, 1);
		}

		PlanningGarde planningGarde = new PlanningGarde();
		planningGarde.setGardeList(gardeList);
		return planningGarde;
	}

	private Doctor findDoctorWithMinGarde(List<Doctor> doctors, List<Garde> gardeList) {

		// 1. Calculate number of gardes for each doctor
		Map<Doctor, Integer> numberOfGardeForEachDoctor = new HashMap<Doctor, Integer>();

		for (Doctor doctor : doctors) {
			numberOfGardeForEachDoctor.put(doctor, new Integer(0));
		}

		for (Garde garde : gardeList) {
			Integer numberOfGarde = numberOfGardeForEachDoctor.get(garde.getDoctorGarde());
			numberOfGardeForEachDoctor.put(garde.getDoctorGarde(), ++numberOfGarde);
		}

		// 2. Find the doctor with minimum of garde
		Doctor doctorWithMinimumOfGardes = doctors.get(0);
		for (Doctor doctor : doctors) {
			Integer numberOfGarde = numberOfGardeForEachDoctor.get(doctor);
			if (numberOfGarde < numberOfGardeForEachDoctor.get(doctorWithMinimumOfGardes)) {
				doctorWithMinimumOfGardes = doctor;
			}
		}

		return doctorWithMinimumOfGardes;
	}

	private void resetHourMinuteSecondeMilliseconde(Date date) {
		DateUtils.round(date, Calendar.HOUR);
		DateUtils.round(date, Calendar.MINUTE);
		DateUtils.round(date, Calendar.SECOND);
		DateUtils.round(date, Calendar.MILLISECOND);
	}
}
