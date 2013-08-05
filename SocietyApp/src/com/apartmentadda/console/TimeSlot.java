package com.apartmentadda.console;

import java.util.Calendar;

public class TimeSlot 
{
//	TimeSlot(String date, String start, String end)
	TimeSlot( String start, String end)
	{
		this.start = Calendar.getInstance();
		this.end = Calendar.getInstance();
		String [] timeInfo = start.split(":");
		this.start.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeInfo[0]));
		this.start.set(Calendar.MINUTE, Integer.parseInt(timeInfo[1]));
		this.start.set(Calendar.SECOND, Integer.parseInt("00"));

		timeInfo = end.split(":");
		this.end.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeInfo[0]));
		this.end.set(Calendar.MINUTE, Integer.parseInt(timeInfo[1]));
		this.end.set(Calendar.SECOND, Integer.parseInt("00"));
	}
	
	public Calendar getStart()
	{
		return this.start;
	}
	
	public Calendar getEnd()
	{
		return this.end;
	}
	
	public boolean overlaps(TimeSlot timeSlot)
	{
		boolean overlaps = false;
		
		//helpful while debugging.
//		Util.printTimeSlot(timeSlot);
//		Util.printDate(this.start);
//		Util.printDate(this.end);
		
		if(timeSlot.start.before(this.start) && timeSlot.end.after(this.start))
		{
			return true;
		}
		
		if(timeSlot.start.before(this.end) && timeSlot.end.after(this.end))
		{
			return true;
		}
		
		return overlaps;
	}
	

	private Calendar start;
	private Calendar end;
}
