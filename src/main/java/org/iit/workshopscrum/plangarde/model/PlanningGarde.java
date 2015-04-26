package org.iit.workshopscrum.plangarde.model;

import java.util.ArrayList;
import java.util.List;

public class PlanningGarde {

	private List<Garde> gardeList;

	public PlanningGarde(List<Garde> gardeList) {
		super();
		this.gardeList = gardeList;
	}

	public PlanningGarde() {
		super();
	}

	public List<Garde> getGardeList() {
		if (gardeList == null) {
			gardeList = new ArrayList<Garde>();
		}
		return gardeList;
	}

	public void setGardeList(List<Garde> gardeList) {
		this.gardeList = gardeList;
	}

	public void addGarde(Garde garde) {
		if (gardeList == null) {
			gardeList = new ArrayList<Garde>();
		}
		gardeList.add(garde);
	}
}
