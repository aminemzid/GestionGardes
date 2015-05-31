package org.iit.workshopscrum.plangarde.model;

import org.joda.time.DateTimeConstants;

public enum DayWeek {

	MONDAY(DateTimeConstants.MONDAY), TUESDAY(DateTimeConstants.TUESDAY), WEDNESDAY(DateTimeConstants.WEDNESDAY), THURSDAY(DateTimeConstants.THURSDAY), FRIDAY(
			DateTimeConstants.FRIDAY), SATURDAY(DateTimeConstants.SATURDAY), SUNDAY(DateTimeConstants.SUNDAY);

	private int day;

	DayWeek(int day) {
		this.day = day;
	}

	public int getDay() {
		return day;
	}

}
