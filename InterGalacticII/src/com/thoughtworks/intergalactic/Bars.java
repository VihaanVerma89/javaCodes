package com.thoughtworks.intergalactic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class Bars 
{
	
	public Bars(AlienNumerals aNumerals)
	{
		this.aNumerals = aNumerals;
		bar = new HashMap<String, Double>();
	}
	
	public void saveInfo(String line)
	{
		Matcher m = regexEngine.getCreditsMatcher(line);
		
		if(m.matches())
		{
			String [] alienNumerals = m.group(1).split("\\s");
			String name = m.group(2);
			int bulkValue = Integer.parseInt(m.group(3));
			
			if(!aNumerals.arePresent(alienNumerals))
			{
				Util.promptUser("Undeclared Alien Numeral was used, input ignored.");
				return;
			}
			
			//units is float to get barUnitValue in double. 
			float units = aNumerals.toNumber(aNumerals.toRomanNumeral(alienNumerals));
			
			float barUnitValue = bulkValue / units;
			addbar(name, barUnitValue);
		}
	}
	
	public double getBarUnitValue(String barName)
	{
//		return bar.get(barName);
		double value =  bar.get(barName);
		return bar.get(barName);
	}
	
	private void addbar(String barName, double barUnitValue)
	{
		bar.put(barName, barUnitValue);
	}
	
	public boolean isPresent(String name)
	{
		boolean result = bar.containsKey(name);
		
		if(!result)
		{
			Util.promptUser(name + " bar is not present.");
		}
		
		return result;
	}
		
	private Map<String, Double> bar;
	private AlienNumerals aNumerals;
	private RegexEngine regexEngine = RegexEngine.getInstance();

}
