import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/*
 * Assumption made for coding
 * 1. The tanks can overlap
 * 2. The starting points for all tanks are (50,50).
 * 3. Tanks can only move between (0,0) and (100,100).
 * 4. Default tank is the most recently created one.
 */

public class BattleTank 
{

	public static void listTanks(Iterator it)
	{
		while(it.hasNext())
		{
			Map.Entry pairs = (Map.Entry)it.next();
			Util.sopln(pairs.getKey() + "." +((Tank)pairs.getValue()).getName());
		}
	}
	
	public static void main(String [] args)
	{
		Map tankMap = new HashMap<Integer , Tank>();
		
		Util.sopln("\t\tBattle Tank");
		Scanner in = new Scanner(System.in);
		Scanner reader = new Scanner(System.in);
		int command=0, choice, capacity, i = 1, currentTank=1;
		
		do
		{
			
			switch(command)
			{
				case 0:
					Util.sopln("Select one of the following Tanks by specifying the corresponding number:");
					Util.sopln("1.Jumbo Tank");
					Util.sopln("2.Mini Tank");
					
					choice = reader.nextInt();
					
					Util.sopln("Enter the tank capacity:");
					capacity = reader.nextInt();
					if(choice == 1)
					{
						Tank tank = new JumboTank(capacity);
						currentTank = i;
						tankMap.put(i++,tank);
					}
					else if (choice == 2)
					{
						Tank tank = new MiniTank(capacity);
						currentTank = i;
						tankMap.put(i++,tank);
					}
					else
					{
						Util.promptUser("Enter a valid input.");
					}
					break;
					
				case 1:
					((Tank)tankMap.get(currentTank)).moveAhead();
					break;
				case 2:
					((Tank)tankMap.get(currentTank)).moveBack();
					break;
				case 3:
					((Tank)tankMap.get(currentTank)).turnLeft();
					break;
				case 4:
					((Tank)tankMap.get(currentTank)).turnRight();
					break;
				case 5:
					Util.promptUser("Direction: " + ((Tank)tankMap.get(currentTank)).getDirection() +"WARDS");
					break;
				case 6:
					Util.promptUser("Distance Travelled: " + ((Tank)tankMap.get(currentTank)).getMeter() + " km/kms.");
					break;
				case 7:
					int [] cooridnates = ((Tank)tankMap.get(currentTank)).getLocation();
					Util.promptUser("X: " + cooridnates[0] + " Y: " +cooridnates[1]);
					break;
					
				case 8:
					Util.promptUser("Fuel Level :" + ((Tank)tankMap.get(currentTank)).getFuelLevel() + " Liter/Liters.");
					break;
					
				case 9: 
					((Tank)tankMap.get(currentTank)).fire();
					break;
					
				case 10:
					if(tankMap.size() > 0)
					{
						Util.sopln("List of tanks:");
						Iterator it = tankMap.entrySet().iterator();
						listTanks(it);
						Util.sopln("Enter the Tank Number to choose:");
						choice = reader.nextInt();
						if(choice >= 1 && choice <= tankMap.size())
						{
							currentTank = choice;
						}
						else
						{
							Util.promptUser("Inavlid tank number.");
						}
					}
					else
					{
						Util.promptUser("There are no tanks at the moment. Please create one first.");
					}
					
				case 11:
						Util.sopln("\nList of tanks:");
						Iterator it = tankMap.entrySet().iterator();
						listTanks(it);
						Util.promptUser(" ");
						
				case 12:
					Util.sopln(((Tank)tankMap.get(currentTank)).getName());
					Util.promptUser("");
					break;
					
				case 111:
					Util.sopln("End of Game.");
			} //switch
			
			Util.sopln("\t\tCommand Menu");
			Util.sopln("0.Create Tank");
			Util.sopln("1.Move Forward");
			Util.sopln("2.Move Back");
			Util.sopln("3.Turn Left");
			Util.sopln("4.Turn Right");
			Util.sopln("5.Get Direction");
			Util.sopln("6.Get Distance Travelled");
			Util.sopln("7.Get Location");
			Util.sopln("8.Get Fuel Level");
			Util.sopln("9.Fire");
			Util.sopln("10.Switch Tank");
			Util.sopln("11.List Tanks");
			Util.sopln("12.Print Tank Name");
			Util.sopln("111.To Exit Program.");
			Util.sopln("\n\nPlease enter a command for the Tank:");
			
			command = in.nextInt();
		
		}while(command != 111);
		
		in.close();
		reader.close();
	}
}
