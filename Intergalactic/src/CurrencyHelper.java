import java.util.Scanner;

/*
 * Assumptions made to write this program are.
 * 1. All the questions will end with a " ?" , if not they are ignored.
 * e.g "how much is pish pish ?"
 * and "how many Credits is glob glob Silver ?"
 *
 * 2. Inputs are case sensitive 
 * i.e "Silver" is not the same as "silver" ,  so is the case with "Credits".
 * 
 * 3. Bullion bars (Iron, Gold, Silver) or any other (like Platinum) will have
 * initials capital. Regex used to process them depends on the captitalization.
 * If they dont being with Capital letters , they are ignored.
 */

public class CurrencyHelper
{
	public static void main(String args [])
	{
		Util.promptUser("This program helps in currency conversion between Vulcan Numerals and Roman Numerals." +
				"\n Feed in value for Roman Numerals like I,V,X etc..");
		Parser parser = new Parser();
		parser.parse();
	}
}
