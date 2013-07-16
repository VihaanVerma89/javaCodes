import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class QuestionHandler
{

	QuestionHandler(RomanNumerals rNumeral, VulcanNumerals vNumeral, NumeralMapper nMapper , CurrencyMapper cMapper )
	{
		this.rNumeral = rNumeral;
		this.vNumeral = vNumeral;
		this.nMapper = nMapper;
		this.cMapper = cMapper;
	}
	public String handle(String line, Global.LineType type)
	{
		String result="";
		if(type == Global.LineType.QUESTION_HOW_MANY)
		{
			result = handleHowMany(line);
		}
		else if(type == Global.LineType.QUESTION_HOW_MUCH)
		{
			result =handleHowMuch(line);
		}
		return result;
	}
	
	private String handleHowMuch(String line)
	{
//		String regex = "^how much is ((?:\\w+ )+)\\?$";
		String regex = Global.regexHowMuch;
		pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(line);

		//pish tegj glob glob
		m.matches();
		String  inputs =  m.group(1);
		
		if(vNumeral.areValid(inputs.split(" ")))
		{
			String romanNumeral = nMapper.convertToRomanNumeral(inputs.split(" "));
			//XLII
			int number = rNumeral.evaluate(romanNumeral);
			if(number != -1 )
			{
				return inputs + " is " + number +".";
			}
			else
			{
				return null;
			}
		}
		
		return null;
	}
	
	private String handleHowMany(String line)
	{
//		String regex = "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
		String regex = Global.regexHowMany;
		pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(line);
		m.matches();
		
		String creditName = m.group(1);
		String [] vulcanNumbers = m.group(2).split("\\s");
		String currencyName = m.group(3);
		
		
		if(!vNumeral.areValid(vulcanNumbers))
		{
			Util.promptUser("Undeclared Vulcan number was used, question ignored.");
			return null;
		}
		
		String romanNumerals = vNumeral.toRomanNumeral(vulcanNumbers);
		int materialQuantitiy  = rNumeral.evaluate(romanNumerals);
		
		if(materialQuantitiy != -1)
		{
			double totalCredits = materialQuantitiy * cMapper.getUnitValue(currencyName); 
			
			StringBuilder result = new StringBuilder();
			
			result.append(m.group(2)+  currencyName + " is " + totalCredits + " " + creditName);
			
			return result.toString();
		}
		return null;
	}
	
	private RomanNumerals rNumeral;
	private VulcanNumerals vNumeral;
	private NumeralMapper nMapper;
	private CurrencyMapper cMapper;
	
	Pattern pattern;
}
