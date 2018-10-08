import java.util.*;
public class StoryCard
{
	private int endAction;
	private ArrayList<Integer> RoundInfo;
	private ArrayList<Card> PlayerCards;
	//Constructor-----------------------------------------------------------------------------------------------------------
	public StoryCard(int y)
	{
		RoundInfo = new ArrayList<Integer>();
		switch(y)
		{
			default:
				break;
			case 0:
				RoundInfo.add(1);
				RoundInfo.add(-1);
				RoundInfo.add(2);
				RoundInfo.add(1);
				break;
			case 1:
				RoundInfo.add(1);
				RoundInfo.add(-1);
				RoundInfo.add(1);
				RoundInfo.add(1);
				break;
			case 2:
				RoundInfo.add(1);
				RoundInfo.add(-1);
				RoundInfo.add(1);
				RoundInfo.add(-1);
				RoundInfo.add(1);
				break;
			case 3:
				RoundInfo.add(1);
				RoundInfo.add(2);
				RoundInfo.add(1);
				break;
			case 4:
				RoundInfo.add(1);
				RoundInfo.add(1);
				RoundInfo.add(1);
				RoundInfo.add(1);
				break;
			case 5:
				RoundInfo.add(1);
				RoundInfo.add(1);
				RoundInfo.add(-1);
				RoundInfo.add(1);
				RoundInfo.add(1);
				break;
			case 6:
				RoundInfo.add(1);
				RoundInfo.add(1);
				RoundInfo.add(-1);
				RoundInfo.add(0);
				break;
			case 7:
				RoundInfo.add(1);
				RoundInfo.add(1);
				RoundInfo.add(-1);
				RoundInfo.add(1);
				break;
			case 8:
				RoundInfo.add(1);
				RoundInfo.add(1);
				RoundInfo.add(-1);
				RoundInfo.add(1);
				break;
			case 9:
				RoundInfo.add(1);
				RoundInfo.add(1);
				RoundInfo.add(-1);
				RoundInfo.add(1);
				break;
		}
		endAction = y;
		PlayerCards = new ArrayList<Card>();
	}
	//Get methods-----------------------------------------------------------------------------------------------------------
	public ArrayList<Integer> getRoundInfo()
	{
		return RoundInfo;
	}
	public ArrayList<Card> getPlayerCards()
	{
		return PlayerCards;
	}
	public int getEndAction()
	{
		return endAction;
	}
	public Card getNextCard()
	{
		return PlayerCards.remove(0);
	}
	//Card management--------------------------------------------------------------------------------------------------------
	public void receiveCard(Card x)
	{
		PlayerCards.add(x);
	}
}