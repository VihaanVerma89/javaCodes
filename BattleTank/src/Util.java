import java.util.Scanner;


public class Util 
{
	public static void sopln(String text)
	{
			System.out.println(text);
	}
	
	public static void promptUser(String status)
	{
		System.out.println(status);
		System.out.println("[Press Enter]\n");
		in.nextLine();
	}
	
	private static Scanner in = new Scanner(System.in);
}
