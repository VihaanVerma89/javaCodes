package com.thoughtworks.intergalactic;

import java.util.Scanner;

public class Parser 
{

	public Parser()
	{
		inputScanner = new Scanner(System.in);
		aNumeral = new AlienNumerals();
		bar = new Bars(aNumeral);
		qHandler = new QuestionHandler(aNumeral, bar);
	}
	
	public void Parse()
	{
		while(inputScanner.hasNext())
		{
			processLine(inputScanner.nextLine());
		}
		
		inputScanner.close();
	}
	
	public void processLine(String line)
	{
		LineType lineType = regexEngine.getLineType(line);
		
		switch(lineType)
		{
		case ASSIGNMENT:
			// assignment processing.
			saveAssignment(line);
			break;

		case CREDITSINFO:
			// CreditsInfo processing.
			bar.saveInfo(line);
			break;

		case QUESTION_HOW_MANY:
			// How many types processing.
			answerQ(line, lineType);
			break;

		case QUESTION_HOW_MUCH:
			// How much types processing.
			answerQ(line, lineType);
			break; 
			
		case NONE :
			Util.noIdea();
			break;
			// I have no idea what you are talking about.
		}
	}
	
	private void answerQ(String line, LineType lineType)
	{
		String answer = qHandler.handleQuestion(line, lineType);
		if(answer != null)
		{
			Util.promptUser(answer);
		}
	}
	
	private void saveAssignment(String line)
	{
		String [] operands = getAssignmentOperands(line);
		aNumeral.addToMap(operands[0], operands[1]);
	}
	
	private String [] getAssignmentOperands(String line)
	{
		String [] parts = line.split(" ");
		return new String [] { parts[0], parts[2]};
	}
	
	private Scanner inputScanner;
	private AlienNumerals aNumeral;
	private Bars bar;
	private QuestionHandler qHandler;
	private RegexEngine regexEngine = RegexEngine.getInstance();
}
