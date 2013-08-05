package com.apartmentadda.console;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocietyClub 
{
	public SocietyClub(Society society)
	{
		this.society = society;

		facilityBookings = new HashMap<Facility, Map<Calendar, List<TimeSlot>>>() ;
	}
	
	public void book(String facilityName, String date, TimeSlot timeSlot)
	{
		Map<Calendar, List<TimeSlot>> bookingDates;
		
		if(society.containsFacility(facilityName))
		{
			Facility facility = society.getFacility(facilityName);
			
			if(facilityBookings.containsKey(facility))
			{
				//figure out if this booking can be added.
				bookingDates = facilityBookings.get(facility);
				
				Calendar calDate = Util.getDate(date);
				
				if(bookingDates.containsKey(calDate))
				{
					//booking are available for the current date.
					//figure out if requested time slots are available.
					if(timeSlotAvailable(bookingDates, calDate, timeSlot))
					{
						List<TimeSlot> timeSlots = bookingDates.get(calDate);
						timeSlots.add(timeSlot);
						addSuccess(timeSlot, facilityName);
					}
					else
					{
						// Time slots overlap.
						Util.promptUser("Already Booked - Booking not allowed.");
					}
				}
				else
				{
					//no booking for this dates are done, simply add it.
					List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
					timeSlots.add(timeSlot);
					bookingDates.put(calDate, timeSlots);
					addSuccess(timeSlot, facilityName);
				}
			}
			else
			{
				//this is the first booking for the facility.
				List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
				bookingDates = new HashMap<Calendar, List<TimeSlot>>();
					
				Calendar bookingDate = Util.getDate(date);
				timeSlots.add(timeSlot);
				
				bookingDates.put(bookingDate,timeSlots);
				facilityBookings.put(facility, bookingDates);
				addSuccess(timeSlot, facilityName);
			}
		}
		else
		{
			Util.promptUser(society.getName() + " does not have " + facilityName + " facility.");
		}
	}

	private void addSuccess(TimeSlot timeSlot, String facilityName)
	{
		Util.promptUser("Booked, Rs. " + Util.diffHours(timeSlot)*society.getFacilityCharges(facilityName));
		
	}
	public boolean timeSlotAvailable(Map<Calendar, List<TimeSlot>> bookingDates,Calendar calDate, TimeSlot timeSlot)
	{
		List<TimeSlot> timeSlots =  bookingDates.get(calDate);
		
		for(TimeSlot ts : timeSlots)
		{
//			if(timeSlot.overlaps(ts))
			if(ts.overlaps(timeSlot))
			{
				return false;
			}
		}
		
		return true;
	}
	
	private Society society;
//	private Map<Calendar, List<TimeSlot>> dateBookings;
	private Map<Facility, Map<Calendar, List<TimeSlot>>> facilityBookings;
}
