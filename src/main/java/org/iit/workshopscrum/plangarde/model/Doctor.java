package org.iit.workshopscrum.plangarde.model;

import java.util.List;

public class Doctor {

	private String name;

	private List<Holiday> holidays;

	public Doctor(String name, List<Holiday> holidays) {
		super();
		this.name = name;
		this.holidays = holidays;
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

	public List<Holiday> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}

}
