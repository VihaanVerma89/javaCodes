import java.util.Scanner;


class Tank 
{
	
	public Tank(int fuelLevel)
	{
		this.fuelLevel = fuelLevel;
		location = new Location(50,50);
		in = new Scanner(System.in);
	}
	
	
	public void fire()
	{
		int fuelConsumed = 0;
		if(this.fuelLevel <=0)
		{
			Util.promptUser("Fuel has finished. Can not fire.");
		}
		else
		{
			System.out.println("Enter the Power Level: ");
			int temp = in.nextInt();
			
			if(temp <= this.powerLevels)
			{
				this.powerLevel = temp;
				fuelConsumed = this.powerLevel * this.fuelConsumption;
				
				//dont display negative fuel level.
//				if(fuelLevel < 0)
//				{
//					fuelLevel = 0 ;
//				}
//				
				if(fuelLevel - fuelConsumed < 0)
				{
					fuelConsumed = fuelLevel;
				}
				fuelLevel -= fuelConsumed;
					
				Util.promptUser("Last fire consumed : " + fuelConsumed + " Liters."+
						"\nCurrent Fuel Level: " + fuelLevel + " Liters.");
				
			}
			else
			{
				Util.promptUser("Invalid power level specified. Firing cancelled.");
			}
		}
	}
	
	public void moveBack()
	{
		turnLeft();
		turnLeft();
		moveAhead();
	}
	

	public void moveAhead()
	{
		if(this.direction == Direction.RIGHT)
		{
			if(this.location.x == 100)
			{
				Util.promptUser("Tank on the boundary can't move Right.");
			}
			else
			{
				if(consumeFuel())
				{
					this.location.x++;
					increaseMeter();
				}
			}
		}
		else if(this.direction == Direction.LEFT)
		{
			if(this.location.x == 0)
			{
				Util.promptUser("Tank on the boundary can't move Left.");
			}
			else
			{
				if(consumeFuel())
				{
					this.location.x--;
					increaseMeter();
				}
			}
		}
		else if(this.direction == Direction.UP)
		{
			if(this.location.y == 100)
			{
				Util.promptUser("Tank on the boundary can't move Up.");
			}
			else
			{
				if(consumeFuel())
				{
					this.location.y++;
					increaseMeter();
				}
			}
		}
		else if(this.direction == Direction.DOWN)
		{
			if(this.location.y == 0)
			{
				Util.promptUser("Tank on the boundary can't move Down.");
			}
			else
			{
				if(consumeFuel())
				{
					this.location.y--;
					increaseMeter();
				}
			}
		}
	}
	
	
	protected void increaseMeter()
	{
		meter++;
	}
	
	protected boolean consumeFuel()
	{
		if(fuelLevel<=0)
		{
			Util.promptUser("Fuel has finished. Can not move.");
			return false;
		}
		else
		{
			this.fuelLevel -= this.fuelConsumption;
			return true;
		}
	}
	
	public int [] getLocation()
	{
		return new int [] {this.location.x, this.location.y};
	}
	
	public Enum getDirection()
	{
		return direction;
	}
	
	public int getMeter()
	{
		return meter;
	}
	
	public int getFuelLevel()
	{
		return fuelLevel;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void turnLeft()
	{
		switch(direction)
		{
			case RIGHT: 
				this.direction = Direction.UP;
				break;
			case UP: 
				this.direction = Direction.LEFT;
				break;
			case LEFT: 
				this.direction = Direction.DOWN;
				break;
			case DOWN: 
				this.direction = Direction.RIGHT;
		}
	}
	
	public void turnRight()
	{
			switch(direction)
		{
			case RIGHT: 
				this.direction = Direction.DOWN;
				break;
			case UP: 
				this.direction = Direction.RIGHT;
				break;
			case LEFT: 
				this.direction = Direction.UP;
				break;
			case DOWN: 
				this.direction = Direction.LEFT;
		}
	}
	
	private class Location
	{
		int x, y;
		public Location(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	protected int powerLevels, powerLevel;
	protected int fuelConsumption;
	protected String name;
	
	private Scanner in;
	private int fuelLevel, meter=0;
	private Direction direction = Direction.RIGHT;
	private Location location;
	private enum Direction
	{ UP, DOWN, LEFT, RIGHT};
	
}
