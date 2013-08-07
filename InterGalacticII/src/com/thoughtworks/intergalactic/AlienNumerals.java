package com.thoughtworks.intergalactic;

import java.util.HashMap;
import java.util.Map;

public class AlienNumerals extends RomanNumerals  
{
	private static AlienNumerals alienNumeral;
	
	public AlienNumerals()
	{
		alienNumerals = new HashMap<String, String>();
	}
	
//	public static AlienNumerals getInstance()
//	{
//		if(alienNumeral == null)
//		{
//			alienNumeral = new AlienNumerals();
//		}
//		
//		return alienNumeral;
//	}

	public void addToMap(String aNumeral, String rNumeral)
	{
		if(regexEngine.isWord(aNumeral) && !isPresent(aNumeral))
		{
			alienNumerals.put(aNumeral, rNumeral);
		}
	}
	
	public String toRomanNumeral(String [] aNumeral)
	{
		StringBuilder romanNumeralBldr = new StringBuilder(aNumeral.length);
		
		for (String s : aNumeral)
		{
			romanNumeralBldr.append(alienNumerals.get(s));
		}
		
		return romanNumeralBldr.toString();
	}
	
	
	private boolean isPresent(String aNumeral)
	{
		return alienNumerals.containsKey(aNumeral);
	}
	
	public boolean arePresent(String [] numeral)
	{
		for (String s : numeral)
		{
			if(!isPresent(s))
			{
				Util.promptUser(s +" is not a Alien Numeral or has not been added.");
				return false;
			}
		}
		return true;
	}
	
	private RegexEngine regexEngine = RegexEngine.getInstance();
	private Map<String, String> alienNumerals;
}
