
public class JumboTank extends Tank
{
	private static int i = 1;
	public JumboTank(int fuelCapacity) 
	{
		super(fuelCapacity);
		this.powerLevels = 5;
		this.fuelConsumption=2;
		this.name = "JumboTank" + String.valueOf(i++);
	}
}
