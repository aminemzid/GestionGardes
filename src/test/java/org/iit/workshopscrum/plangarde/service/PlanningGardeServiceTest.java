package org.iit.workshopscrum.plangarde.service;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.iit.workshopscrum.plangarde.model.Doctor;
import org.iit.workshopscrum.plangarde.model.Holiday;
import org.iit.workshopscrum.plangarde.model.ImpossibleToPlan;
import org.iit.workshopscrum.plangarde.model.PlanningGarde;
import org.iit.workshopscrum.plangarde.utils.GardeUtils;
import org.joda.time.LocalDate;
import org.junit.Test;

public class PlanningGardeServiceTest {

	private PlanningGardeService planningGardeService = new PlanningGardeService();

	@Test(expected = IllegalArgumentException.class)
	public void test_generatePlanning_CaseStartDateNull() throws ImpossibleToPlan {

		// Inputs
		Set<Doctor> doctors = new HashSet<Doctor>();
		LocalDate startDate = null;
		LocalDate endDate = new LocalDate();

		// Call method under test
		planningGardeService.generatePlanningGarde(doctors, startDate, endDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_generatePlanning_CaseEndDateNull() throws ImpossibleToPlan {

		// Inputs
		Set<Doctor> doctors = new HashSet<Doctor>();
		LocalDate startDate = new LocalDate();
		LocalDate endDate = null;

		// Call method under test
		planningGardeService.generatePlanningGarde(doctors, startDate, endDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_generatePlanning_CaseEndDateBeforeStartDate() throws ImpossibleToPlan {

		// Inputs
		Set<Doctor> doctors = new HashSet<Doctor>();
		doctors.add(new Doctor("Mohamed"));
		LocalDate startDate = new LocalDate();
		LocalDate endDate = startDate.minusDays(1);

		// Call method under test
		planningGardeService.generatePlanningGarde(doctors, startDate, endDate);
	}

	@Test(expected = ImpossibleToPlan.class)
	public void test_generatePlanning_CaseZeroDoctorAndOneDayPeriod() throws ImpossibleToPlan {

		// Inputs
		Set<Doctor> doctors = new HashSet<Doctor>();
		LocalDate startDate = new LocalDate();
		LocalDate endDate = new LocalDate();

		// Call method under test
		planningGardeService.generatePlanningGarde(doctors, startDate, endDate);
	}

	@Test
	public void test_generatePlanning_CaseOneDoctorAndOneDayPeriod() throws ImpossibleToPlan {

		// Inputs
		Set<Doctor> doctors = new HashSet<Doctor>();
		doctors.add(new Doctor("Mohamed"));
		LocalDate startDate = new LocalDate();
		LocalDate endDate = startDate.plusDays(1);

		// Call method under test
		PlanningGarde planningGarde = planningGardeService.generatePlanningGarde(doctors, startDate, endDate);

		// Display results
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
		Set<Doctor> doctors = new HashSet<Doctor>();
		doctors.add(new Doctor("Mohamed"));
		LocalDate startDate = new LocalDate();
		LocalDate endDate = startDate.plusDays(10);

		// Call method under test
		PlanningGarde planningGarde = planningGardeService.generatePlanningGarde(doctors, startDate, endDate);

		// Display results
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
		Set<Doctor> doctors = new HashSet<Doctor>();
		Doctor doctor1 = new Doctor("Mohamed");
		doctors.add(doctor1);
		Doctor doctor2 = new Doctor("Salah");
		doctors.add(doctor2);
		LocalDate startDate = new LocalDate();
		LocalDate endDate = startDate.plusDays(10);

		// Call method under test
		PlanningGarde planningGarde = planningGardeService.generatePlanningGarde(doctors, startDate, endDate);

		// Display results
		GardeUtils.displayPlanningGarde(planningGarde);
		Map<Doctor, Integer> numberOfGardeForEachDoctor = GardeUtils.calculateNumberOfGardeForEachDoctor(doctors, planningGarde.getGardeList());
		GardeUtils.displayNumberOfGardeForEachDoctor(numberOfGardeForEachDoctor);

		// Assertion
		assertEquals(10, planningGarde.getGardeList().size());
		assertEquals(numberOfGardeForEachDoctor.get(doctor1).intValue(), 5);
		assertEquals(numberOfGardeForEachDoctor.get(doctor2).intValue(), 5);
	}

	@Test(expected = ImpossibleToPlan.class)
	public void test_generatePlanning_CaseOneDoctorOnHolidayEveryDay() throws ImpossibleToPlan {

		// Inputs
		LocalDate startDate = new LocalDate();
		LocalDate endDate = startDate.plusDays(10);
		Set<Holiday> holidays = new HashSet<Holiday>();
		Holiday holiday = new Holiday(startDate, endDate);
		holidays.add(holiday);
		Set<Doctor> doctors = new HashSet<Doctor>();
		Doctor doctor1 = new Doctor("Mohamed", holidays);
		doctors.add(doctor1);

		// Call method under test
		planningGardeService.generatePlanningGarde(doctors, startDate, endDate);
	}
}
