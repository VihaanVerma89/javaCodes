package com.thoughtworks.intergalactic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RomanNumerals  extends Numerals
{		
	
	private enum Numeral{ I(1), V(5), X(10), L(50), C(100), D(500),
		M(1000);
			
			private final int number;
			private Numeral(int number)
			{
				this.number = number;
			}
			
			public int getNumber()
			{
				return number;
			}
		};//Numeral

	
	private int getNumberFromChar(char numeral)
	{
		//temp cant be null.
		int temp = -1;
		switch(numeral)
		{
			case 'I' : 
						temp = Numeral.I.getNumber();
						break;
			case 'V' : 
						temp = Numeral.V.getNumber();
						break;
			case 'L' : 
						temp = Numeral.L.getNumber(); 
						break;
			case 'X' : 
						temp = Numeral.X.getNumber();
						break;
			case 'C' : 
						temp = Numeral.C.getNumber();
						break;
			case 'D' : 
						temp = Numeral.D.getNumber();
						break;
			case 'M' : 
						temp = Numeral.M.getNumber();
						break;
			default:
						Util.promptUser("Invalid Entry");
		}
		return temp;
	 }
	
	
	private List<Integer> assignSigns(List<Integer> numbers)
	{
		int currentElement;
		//dont negate the last number , as is to be added everytime.
		for(int i = 0 ; i < numbers.size() -1; i++)
		{
			currentElement = numbers.get(i);
			if( currentElement < numbers.get(i+1))
			{
				numbers.set(i, -currentElement);
			}
		}
		return numbers;
	}
	
	public int toNumber(String romanNumeral)
	{
		List<Integer> numbers = getNumbers(romanNumeral.toCharArray());
		
		if(numbers != null)
		{
			numbers = assignSigns(numbers);
				
			int  finalNumber=0;
			
			//check the Roman Numeral validity.
			if(!regexEngine.violatesRomanRepetition(romanNumeral))
			{
				if(!violatesSubtraction(numbers))
				{
					//The number is a valid Roman numeral, time to convert it into number.
					for(int i : numbers)
					{
						finalNumber += i;
					}
					return finalNumber;		
				}
				else
				{
					Util.promptUser("Roman Numeral Subtraction condition not met.");
				}
			}
		
		}
		return -1;
	}
	
	//Get Roman equivalent Numbers.
	private List<Integer> getNumbers(char [] parts)
	{
		int result;
		List<Integer> numbers = new ArrayList<Integer>();
	
		for(char c : parts)
		{
			result = getNumberFromChar(c);
			if(result != -1)
			{
				numbers.add(result);
			}
			else
			{
				Util.promptUser("Incorrect Roman Numeral.");
				numbers = null;
				break;
			}
		}
		return numbers;
	}
	
	private boolean violatesSubtraction(List<Integer> numbers)
	{
		int number, nextNumber;
		for(int i = 0 ; i < numbers.size()-1; i++)
		{
			number = numbers.get(i);
			nextNumber = Math.abs(numbers.get(i+1));
			
			//V, L , D can never be subtracted.
//			if( number == -5 || number == -50 || number == - 500)
			if( number == - Numeral.V.getNumber() || number == -Numeral.L.getNumber()
					|| number == - Numeral.D.getNumber())
			{
				Util.promptUser("V, L, and D can never be subtracted.");
				return true;
			}
			
			switch(Math.abs(number))
			{
			case  1:
					// I can only be subtracted from V or X.
					if(nextNumber > Numeral.I.getNumber() && nextNumber != Numeral.V.getNumber() && nextNumber != Numeral.X.getNumber() && nextNumber != number )
					{
						Util.promptUser("I can be subtracted from V and X only.");
						return true;
					}
					break;
					
			case 10:
					// X can only be subtracted from L or C.
					if(nextNumber > Numeral.X.getNumber() && nextNumber != Numeral.L.getNumber() && nextNumber != Numeral.C.getNumber() && nextNumber != number )
					{
						Util.promptUser("X can be subtracted from L and C only.");
						return true;
					}
					break;
					
			case 100 :
					// C can only be subtracted from D or M.
					if(nextNumber > Numeral.C.getNumber() && nextNumber != Numeral.D.getNumber()&& nextNumber != Numeral.M.getNumber()&& nextNumber != number )
					{
						Util.promptUser("C can be subtracted from D and M only.");
						return true;
					}
					break;
			}
		}
		
		return false;
	}
			
	private RegexEngine regexEngine = RegexEngine.getInstance();
}
