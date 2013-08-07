import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.thoughtworks.intergalactic.AlienNumerals;


public class AlienNumeralTest {

	@Test
	public void testMethods()
	{
		AlienNumerals aNumeral = new AlienNumerals();
		
		aNumeral.addToMap("glob", "I");
		aNumeral.addToMap("prok", "V");
		aNumeral.addToMap("pish", "X");
		aNumeral.addToMap("tegj", "L");
		
		assertEquals(true, aNumeral.arePresent(new String []{"glob","prok"}));
		assertEquals(false, aNumeral.arePresent(new String []{"glob","prok", "another"}));
		
		assertEquals("IV", aNumeral.toRomanNumeral(new String []{"glob","prok"}));
		assertEquals("VI", aNumeral.toRomanNumeral(new String []{"prok","glob"}));
		assertEquals("XLII", aNumeral.toRomanNumeral(new String []{"pish","tegj", "glob", "glob"}));
		}
	} 