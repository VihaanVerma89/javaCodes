import static org.junit.Assert.*;

import org.junit.Test;

import com.thoughtworks.intergalactic.RomanNumerals;


public class RomanNumeralsTest {

	@Test
	public void toNumbersTest() 
	{
		RomanNumerals rNumeral = new RomanNumerals();
		
		assertEquals(3900, rNumeral.toNumber("MMMCM"));
		assertEquals(101, rNumeral.toNumber("CI"));
		assertEquals(105, rNumeral.toNumber("CV"));
		assertEquals(110, rNumeral.toNumber("CX"));
		assertEquals(150, rNumeral.toNumber("CL"));
		assertEquals(400, rNumeral.toNumber("CD"));
		assertEquals(900, rNumeral.toNumber("CM"));
		
		assertEquals(501, rNumeral.toNumber("DI"));
		assertEquals(505, rNumeral.toNumber("DV"));
		assertEquals(510, rNumeral.toNumber("DX"));
		assertEquals(550, rNumeral.toNumber("DL"));
		assertEquals(600, rNumeral.toNumber("DC"));
		
		
		assertEquals(2, rNumeral.toNumber("II"));
		assertEquals(4, rNumeral.toNumber("IV"));
		assertEquals(1903, rNumeral.toNumber("MCMIII"));
		assertEquals(3900, rNumeral.toNumber("MMMCM"));
		assertEquals(9, rNumeral.toNumber("IX"));
		assertEquals(11, rNumeral.toNumber("XI"));
		assertEquals(15, rNumeral.toNumber("XV"));
		assertEquals(40, rNumeral.toNumber("XL"));
		assertEquals(90, rNumeral.toNumber("XC"));
		assertEquals(101, rNumeral.toNumber("CI"));
		assertEquals(105, rNumeral.toNumber("CV"));
		assertEquals(110, rNumeral.toNumber("CX"));
		assertEquals(150, rNumeral.toNumber("CL"));
		assertEquals(400, rNumeral.toNumber("CD"));
		assertEquals(900, rNumeral.toNumber("CM"));
		assertEquals(501, rNumeral.toNumber("DI"));
		assertEquals(505, rNumeral.toNumber("DV"));
		assertEquals(510, rNumeral.toNumber("DX"));
		assertEquals(550, rNumeral.toNumber("DL"));
		assertEquals(600, rNumeral.toNumber("DC"));
		
		//following inputs violates the subtraction rule 
		//of roman numerals.
		assertEquals(-1, rNumeral.toNumber("IVX"));
		assertEquals(-1, rNumeral.toNumber("IMM"));
		
		assertEquals(-1, rNumeral.toNumber("MMMIM"));
		assertEquals(-1, rNumeral.toNumber("LL"));
		assertEquals(-1, rNumeral.toNumber("VV"));
		assertEquals(-1, rNumeral.toNumber("IL"));
		assertEquals(-1, rNumeral.toNumber("IC"));
		assertEquals(-1, rNumeral.toNumber("ID"));
		assertEquals(-1, rNumeral.toNumber("IM"));
		assertEquals(-1, rNumeral.toNumber("XD"));
		assertEquals(-1, rNumeral.toNumber("XM"));
		assertEquals(-1, rNumeral.toNumber("DM"));
		
	}

}
