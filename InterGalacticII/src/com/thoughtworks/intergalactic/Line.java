package com.thoughtworks.intergalactic;

public enum Line
{
	Assignment ("^([a-z]+) is ([I|V|X|L|C|D|M])$", LineType.ASSIGNMENT),
	CreditsInfo ("((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$", LineType.CREDITSINFO),
	HowMany("^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$", LineType.QUESTION_HOW_MANY),
	HowMuch("^how much is ((?:\\w+[^0-9] )+)\\?$", LineType.QUESTION_HOW_MUCH);
	
	Line(String regex, LineType lineType)
	{
		this.regex = regex;
		this.lineType = lineType;
	}
	
	public LineType getType()
	{
		return this.lineType;
	}
	
	public String getRegex()
	{
		return this.regex;
	}
	
	private String regex;
	private LineType lineType;
}