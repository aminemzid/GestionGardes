package org.iit.workshopscrum.plangarde.model;

import org.apache.commons.lang.StringUtils;

public class ImpossibleToPlan extends Exception {

	private static final long serialVersionUID = 1L;

	private ImpossibleToPlanEnum impossibleToPlanEnum;

	private String message;

	public ImpossibleToPlan(ImpossibleToPlanEnum impossibleToPlanEnum) {
		super();
		this.impossibleToPlanEnum = impossibleToPlanEnum;
	}

	public ImpossibleToPlan(ImpossibleToPlanEnum impossibleToPlanEnum, String message) {
		super();
		this.impossibleToPlanEnum = impossibleToPlanEnum;
		this.message = message;
	}

	public ImpossibleToPlanEnum getImpossibleToPlanEnum() {
		return impossibleToPlanEnum;
	}

	public void setImpossibleToPlanEnum(ImpossibleToPlanEnum impossibleToPlanEnum) {
		this.impossibleToPlanEnum = impossibleToPlanEnum;
	}

	@Override
	public String toString() {
		if (StringUtils.isEmpty(message)) {
			return impossibleToPlanEnum.toString();
		} else {
			return impossibleToPlanEnum.toString() + " : " + message;
		}
	}

}
