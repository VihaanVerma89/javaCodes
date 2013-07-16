import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class NumeralMapper 
{
	NumeralMapper(RomanNumerals rNumeral, VulcanNumerals vNumeral)
	{
		mappings = new HashMap<String, String>();
		this.rNumeralObj = rNumeral;
		this.vNumeralObj = vNumeral;
		vNumeralObj.storeNumeralMapper(this);
	}
	
	public String convertToRomanNumeral(String[] vNumeral)
	{
		// pish tegj glob globkey
		StringBuilder romanNumeral = new StringBuilder(vNumeral.length);
		
		for(String s : vNumeral)
		{
			romanNumeral.append(mappings.get(s));
		}
		
		return romanNumeral.toString();
	}
	
	public String convertToVulcanNumeral(String rNumeral)
	{
		return new String();
	}

	public void addToMap(String vNumeral, String rNumeral)
	{
		if( mappings.containsKey(vNumeral) || getKeyByValue(rNumeral) !=null)
		{
			Util.promptUser("Mapping is already present.");
		}
		else
		{
//			if(rNumeralObj.isRomanNumeral(rNumeral)&& vNumeralObj.isWord(vNumeral))
			if(vNumeralObj.isWord(vNumeral))
			{
				//add to list of know vulcan numerals
				vNumeralObj.addNumeral(vNumeral);
				mappings.put(vNumeral, rNumeral);
			}
			else
			{
				Util.promptUser("Incorrect assignment.");
			}
		}
	}
	
	private String getKeyByValue(String value) {
	    for (Entry<String, String> entry : mappings.entrySet()) 
	    {
	        if (value.equals((String)(entry.getValue())))
	        {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	private RomanNumerals rNumeralObj;
	private VulcanNumerals vNumeralObj;
	
	private static Map<String, String> mappings;
}
