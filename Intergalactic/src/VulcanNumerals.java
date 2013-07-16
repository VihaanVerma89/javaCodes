import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VulcanNumerals 
{
	public VulcanNumerals() 
	{
		vulcanNumerals = new ArrayList<>();
	}
	
	public void storeNumeralMapper(NumeralMapper nMapper)
	{
		this.nMapper = nMapper;
	}
	
	public String toRomanNumeral(String [] numeral)
	{
		 return nMapper.convertToRomanNumeral(numeral);
	}
	
	public void addNumeral(String numeral)
	{
		if(isWord(numeral))
		{
			vulcanNumerals.add(numeral);
		}
		else
		{
			Util.promptUser("Inncorrect Vulcan Numeral");
		}
	}
	
	private boolean isValid(String numeral)
	{
		for(String item : vulcanNumerals)
		{
			if ( numeral.equals(item))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean areValid(String [] numeral)
	{
		for (String s : numeral)
		{
			if(!isValid(s))
			{
				Util.promptUser(s +" is not a Vulcan Numeral.");
				return false;
			}
		}
		return true;
	}
	
	public boolean isWord(String numeral)
	{
		String wordRegex ="^[a-z]+";
		Matcher matcher;
		Pattern wordPattern = Pattern.compile(wordRegex);
		matcher = wordPattern.matcher(numeral);
		return (matcher.matches()? true: false);
	}
	
	private static List<String> vulcanNumerals;
	private NumeralMapper nMapper;
}
