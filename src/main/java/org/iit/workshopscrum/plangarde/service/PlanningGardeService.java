package org.iit.workshopscrum.plangarde.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.iit.workshopscrum.plangarde.model.Doctor;
import org.iit.workshopscrum.plangarde.model.Garde;
import org.iit.workshopscrum.plangarde.model.Holiday;
import org.iit.workshopscrum.plangarde.model.ImpossibleToPlan;
import org.iit.workshopscrum.plangarde.model.ImpossibleToPlanEnum;
import org.iit.workshopscrum.plangarde.model.PlanningGarde;
import org.joda.time.LocalDate;

public class PlanningGardeService {

	public PlanningGarde generatePlanningGarde(Set<Doctor> doctors, LocalDate startDate, LocalDate endDate) throws ImpossibleToPlan {

		if (startDate == null || endDate == null) {
			throw new IllegalArgumentException("Wrong arguments startDate/endDate");
		}

		if (doctors == null || doctors.isEmpty()) {
			throw new ImpossibleToPlan(ImpossibleToPlanEnum.NEED_AT_LEAST_ONE_DOCTOR);
		}

		// startDate must be strictly less than endDate
		if (startDate.isAfter(endDate)) {
			throw new IllegalArgumentException("Wrong arguments startDate/endDate");
		}

		// Result to be returned
		List<Garde> gardeList = new ArrayList<Garde>();

		LocalDate currentLocalDate = new LocalDate(startDate);
		while (currentLocalDate.isBefore(endDate)) {

			// Filters on available doctors (ie who are not on holiday in currentDate)
			Set<Doctor> availableDoctors = filterOnAvailableDoctors(doctors, currentLocalDate);

			if (availableDoctors.isEmpty()) {
				throw new ImpossibleToPlan(ImpossibleToPlanEnum.NO_DOCTOR_AVAILABLE, currentLocalDate.toString());
			}

			// Finds doctor having the minimum of gardes
			Doctor doctorWithMinGardes = findDoctorWithMinGarde(availableDoctors, gardeList);

			Garde garde = new Garde(currentLocalDate, doctorWithMinGardes);
			gardeList.add(garde);

			currentLocalDate = currentLocalDate.plusDays(1);
		}

		PlanningGarde planningGarde = new PlanningGarde();
		planningGarde.setGardeList(gardeList);
		return planningGarde;
	}

	private Set<Doctor> filterOnAvailableDoctors(Set<Doctor> doctors, LocalDate date) {
		Set<Doctor> availableDoctors = new HashSet<Doctor>();

		for (Doctor doctor : doctors) {
			if (doctor.getHolidays().isEmpty()) {
				availableDoctors.add(doctor);
			} else {
				for (Holiday holiday : doctor.getHolidays()) {
					if (date.isBefore(holiday.getStartDate()) || date.isAfter(holiday.getEndDate())) {
						availableDoctors.add(doctor);
					}
				}
			}
		}

		return availableDoctors;
	}

	private Doctor findDoctorWithMinGarde(Set<Doctor> doctors, List<Garde> gardeList) {

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
		Doctor doctorWithMinimumOfGardes = doctors.iterator().next();
		for (Doctor doctor : doctors) {
			Integer numberOfGarde = numberOfGardeForEachDoctor.get(doctor);
			if (numberOfGarde < numberOfGardeForEachDoctor.get(doctorWithMinimumOfGardes)) {
				doctorWithMinimumOfGardes = doctor;
			}
		}

		return doctorWithMinimumOfGardes;
	}

}
