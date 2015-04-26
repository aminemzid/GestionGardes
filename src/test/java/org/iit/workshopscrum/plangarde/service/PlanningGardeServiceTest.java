package org.iit.workshopscrum.plangarde.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.iit.workshopscrum.plangarde.model.Doctor;
import org.iit.workshopscrum.plangarde.model.ImpossibleToPlan;
import org.iit.workshopscrum.plangarde.model.PlanningGarde;
import org.junit.Ignore;
import org.junit.Test;

public class PlanningGardeServiceTest {

	@Ignore
	@Test(expected = ImpossibleToPlan.class)
	public void test_generatePlanning_CaseZeroDoctorAndOneDayPeriod() throws ImpossibleToPlan {

		// Inpputs
		List<Doctor> doctors = new ArrayList<Doctor>();
		Date startDate = new Date();
		Date endDate = new Date();

		// Call method under test
		PlanningGarde planningGarde = new PlanningGardeService().generatePlanningGarde(doctors, startDate, endDate);

		// Assertion

	}

	@Ignore
	@Test
	public void test_generatePlanning_CaseOneDoctorAndOneDayPeriod() throws ImpossibleToPlan {

		// Inpputs
		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors.add(new Doctor("Mohamed", null));
		Date startDate = new Date();
		Date endDate = new Date();

		// Call method under test
		PlanningGarde planningGarde = new PlanningGardeService().generatePlanningGarde(doctors, startDate, endDate);

		// Assertion
		assertEquals(1, planningGarde.getPlanning().size());
		assertEquals("Mohamed", planningGarde.getPlanning().get(new Date()).getName());
	}

}
