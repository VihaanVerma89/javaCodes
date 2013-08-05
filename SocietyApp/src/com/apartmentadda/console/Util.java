package com.apartmentadda.console;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Util 
{
	private static void sopln(String text)
	{
			System.out.println("\n"+text+"\n");
	}
	
	public static void promptUser(String text)
	{
		sopln(text);
	}
	
	public static void noIdea()
	{
		sopln("I have no idea what you are talking about.");
	}
	
	
	public static Calendar getDate(String date)
	{
		Calendar cal = null;
		int [] dateParts = stringArry2IntArry(date.split("-"));
		if(dateParts.length == 3)
		{
			cal = new GregorianCalendar(dateParts[0],dateParts[1],dateParts[2]);
		}
		
		return cal;
	}
	
	private static int [] stringArry2IntArry(String [] stringArry) 
	{
		int [] intArry = new int[stringArry.length];
		
		for(int i=0 ; i < stringArry.length; i++)
		{
			try
			{
				intArry[i] = Integer.parseInt(stringArry[i]);
			}
			catch(NumberFormatException nfe)
			{
				nfe.printStackTrace();
			}
		}
		
		return intArry;
	}
	
//	public static int diffHours(Calendar start, Calendar end)
	public static int diffHours(TimeSlot timeSlot)
	{
		Date startDate = timeSlot.getStart().getTime();
		Date endDate = timeSlot.getEnd().getTime();
		
		long secs = (endDate.getTime() - startDate.getTime())/1000;
		int hours = (int) secs / 3600;
		secs = secs % 3600;
		int mins = (int) secs / 60;
		return (hours+mins);
	}
	public static void printTimeSlot(TimeSlot timeSlot)
	{
		Calendar cal = timeSlot.getStart();
		Util.promptUser("Start Time");
		Util.promptUser("Year: " + String.valueOf(cal.get(Calendar.YEAR)));
		Util.promptUser("Month: " +String.valueOf(cal.get(Calendar.MONTH)));
		Util.promptUser("Day of Month: " + String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		Util.promptUser("Hour: "+String.valueOf(cal.get(Calendar.HOUR)));
		Util.promptUser("Minute: "+String.valueOf(cal.get(Calendar.MINUTE)));
		
		cal = timeSlot.getEnd();
		Util.promptUser("End Time");
		Util.promptUser("Year: " + String.valueOf(cal.get(Calendar.YEAR)));
		Util.promptUser("Month: " +String.valueOf(cal.get(Calendar.MONTH)));
		Util.promptUser("Day of Month: " + String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		Util.promptUser("Hour: "+String.valueOf(cal.get(Calendar.HOUR)));
		Util.promptUser("Minute: "+String.valueOf(cal.get(Calendar.MINUTE)));
	}
	
	public static void printDate(Calendar cal)
	{
		Util.promptUser("Year :"+ cal.get(Calendar.YEAR));
		Util.promptUser("Month :"+ cal.get(Calendar.MONTH));
		Util.promptUser("Day of Month :"+ cal.get(Calendar.DAY_OF_MONTH));
		Util.promptUser("Hour: "+String.valueOf(cal.get(Calendar.HOUR)));
		Util.promptUser("Minute: "+String.valueOf(cal.get(Calendar.MINUTE)));
	}
	
}
