import java.util.Scanner;


public class SpreadSheets
{
	public String getLabel(int number)
	{
		int numberOfChar = getNumberOfChar(number);
		String format [] = getInFormat(number, numberOfChar);
		StringBuilder sb = new StringBuilder(); 
		
		for(int i = format.length -1 ; i >= 0 ; i--)
		{
			sb.append(getCharacter(Integer.parseInt(format[i])));
		}
		
		return sb.toString();
	}
	
	private String getCharacter(int number)
	{
		int asciiValue = 65 + number - 1;
		String value1 = Character.toString((char)asciiValue);
		return value1;
	}
	
	private String [] getInFormat(int number, int numberOfChar)
	{
		StringBuilder sb = new StringBuilder();
		int temp = number;
		
		for(int i =0 ;  i <= numberOfChar -1 ; i++)
		{
			sb.append(getR(temp, numberOfChar));
			sb.append(" ");
			temp = getQ(temp);
		}
		if(numberOfChar > 1 && sb.length()< numberOfChar)
		{
			sb.append(getQ(temp));
		}
		return sb.toString().split(" ");
	}
	
	private int getQ(int number)
	{
		if( number % 26 == 0)
		{
			//consider number 1353 which should give BAA.
			// 1353 %26 = 1
			// 1353 /26 = 52
			// 52 % 26 = 0 which should not happen 
			
			return number+1;
		}
		else
		{
			return number / 26;
		}
	}
	
	private int getR(int number, int numberOfChar)
	{
		//when number is 26 or a muliple of 26 return 26.
		if( number % 26 == 0)
			return  26;
		else
		return number % 26;
	}
	
	
	private int getNumberOfChar(int number)
	{
		int base = 26;
		int sum = 0;
		int i=0;
		
			while( !(number < ( Math.pow(base, i) + sum)))
			{
				sum += Math.pow(base, i);
				i++;
			}
		
		return i;
	}
	
	
	public static void main(String [] args )
	{
		SpreadSheets ss = new SpreadSheets();
//		int number = 703;
//		int number = 18279;
		int number = 18500;
//		int number = 26*26*26*26*26;
//		int number = 26*26*26*26*26;
		
		for(int i = 1 ; i <= number ; i++)
		{
			String result =ss.getLabel(i);
			System.out.println(i + " = " +result);
		}
		
	}
}
