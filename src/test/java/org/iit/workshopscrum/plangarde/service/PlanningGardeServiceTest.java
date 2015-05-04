package org.iit.workshopscrum.plangarde.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.iit.workshopscrum.plangarde.model.Doctor;
import org.iit.workshopscrum.plangarde.model.ImpossibleToPlan;
import org.iit.workshopscrum.plangarde.model.PlanningGarde;
import org.iit.workshopscrum.plangarde.utils.GardeUtils;
import org.junit.Test;

public class PlanningGardeServiceTest {

	private PlanningGardeService planningGardeService = new PlanningGardeService();

	@Test(expected = IllegalArgumentException.class)
	public void test_generatePlanning_CaseStartDateNull() throws ImpossibleToPlan {

		// Inputs
		List<Doctor> doctors = new ArrayList<Doctor>();
		Date startDate = null;
		Date endDate = new Date();

		// Call method under test
		planningGardeService.generatePlanningGarde(doctors, startDate, endDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_generatePlanning_CaseEndDateNull() throws ImpossibleToPlan {

		// Inputs
		List<Doctor> doctors = new ArrayList<Doctor>();
		Date startDate = new Date();
		Date endDate = null;

		// Call method under test
		planningGardeService.generatePlanningGarde(doctors, startDate, endDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_generatePlanning_CaseEndDateBeforeStartDate() throws ImpossibleToPlan {

		// Inputs
		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors.add(new Doctor("Mohamed"));
		Date startDate = new Date();
		Date endDate = DateUtils.addDays(startDate, -1);

		// Call method under test
		planningGardeService.generatePlanningGarde(doctors, startDate, endDate);
	}

	@Test(expected = ImpossibleToPlan.class)
	public void test_generatePlanning_CaseZeroDoctorAndOneDayPeriod() throws ImpossibleToPlan {

		// Inputs
		List<Doctor> doctors = new ArrayList<Doctor>();
		Date startDate = new Date();
		Date endDate = new Date();

		// Call method under test
		planningGardeService.generatePlanningGarde(doctors, startDate, endDate);
	}

	@Test
	public void test_generatePlanning_CaseOneDoctorAndOneDayPeriod() throws ImpossibleToPlan {

		// Inputs
		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors.add(new Doctor("Mohamed"));
		Date startDate = new Date();
		Date endDate = DateUtils.addDays(startDate, 1);

		// Call method under test
		PlanningGarde planningGarde = planningGardeService.generatePlanningGarde(doctors, startDate, endDate);

		GardeUtils.displayPlanningGarde(planningGarde);
		Map<Doctor, Integer> numberOfGardeForEachDoctor = GardeUtils.calculateNumberOfGardeForEachDoctor(doctors, planningGarde.getGardeList());
		GardeUtils.displayNumberOfGardeForEachDoctor(numberOfGardeForEachDoctor);

		// Assertion
		assertEquals(1, planningGarde.getGardeList().size());
		assertEquals("Mohamed", planningGarde.getGardeList().get(0).getDoctorGarde().getName());
	}

	@Test
	public void test_generatePlanning_CaseOneDoctorAndTenDaysPeriod() throws ImpossibleToPlan {

		// Inputs
		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors.add(new Doctor("Mohamed"));
		Date startDate = new Date();
		Date endDate = DateUtils.addDays(startDate, 10);

		// Call method under test
		PlanningGarde planningGarde = planningGardeService.generatePlanningGarde(doctors, startDate, endDate);

		GardeUtils.displayPlanningGarde(planningGarde);
		Map<Doctor, Integer> numberOfGardeForEachDoctor = GardeUtils.calculateNumberOfGardeForEachDoctor(doctors, planningGarde.getGardeList());
		GardeUtils.displayNumberOfGardeForEachDoctor(numberOfGardeForEachDoctor);

		// Assertion
		assertEquals(10, planningGarde.getGardeList().size());
		assertEquals("Mohamed", planningGarde.getGardeList().get(0).getDoctorGarde().getName());
		assertEquals("Mohamed", planningGarde.getGardeList().get(9).getDoctorGarde().getName());
	}

	@Test
	public void test_generatePlanning_CaseTwoDoctorsAndTenDaysPeriod() throws ImpossibleToPlan {

		// Inputs
		List<Doctor> doctors = new ArrayList<Doctor>();
		Doctor doctor1 = new Doctor("Mohamed");
		doctors.add(doctor1);
		Doctor doctor2 = new Doctor("Salah");
		doctors.add(doctor2);
		Date startDate = new Date();
		Date endDate = DateUtils.addDays(startDate, 10);

		// Call method under test
		PlanningGarde planningGarde = planningGardeService.generatePlanningGarde(doctors, startDate, endDate);

		GardeUtils.displayPlanningGarde(planningGarde);
		Map<Doctor, Integer> numberOfGardeForEachDoctor = GardeUtils.calculateNumberOfGardeForEachDoctor(doctors, planningGarde.getGardeList());
		GardeUtils.displayNumberOfGardeForEachDoctor(numberOfGardeForEachDoctor);

		// Assertion
		assertEquals(10, planningGarde.getGardeList().size());
		assertEquals(numberOfGardeForEachDoctor.get(doctor1).intValue(), 5);
		assertEquals(numberOfGardeForEachDoctor.get(doctor2).intValue(), 5);
	}

}
