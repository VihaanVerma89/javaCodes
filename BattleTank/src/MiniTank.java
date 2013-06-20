
public class MiniTank extends Tank
{
	private static int i = 1;
	public MiniTank(int fuelCapacity) 
	{
		super(fuelCapacity);
		this.powerLevels = 4;
		this.fuelConsumption=1;
		this.name = "MiniTank"+ String.valueOf(i++);
	}
}
