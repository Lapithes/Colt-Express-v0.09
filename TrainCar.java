import java.util.*;
public class TrainCar
{
	//3 bags
	//1 bag
	//3 jewels
	//4 bags 1 jewel
	//3 bags 1 jewel
	//1 bag 1 jewel
	
	//money supply
	//2 lockboxes
	//6 jewels
	private ArrayList<Money> botList, topList;
	private ArrayList<Player> botplayers, topplayers;
	private int num;
	public TrainCar(ArrayList<Money> initList)
	{
		botList = initList;
		topList = new ArrayList<Money>();
		botplayers = new ArrayList<Player>();
		topplayers = new ArrayList<Player>();
		
	}
	
	public int getNum()
	{
		return num;
	}
	
	public void setNum(int n)
	{
		num = n;
	}
	
	public ArrayList<Money> getBotList()
	{
		return botList;
	}
	public ArrayList<Money> getTopList()
	{
		return topList;
	}
	public ArrayList<Player> getBotPlayers()
	{
		return botplayers;
	}
	
	public ArrayList<Player> getTopPlayers()
	{
		return topplayers;
	}
	
	public void receivePlayer(Player p)
	{
		if(p.getIsTop())
		{
			topplayers.add(p);
		}
		else
		{
			botplayers.add(p);
		}
		
	}
	
	public Player removePlayer(Player p)
	{
		for(int x=0; x<botplayers.size(); x++)
		{
			if(botplayers.get(x).equals(p))
			{
				return botplayers.remove(x);
			}
		}
		for(int x=0; x<topplayers.size(); x++)
		{
			if(topplayers.get(x).equals(p))
			{
				return topplayers.remove(x);
			}
		}
		return null;
	}
}