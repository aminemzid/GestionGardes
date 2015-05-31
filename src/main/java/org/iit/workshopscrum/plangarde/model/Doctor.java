package org.iit.workshopscrum.plangarde.model;

import java.util.Set;

public class Doctor {

	private String name;

	private Set<Holiday> holidays;

	// TODO not used for the moment
	private Set<DayWeek> preferredGardeDays;

	public Doctor(String name, Set<Holiday> holidays) {
		super();
		this.name = name;
		this.holidays = holidays;
	}

	public Doctor(String name, Set<Holiday> holidays, Set<DayWeek> preferredGardeDays) {
		super();
		this.name = name;
		this.holidays = holidays;
		this.preferredGardeDays = preferredGardeDays;
	}

	public Doctor(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Holiday> getHolidays() {
		return holidays;
	}

	public void setHolidays(Set<Holiday> holidays) {
		this.holidays = holidays;
	}

	public Set<DayWeek> getPreferredGardeDays() {
		return preferredGardeDays;
	}

	public void setPreferredGardeDays(Set<DayWeek> preferredGardeDays) {
		this.preferredGardeDays = preferredGardeDays;
	}

}
