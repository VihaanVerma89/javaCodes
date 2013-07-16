import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Parser 
{
	public Parser()
	{
		inputScanner  = new Scanner(System.in);
		rNumeral = new RomanNumerals();
		vNumeral = new VulcanNumerals();
		nMapper =  new NumeralMapper(rNumeral, vNumeral);
		cMapper = new CurrencyMapper(rNumeral, vNumeral);
		qHandler = new QuestionHandler(rNumeral, vNumeral, nMapper, cMapper);
	}
	
	private String [] getAssignmentOperands(String line)
	{
		String [] parts = line.split(" ");
		return new String [] { parts[0], parts[2]};
	}
	
	public void parse()
	{
		try
		{
			while(inputScanner.hasNext())
			{
					process(inputScanner.nextLine());
			}
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
	
	public void process(String lineContents)
	{
		String answer = "";
		LineRegEx[] lineRegEx = LineRegEx.values();
		boolean matchFound = false;
//		lineContents = inputScanner.nextLine();

		if(lineContents.length() > 0)
		{
			for(LineRegEx l : lineRegEx)
			{
				matcher = l.getPattern().matcher(lineContents);

				if(matcher.matches())
				{
					switch(l.getType())
					{
					case ASSIGNMENT:
						// assignment processing.
						matchFound = true;
						String [] operands = getAssignmentOperands(lineContents);
						nMapper.addToMap(operands[0], operands[1]);
						break;

					case CREDITSINFO:
						// CreditsInfo processing.
						matchFound = true;
						cMapper.processCreditInfo(lineContents);
						break;

					case QUESTION_HOW_MANY:
						// How many types processing.
						matchFound = true;
						answer = qHandler.handle(lineContents, l.getType());
						if(answer != null)
						{
							Util.promptUser(answer);
						}
						break;

					case QUESTION_HOW_MUCH:
						// How much types processing.
						matchFound = true;
						answer = qHandler.handle(lineContents, l.getType());
						if(answer != null)
						{
							Util.promptUser(answer);
						}
						break;
					}
				}

				if(matchFound)
				{
					break;
				}
			}

			if(!matchFound)
			{
				Util.noIdea();
			}
		}
	}
	
	private enum LineRegEx
	{
		Assignment(Global.regexAssignment, Global.LineType.ASSIGNMENT),
		CreditsInfo(Global.regexCreditsInfo, Global.LineType.CREDITSINFO),
		Question_HOW_MANY(Global.regexHowMany, Global.LineType.QUESTION_HOW_MANY),
		Question_HOW_MUCH(Global.regexHowMuch, Global.LineType.QUESTION_HOW_MUCH);
		
		private final Pattern linePattern;
		private final Global.LineType type;
		LineRegEx(String linePattern, Global.LineType type)
		{
			this.linePattern=Pattern.compile(linePattern);
			this.type = type;
		}
		
		Pattern getPattern()
		{
			return linePattern;
		}
		
		Global.LineType getType()
		{
			return type;
		}
	}
	
	private String lineContents;
	private Matcher matcher;
	
	private QuestionHandler qHandler;
	private NumeralMapper nMapper;
	private RomanNumerals rNumeral;
	private VulcanNumerals vNumeral;
	private CurrencyMapper cMapper;
	private Scanner inputScanner;
		
}
