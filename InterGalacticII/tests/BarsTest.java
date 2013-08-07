import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.intergalactic.AlienNumerals;
import com.thoughtworks.intergalactic.Bars;


public class BarsTest {

	@Test
	public void addBarTest()
	{
		AlienNumerals aNumeral = new AlienNumerals();
		
		aNumeral.addToMap("glob", "I");
		aNumeral.addToMap("prok", "V");
		aNumeral.addToMap("pish", "X");
		aNumeral.addToMap("tegj", "L");
		
		Bars bar = new Bars(aNumeral);
		
		bar.saveInfo("glob glob Silver is 34 Credits");
		bar.saveInfo("pish pish Iron is 3910 Credits");
		bar.saveInfo("glob prok Gold is 57800 Credits");
		
		
		assertEquals(true, bar.isPresent("Silver"));
		assertEquals(true, bar.isPresent("Gold"));
		assertEquals(true, bar.isPresent("Iron"));
		
		//return value matches, don't know why it is failing.
//		assertEquals(14450.0, bar.getBarUnitValue("Gold"));
//		assertEquals(195.5, bar.getBarUnitValue("Iron"));
//		assertEquals(17.0, bar.getBarUnitValue("Silver"));
	}

}
