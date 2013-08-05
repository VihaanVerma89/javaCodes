package com.apartmentadda.console;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.FailedLoginException;

public class Society
{
	Society(String name)
	{
		club = new SocietyClub(this);
		this.name  = name;
		facilities = new HashMap<String, Facility>();
		facilityCharges = new HashMap<String, Integer>();
	}
	
	public void addFacility(Facility facility)
	{
		if(facilities.containsKey(facility.getName()))
		{
			Util.promptUser(facility.getName() + " is already present in " + this.getName());
		}
		else
		{
			facilities.put(facility.getName(), facility);
			facilityCharges.put(facility.getName(), facility.getChargesPerHour());
		}
	}
	
	public int getFacilityCharges(String facilityName)
	{
		if(facilityCharges.containsKey(facilityName))
		{
			return facilityCharges.get(facilityName);
		}
		
		return -1;
	}
	
	public Facility getFacility(String facilityName)
	{
		Facility facility = null;
		
		if(facilities.containsKey(facilityName))
		{
			return facilities.get(facilityName);
		}
		
		return facility;
	}
	
	public boolean containsFacility(String facilityName)
	{
		return facilities.containsKey(facilityName);
	}
	
	public String getName()
	{
		return name;
	}
	
	public SocietyClub getClub()
	{
		return club;
	}
	
	private String name;
	private SocietyClub club;
	private Map<String, Facility> facilities;
	private Map<String, Integer> facilityCharges;
}
