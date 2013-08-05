package com.apartmentadda.console;

import java.util.Calendar;

public class Facility 
{
	Facility(String name, String startTime, String endTime, String chargesPerH)
	{
		this.name=name;
		timeSlot = new TimeSlot(startTime, endTime);
		this.chargesPerHour=Integer.parseInt(chargesPerH);
	}
	
	public String getName()
	{
		return name;
	}
	
	public Calendar getStartTime()
	{
		return timeSlot.getStart();
	}
	
	public Calendar getEndTime()
	{
		return timeSlot.getEnd();
	}
	
	public int getChargesPerHour()
	{
		return chargesPerHour;
	}
	
	private String name;
	private TimeSlot timeSlot;
	private int chargesPerHour;
}
