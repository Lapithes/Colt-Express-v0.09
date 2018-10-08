import java.util.*;
public class Train
{//set numplayers to 4;
	private ArrayList<TrainCar> carList;
	public Train(int numplayers)
	{
		carList = new ArrayList<TrainCar>();
		ArrayList<Money> bags =  new ArrayList<Money>();
		int num250bags = 8-numplayers;
		for(int x=0; x<num250bags; x++)
		{
			bags.add(new Money(250, "bag"));
		}
		for(int x=0; x<2; x++)
		{
			bags.add(new Money(300, "bag"));
			bags.add(new Money(350, "bag"));
			bags.add(new Money(400, "bag"));
			bags.add(new Money(450, "bag"));
			bags.add(new Money(500, "bag"));
		}
		Collections.shuffle(bags);
		
		ArrayList<Money> monies = new ArrayList<Money>();
		monies.add(new Money(1000, "lockbox"));
		carList.add(new TrainCar(monies));  
		
		ArrayList<Integer> types = new ArrayList<Integer>();
		for(int x=0; x<6; x++)
		{
			types.add(x);
		}
		Collections.shuffle(types);
		for(int x=0; x<numplayers; x++)
		{
			ArrayList<Money> temp = new ArrayList<Money>();
			switch(types.remove(0))
			{
				default:
					break;
				case 0:
					temp.add(bags.remove(0));
					carList.add(new TrainCar(temp));
					break;
				case 1:
					for(int y=0; y<3; y++)
					{
						temp.add(bags.remove(0));
					}
					carList.add(new TrainCar(temp));
					break;
				case 2:
					temp.add(bags.remove(0));
					temp.add(new Money(500, "jewel"));
					carList.add(new TrainCar(temp));
					break;
				case 3:
					for(int y=0; y<3; y++)
					{
						temp.add(bags.remove(0));
					}
					temp.add(new Money(500, "jewel"));
					carList.add(new TrainCar(temp));
					break;
				case 4:
					for(int y=0; y<4; y++)
					{
						temp.add(bags.remove(0));
					}
					temp.add(new Money(500, "jewel"));
					carList.add(new TrainCar(temp));
					break;
				case 5:
					for(int y=0; y<3; y++)
					{
						temp.add(new Money(500, "jewel"));
					}
					carList.add(new TrainCar(temp));
					break;
			}
			
			
		}
		for(int x=0; x<carList.size(); x++)
		{
			carList.get(x).setNum(x);
		}
	}
	public ArrayList<TrainCar> getCarList()
	{
		return carList;
	}
	public void update()//
	{
		for(TrainCar c: getCarList())
		{
			for(Player p: c.getTopPlayers())
			{
				if(p.getTrainCarNum()!=c.getNum())
				{
					getCarList().get(p.getTrainCarNum()).receivePlayer(c.removePlayer(p));
				}
			}
		}
	}
}