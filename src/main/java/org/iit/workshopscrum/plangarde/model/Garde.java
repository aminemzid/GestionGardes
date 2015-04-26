package org.iit.workshopscrum.plangarde.model;

import java.util.Date;

public class Garde {

	private Date dateGarde;

	private Doctor doctorGarde;

	public Garde(Date dateGarde, Doctor doctorGarde) {
		super();
		this.dateGarde = dateGarde;
		this.doctorGarde = doctorGarde;
	}

	public Date getDateGarde() {
		return dateGarde;
	}

	public void setDateGarde(Date dateGarde) {
		this.dateGarde = dateGarde;
	}

	public Doctor getDoctorGarde() {
		return doctorGarde;
	}

	public void setDoctorGarde(Doctor doctorGarde) {
		this.doctorGarde = doctorGarde;
	}

}
