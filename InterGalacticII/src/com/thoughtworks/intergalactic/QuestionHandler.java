package com.thoughtworks.intergalactic;

import java.util.regex.Matcher;

public class QuestionHandler 
{
	public QuestionHandler(AlienNumerals aNumeral, Bars bar)
	{
		this.aNumeral = aNumeral;
		this.bar = bar;
	}
	
	public String handleQuestion(String line, LineType lineType)
	{
		String answer = "";
		if(lineType == LineType.QUESTION_HOW_MANY)
		{
			answer = handleHowManyQ(line);
		}
		else if (lineType ==LineType.QUESTION_HOW_MUCH)
		{
			answer = handleHowMuchQ(line);
		}
		return answer;
	}
	
	private String handleHowMuchQ(String line)
	{
		String answer = null;
		Matcher m = regexEngine.getHowMuchMatcher(line);
		
		if(m.matches())
		{
			String inputNumerals = m.group(1);
			String [] alienNumerals = m.group(1).split(" ");
			if(aNumeral.arePresent(alienNumerals))
			{
				int number = aNumeral.toNumber(aNumeral.toRomanNumeral(alienNumerals));
				if(number != -1)
				{
					answer = inputNumerals + " is " + number + ".";
				}
			}
		}
		return answer;
	}
	
	private String handleHowManyQ(String line)
	{
		String answer = null;
		Matcher m = regexEngine.getHowManyMatcher(line);
		
		if(m.matches())
		{
			String creditName = m.group(1);
			String [] alienNumerals = m.group(2).split("\\s");
			String barName = m.group(3);
			
			if(aNumeral.arePresent(alienNumerals) && bar.isPresent(barName))
			{
				int barQuantity = aNumeral.toNumber(aNumeral.toRomanNumeral(alienNumerals));
				
				if( barQuantity != -1)
				{
					double totalCredits = barQuantity * bar.getBarUnitValue(barName);
					StringBuilder result = new StringBuilder();
					result.append(m.group(2)+  barName + " is " + totalCredits + " " + creditName);
					answer = result.toString();
				}
			}	
		}
		
		return answer;
	}
	
	private RegexEngine regexEngine = RegexEngine.getInstance();
	private AlienNumerals aNumeral;
	private Bars bar;
}
