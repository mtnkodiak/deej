package com.bakesale.deejmixer;

import java.util.Date;

/**
 * Represents a discrete event with start and (optionally) end times
 * @author hbl2686
 *
 */
public interface IParty {

	/**
	 * Returns the start time of this event.
	 * @return Date
	 */
	public Date getStartTime();
	
	/**
	 * Return the end time of this event
	 * @return Date
	 */
	public Date getEndTime();
	
}
