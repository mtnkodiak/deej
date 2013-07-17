package com.bakesale.deejmixer;

import java.util.Date;

public class Party implements IParty {

	private Date startTime;
	private Date endTime;
	
	public Party(Date startTimeIn, Date endTimeIn) {
		startTime=startTimeIn;
		endTime=endTimeIn;
	}
	
	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public Date getEndTime() {
		return endTime;
	}

}
