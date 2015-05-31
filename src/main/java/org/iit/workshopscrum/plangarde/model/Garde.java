package org.iit.workshopscrum.plangarde.model;

import org.joda.time.LocalDate;

public class Garde {

	private LocalDate dateGarde;

	private Doctor doctorGarde;

	public Garde(LocalDate dateGarde, Doctor doctorGarde) {
		super();
		this.dateGarde = dateGarde;
		this.doctorGarde = doctorGarde;
	}

	public LocalDate getDateGarde() {
		return dateGarde;
	}

	public void setDateGarde(LocalDate dateGarde) {
		this.dateGarde = dateGarde;
	}

	public Doctor getDoctorGarde() {
		return doctorGarde;
	}

	public void setDoctorGarde(Doctor doctorGarde) {
		this.doctorGarde = doctorGarde;
	}

}
