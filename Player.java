//Nicholas Roth
import java.util.*;
public class Player
{
	private String CharacterName;
	private int Ability,TrainCarNum, playerNum, finalScore;
	private Stack<Card> BulletPile, DrawPile, DiscardPile;
	private ArrayList<Card> hand;
	private ArrayList<Money> cash;
	private boolean isTop,bulletCash;
	public Player(String name)//revise constructor
	{
		cash = new ArrayList<Money>();
		hand = new ArrayList<Card>();
		BulletPile = new Stack<Card>();
		DrawPile = new Stack<Card>();
		DiscardPile = new Stack<Card>();
		CharacterName = name;//Sets character name
		receiveMoney(new Money(250, "bag"));//initial money
		bulletCash = false;
		//TrainCarNum = TCar;
		isTop = false;
		finalScore = 0;
		pileInit();//initailizes card piles at the begining of the game
	}
	
	public String getCharacterName()
	{
		return CharacterName;
	}
	public ArrayList<Card> getHand()
	{
		return hand;
	}
	public ArrayList<Money> getCash()
	{
		return cash;
	}
	public Stack<Card> getDrawPile()
	{
		return DrawPile;
	}
	public Stack<Card> getDiscardPile()
	{
		return DiscardPile;
	}
	public boolean getIsTop()
	{
		return isTop;
	}
	public boolean getBulletCash()
	{
		return bulletCash;
	}
	public int getTrainCarNum()
	{
		return TrainCarNum;
	}
	public void setTrainCarNum(int num)
	{
		TrainCarNum = num;
	}
	public void setFinalScore(int num)
	{
		finalScore = num;
	}
	public int getFinalScore()
	{
		return finalScore;
	}
	public void pileInit()//checks what player is being created and uses the DIR associated with said player
	{
		BulletPile = new Stack<Card>();
		for(int x=1; x<7; x++)
		{
			BulletPile.push(new Card("Bullet"+x, this));
		}
		
		DrawPile = new Stack<Card>();
		for(int x=0; x<2; x++)
		{
			DrawPile.push(new Card("Move", this));
			DrawPile.push(new Card("MoveVert", this));
			DrawPile.push(new Card("Shoot", this));
			DrawPile.push(new Card("Pick", this));
		}
		DrawPile.push(new Card("Sheriff", this));
		DrawPile.push(new Card("Punch", this));
		Collections.shuffle(DrawPile);
		
		hand = new ArrayList<Card>();
		DiscardPile = new Stack<Card>();
		drawToSix();
	}
	//Player interations--------------------------------------------------------------------------------------------------
	public void receiveMoney(Money m)
	{
		cash.add(m);
	}
	public int getMoneyCount()
	{
		int sum = 0;
		for(Money m: cash)
		{
			sum+=m.getValue();
		}
		return sum;
	}

	public Card getCard(String name)
	{
		for(int x=0; x<getHand().size(); x++)
		{
			if(getHand().get(x).getSName().equals(name))
			{
				return hand.remove(x);
			}
		}
		return null;
	}
	
	public Card getShot()
	{
		Card shot;
		if(!BulletPile.empty())
			shot = BulletPile.pop();
		else
			shot = null;
		
		if(BulletPile.empty()&&!bulletCash)
		{
			//moneyCount+=1000;
			bulletCash = true;//at the end of the game, check if bulletcash == true, if true, add 1000 to score
		}
		return shot;
	}
	public void receiveCard(Card c)
	{
		DrawPile.push(c);
	}
	//Card management-----------------------------------------------------------------------------------------------------
	public void drawToSix()
	{
		if(!CharacterName.equals("Doc"))
		{
			while(hand.size()!=6)
			{
				if(DrawPile.empty())
					repile();
				hand.add(DrawPile.pop());
			}
		}	
		else
		{
			while(hand.size()!=7)
			{
				if(DrawPile.empty())
					repile();
				hand.add(DrawPile.pop());
			}
		}
		
	}
	public void drawThree()
	{
		for(int i=0;i<3;i++)
		{
			if(DrawPile.empty())
				repile();
			hand.add(DrawPile.pop());
		}
	}
	public void repile()
	{
		Collections.shuffle(DiscardPile);
		DrawPile = DiscardPile;
		DiscardPile = new Stack<Card>();
	}
	//Player movement-----------------------------------------------------------------------------------------------------
	public void move(String direction, int carNum)
	{
		if(direction.equals("vertical"))
		{
			if(isTop)
				isTop = false;
			else
				isTop = true;
		}
		else
		{
			setTrainCarNum(carNum);
		}
	}
}
	
	
	
	