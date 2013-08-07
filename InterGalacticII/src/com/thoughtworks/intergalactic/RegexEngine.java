package com.thoughtworks.intergalactic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEngine 
{
	
	private static RegexEngine regexEngine;
	private RegexEngine()
	{
		
	}
	
	public static RegexEngine getInstance()
	{
		 if(regexEngine == null)
		 {
			 regexEngine = new RegexEngine();
		 }
		 
		 return regexEngine;
	}
	
	public LineType getLineType(String line)
	{
		for(Line l : Line.values())
		{
			pattern = getPattern(l.getRegex());
			matcher = getMatcher(pattern, line);
			
			if(matcher.matches())
			{
				switch(l.getType())
				{
					case ASSIGNMENT:
						return LineType.ASSIGNMENT;
					
					case CREDITSINFO:
						return LineType.CREDITSINFO;
					
					case QUESTION_HOW_MANY:
						return LineType.QUESTION_HOW_MANY;
					
					case QUESTION_HOW_MUCH:
						return LineType.QUESTION_HOW_MUCH;
				}
			}
		}
		
		return LineType.NONE;
	}
	
	public Pattern getPattern(String line)
	{
		return Pattern.compile(line);
	}
	
	public Matcher getMatcher(Pattern pattern, String line)
	{
		return pattern.matcher(line);
	}
	
	public boolean violatesRomanRepetition(String romanNumeral)
	{
		String regex ="";
		String [] repetitionTest = {"(IIII+)","(XXXX+)", "(CCCC+)", "(MMMM+)", "(DD+)", "(LL+)", "(VV+)"}; 
		
		for(int i =0 ; i < repetitionTest.length; i ++)
		{
			regex = repetitionTest[i];
			Matcher m = getMatcher(getPattern(regex), romanNumeral);
			
			if(m.matches())
			{
				Util.promptUser("The number entered violates Roman Number repitition constraints.");
				return true;
			}
		}
		return false;
	}
	
	public boolean isWord(String numeral)
	{
		String wordRegex ="^[a-z]+";
		matcher = getMatcher(getPattern(wordRegex),(numeral.toLowerCase()));
		if(!matcher.matches())
		{
			Util.promptUser("Alien numeral must be a word.");
		}
		return (matcher.matches()? true: false);
	}
	
	public Matcher getHowMuchMatcher(String line)
	{
		String howMuchRegEx = "^how much is ((?:\\w+[^0-9] )+)\\?$";
		return getMatcher(getPattern(howMuchRegEx), line);
	}
	
	public Matcher getHowManyMatcher(String line)
	{
		String howManyRegex = "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
		return  getMatcher(getPattern(howManyRegex), line);
	}
	
	public Matcher getCreditsMatcher(String line)
	{
		String creditsInfoRegEx = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
		return  getMatcher(getPattern(creditsInfoRegEx), line);
	}
	
	
	private Matcher matcher;
	private Pattern pattern;
}
