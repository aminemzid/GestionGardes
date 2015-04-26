package org.iit.workshopscrum.plangarde.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.iit.workshopscrum.plangarde.model.Doctor;
import org.iit.workshopscrum.plangarde.model.Garde;
import org.iit.workshopscrum.plangarde.model.PlanningGarde;

public class GardeUtils {

	public static Map<Doctor, Integer> calculateNumberOfGardeForEachDoctor(List<Doctor> doctors, List<Garde> gardeList) {

		Map<Doctor, Integer> numberOfGardeForEachDoctor = new HashMap<Doctor, Integer>();

		for (Doctor doctor : doctors) {
			numberOfGardeForEachDoctor.put(doctor, new Integer(0));
		}

		for (Garde garde : gardeList) {
			Integer numberOfGarde = numberOfGardeForEachDoctor.get(garde.getDoctorGarde());
			numberOfGardeForEachDoctor.put(garde.getDoctorGarde(), ++numberOfGarde);
		}

		return numberOfGardeForEachDoctor;
	}

	public static void displayNumberOfGardeForEachDoctor(Map<Doctor, Integer> numberOfGardeForEachDoctor) {
		System.out.println("######################### START DISPLAYING NUMBER OF GARDE FOR EACH DOCTOR #########################");
		int doctorNumber = 1;
		for (Doctor doctor : numberOfGardeForEachDoctor.keySet()) {
			System.out.println("Doctor " + doctorNumber + " : DOCTOR = " + doctor.getName() + " / NUMBER OF GARDE = " + numberOfGardeForEachDoctor.get(doctor));
			doctorNumber++;
		}
		System.out.println("######################### END DISPLAYING NUMBER OF GARDE FOR EACH DOCTOR #########################");
	}

	public static void displayPlanningGarde(PlanningGarde planningGarde) {
		System.out.println("######################### START DISPLAYING PLANNING GARDE #########################");
		int gardeNumber = 1;
		for (Garde garde : planningGarde.getGardeList()) {
			System.out.println("Garde " + gardeNumber + " : DATE = " + DateFormatUtils.format(garde.getDateGarde(), "E dd/MM/yyyy") + " / DOCTOR = "
					+ garde.getDoctorGarde().getName());
			gardeNumber++;
		}
		System.out.println("######################### END DISPLAYING PLANNING GARDE #########################");
	}

}
