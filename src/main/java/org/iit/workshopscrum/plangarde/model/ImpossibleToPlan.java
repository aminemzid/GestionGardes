package org.iit.workshopscrum.plangarde.model;

public class ImpossibleToPlan extends Exception {

	private static final long serialVersionUID = 1L;

	private ImpossibleToPlanEnum impossibleToPlanEnum;

	public ImpossibleToPlan(ImpossibleToPlanEnum impossibleToPlanEnum) {
		super();
		this.impossibleToPlanEnum = impossibleToPlanEnum;
	}

	public ImpossibleToPlanEnum getImpossibleToPlanEnum() {
		return impossibleToPlanEnum;
	}

	public void setImpossibleToPlanEnum(ImpossibleToPlanEnum impossibleToPlanEnum) {
		this.impossibleToPlanEnum = impossibleToPlanEnum;
	}

}
