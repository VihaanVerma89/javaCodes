package com.apartmentadda.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Parser 
{
	Parser()
	{
		society = new Society("Oberoi Woods");
	}
	
	public void readConfigFile(File file)
	{
		String line;
		try
		{
			inputScanner = new Scanner(file);
			while(inputScanner.hasNextLine())
			{
				line = inputScanner.nextLine();
				facility = lineToFacility(line);
				society.addFacility(facility);
			}
		}
		catch(FileNotFoundException fne)
		{
			fne.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			inputScanner.close();
		}
	}
	
	public void processBooking(File file)
	{
		String line;
		SocietyClub societyClub = society.getClub();
		try
		{
			inputScanner = new Scanner(file);
			while(inputScanner.hasNextLine())
			{
				line  = inputScanner.nextLine();
				String [] bookingInfo = line.split(",");
				
				bookingInfo = fixStrings(bookingInfo);
//				TimeSlot timeSlot = new TimeSlot(bookingInfo[0], bookingInfo[2], bookingInfo[3]);
				TimeSlot timeSlot = new TimeSlot( bookingInfo[2], bookingInfo[3]);
				
				societyClub.book(bookingInfo[0], bookingInfo[1], timeSlot);
			}
		}
		catch(FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}
	}
	
	private Facility lineToFacility(String line)
	{
		String [] facilityInfo = line.split(",");
		
		facilityInfo = fixStrings(facilityInfo);
		facility = new Facility(facilityInfo[0],facilityInfo[1],
				facilityInfo[2],facilityInfo[3]);
		
		return facility;
	}
	
	private String [] fixStrings(String [] stringArray)
	{
		for(int i =0 ; i < stringArray.length; i++)
		{
			stringArray[i] = stringArray[i].trim().toLowerCase();
		}
		return stringArray;
	}
	
	private Society society;
	private Facility facility;
	
	private Scanner inputScanner;
}
