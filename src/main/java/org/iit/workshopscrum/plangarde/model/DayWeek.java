package org.iit.workshopscrum.plangarde.model;

import java.util.Calendar;

public enum DayWeek {

	MONDAY(Calendar.MONDAY), TUESDAY(Calendar.TUESDAY), WEDNESDAY(Calendar.WEDNESDAY), THURSDAY(Calendar.THURSDAY), FRIDAY(Calendar.FRIDAY), SATURDAY(
			Calendar.SATURDAY), SUNDAY(Calendar.SUNDAY);

	private int day;

	DayWeek(int day) {
		this.day = day;
	}

	public int getDay() {
		return day;
	}

}
