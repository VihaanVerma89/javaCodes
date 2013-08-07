import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.thoughtworks.intergalactic.LineType;
import com.thoughtworks.intergalactic.RegexEngine;


public class RegexEngineTest {

	@Test
	public void getLineTypeTest()
	{
		RegexEngine regexEngine = RegexEngine.getInstance();
		
		assertEquals(LineType.ASSIGNMENT,regexEngine.getLineType("glob is I"));
		assertEquals(LineType.CREDITSINFO,regexEngine.getLineType("glob glob Silver is 34 Credits" ));
		assertEquals(LineType.QUESTION_HOW_MANY,regexEngine.getLineType("how many Credits is glob prok Gold ?"));
		assertEquals(LineType.QUESTION_HOW_MUCH,regexEngine.getLineType("how much is glob glob glob glob ?"));
		assertEquals(LineType.NONE,regexEngine.getLineType("how much wood could a woodchuck chuck if a woodchu"));
	}
	
	@Test
	public void voilatesRomanRepitionTest()
	{
		RegexEngine regexEngine = RegexEngine.getInstance();
		assertEquals(true, regexEngine.violatesRomanRepetition("IIII"));
		assertEquals(true, regexEngine.violatesRomanRepetition("XXXX"));
		assertEquals(true, regexEngine.violatesRomanRepetition("CCCC"));
		assertEquals(true, regexEngine.violatesRomanRepetition("MMMM"));
		assertEquals(true, regexEngine.violatesRomanRepetition("DD"));
	}
	
	@Test
	public void isWordTest()
	{
		assertEquals(true, RegexEngine.getInstance().isWord("word"));
		assertEquals(false, RegexEngine.getInstance().isWord("123"));
		assertEquals(false, RegexEngine.getInstance().isWord("word123"));
	}
	
}
