package com.thoughtworks.intergalactic;


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
}
