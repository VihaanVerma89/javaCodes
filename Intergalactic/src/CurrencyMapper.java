import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CurrencyMapper
{
	CurrencyMapper(RomanNumerals rNumeral, VulcanNumerals vNumeral)
	{
		currencyMap = new HashMap<String, Double>(); 
		this.rNumeral = rNumeral;
		this.vNumeral = vNumeral;
	}
	
	public void addCurrency(String name, double value)
	{
		currencyMap.put(name, value);
	}
	
	public double getUnitValue(String currencyName)
	{
		return currencyMap.get(currencyName);
	}
	
	public void processCreditInfo(String line)
	{
		String regex = Global.regexCreditsInfo;
		pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(line);
		m.matches();
		
		String [] vulcanNumbers = m.group(1).split("\\s");
		String currencyName = m.group(2);
		int creditValue = Integer.parseInt(m.group(3));
		
		// Check if vulcan numerals used are declared first.
		if(!vNumeral.areValid(vulcanNumbers))
		{
			Util.promptUser("Undeclared Vulcan number was used, input ignored.");
			return;
		}
		
		String romanNumerals = vNumeral.toRomanNumeral(vulcanNumbers);
		
		float divisor = rNumeral.evaluate(romanNumerals);
		
		double unitValue = creditValue / divisor;
		
		addCurrency(currencyName, unitValue);
	}
	
	private static Map<String , Double> currencyMap;
	private VulcanNumerals vNumeral;
	private RomanNumerals rNumeral;
	Pattern pattern;
}
