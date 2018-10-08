import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
public class Game {
	GUI GUIMain;
	StoryCard current;
	Stack<StoryCard> StoryCards;
	ArrayList<Player> playerOrder;
	int sheriffPosition;
	Train train;
	public Game() throws InterruptedException
	{
		initialize();
		GUIMain = new GUI(playerOrder, train);
		TimeUnit.MILLISECONDS.sleep(3000);
		GUIMain.init(playerOrder, train, sheriffPosition);
	}
	
	public void initialize()		
	{
		StoryCards = new Stack<StoryCard>();
		playerOrder = new ArrayList<Player>();
		sheriffPosition = 0;
		train = new Train(4);
		
		ArrayList<StoryCard> temp = new ArrayList<StoryCard>();
		for(int x=7; x<10; x++)
		{
			temp.add(new StoryCard(x));
		}
		int rand = (int)Math.random()*3;
		StoryCards.push(temp.get(rand));
		
		Stack<StoryCard> temp1 = new Stack<StoryCard>();
		for(int x=0; x<7; x++)
		{
			temp1.push(new StoryCard(x));
		}
		Collections.shuffle(temp1);
		
		for(int x=0; x<7; x++)
		{
			StoryCards.push(temp1.pop());
		}
		
		
		ArrayList<Integer> types = new ArrayList<Integer>();
		for(int y=1; y<7; y++)
		{
			types.add(y);
			
		}
		Collections.shuffle(types);
		for(int x=0; x<4; x++)
		{
			switch(types.remove(0))
			{
				default:
					break;
				case 1:
					playerOrder.add(new Player("Cheyenne"));
					break;
				case 2:
					playerOrder.add(new Player("Django"));
					break;
				case 3:
					playerOrder.add(new Player("Ghost"));
					break;
				case 4:
					playerOrder.add(new Player("Doc"));
					break;
				case 5:
					playerOrder.add(new Player("Tuco"));
					break;
				case 6:
					playerOrder.add(new Player("Belle"));
					break;
			}
				
		}
		
		// put sheriff in top right corner of each traincar
		//paint window
		//make ui
		//include number of monies in each traincar
		//include player monies
		for(int x=0; x<4; x++)
		{
			if(x/2==0)
			{
				playerOrder.get(x).setTrainCarNum(train.getCarList().size()-1);
				train.getCarList().get(train.getCarList().size()-1).receivePlayer(playerOrder.get(x));
			}
			else
			{
				playerOrder.get(x).setTrainCarNum(train.getCarList().size()-2);
				train.getCarList().get(train.getCarList().size()-2).receivePlayer(playerOrder.get(x));
			}
			//display players in traincar
			
		}
		
		
	}
	public void startGame() throws InterruptedException
	{
		/*current = getNewCurrent();
		for(int x=0; x<current.getRoundInfo().size(); x++)
		{
			System.out.print(current.getRoundInfo().get(x));
		}
		GUIMain.ask(playerOrder.get(0),"up");
		while(!GUIMain.clicked)
		{
			TimeUnit.MILLISECONDS.sleep(100);
		}
		System.out.println("one");
		GUIMain.clicked = false;*/
		
		
		
		while(!StoryCards.empty())
		{
			current = getNewCurrent();
			for(int x=0; x<current.getRoundInfo().size(); x++)
			{
				System.out.println(">"+current.getRoundInfo().get(x)+", ");
			}
			//display current storycard on screen
			getCards();
			playStory();
			switchPlayerOrder();
		}
	}
	public StoryCard getNewCurrent()
	{
		return StoryCards.pop();
	}
	public void getCards() throws InterruptedException
	{
		ArrayList<Integer> roundInfo = current.getRoundInfo();
		for(int i=0;i<roundInfo.size();i++)
		{
			int type = roundInfo.get(i);
			if(type==0)
			{
				for(int x=playerOrder.size()-1; x>-1; x--)
				{
					Card c = null;
					if(i==0&&playerOrder.get(x).getCharacterName().equals("Ghost"))
					{
						GUIMain.ask(playerOrder.get(x),"down");
						while(!GUIMain.clicked)
						{
							TimeUnit.MILLISECONDS.sleep(100);
						}
						GUIMain.clicked = false;
						System.out.println(">Ghost has made a move");
					}
					else
					{
						GUIMain.ask(playerOrder.get(x),"up");
						while(!GUIMain.clicked)
						{
							TimeUnit.MILLISECONDS.sleep(100);
						}
						GUIMain.clicked = false;
						System.out.println(">"+playerOrder.get(x).getCharacterName()+" has made a move");
					}
					c=GUIMain.currentCard;
					if(c!=null)
						current.receiveCard(c);
						
				}
			}
			else if(type==1)
			{
				for(Player p: playerOrder)
				{
					Card c = null;
					if(i==0&&p.getCharacterName().equals("Ghost"))
					{
						GUIMain.ask(p,"down");
						while(!GUIMain.clicked)
						{
							TimeUnit.MILLISECONDS.sleep(100);
						}
						GUIMain.clicked = false;
						System.out.println(">"+p.getCharacterName()+" has made a move");
					}
					else
					{
						GUIMain.ask(p,"up");
						while(!GUIMain.clicked)
						{
							TimeUnit.MILLISECONDS.sleep(100);
						}
						GUIMain.clicked = false;
						System.out.println(">"+p.getCharacterName()+" has made a move");
					}
					c = GUIMain.currentCard;
					if(c!=null)
						current.receiveCard(c);
				}
			}
			else if(type==2)
			{
				for(Player p: playerOrder)
				{
					Card c1 = null;
					Card c2 = null;
					if(i==0&&p.getCharacterName().equals("Ghost"))
					{
						GUIMain.ask(p,"down");
						while(!GUIMain.clicked)
						{
							TimeUnit.MILLISECONDS.sleep(100);
						}
						GUIMain.clicked = false;
						System.out.println(">"+p.getCharacterName()+" has made a move");
						c1 = GUIMain.currentCard;
						if(c1!=null)
							current.receiveCard(c1);
						
						GUIMain.ask(p,"up");
						while(!GUIMain.clicked)
						{
							TimeUnit.MILLISECONDS.sleep(100);
						}
						GUIMain.clicked = false;
						System.out.println(">"+p.getCharacterName()+" has made a move");
						c2 = GUIMain.currentCard;
						if(c2!=null)
							current.receiveCard(c2);
					}
					else
					{
						GUIMain.ask(p,"up");
						while(!GUIMain.clicked)
						{
							TimeUnit.MILLISECONDS.sleep(100);
						}
						GUIMain.clicked = false;
						System.out.println(">"+p.getCharacterName()+" has made a move");
						c1 = GUIMain.currentCard;
						if(c1!=null)
							current.receiveCard(c1);
						
						GUIMain.ask(p,"up");
						while(!GUIMain.clicked)
						{
							TimeUnit.MILLISECONDS.sleep(100);
						}
						GUIMain.clicked = false;
						System.out.println(">"+p.getCharacterName()+" has made a move");
						c2 = GUIMain.currentCard;
						if(c2!=null)
							current.receiveCard(c2);
					}
					
					
				}
			}
			else if(type==-1)
			{
				Card c = null;
				for(Player p:playerOrder)
				{
					GUIMain.ask(p,"down");
					while(!GUIMain.clicked)
					{
						TimeUnit.MILLISECONDS.sleep(100);
					}
					GUIMain.clicked = false;
					System.out.println(">"+p.getCharacterName()+" has made a move");
					c = GUIMain.currentCard;
					if(c!=null)
						current.receiveCard(c);
				}
				
			}
			/*else if(type==-2)
			{
				for(Player p:playerOrder)
				{
					current.receiveCard(GUIMain.ask(p,"down"));
					current.receiveCard(GUIMain.ask(p,"down"));
				}
			}*/
			
		}
	}
	public void playStory() throws InterruptedException
	{
		for(Card c:current.getPlayerCards())
		{
			playCard(c);
			c.getThisPlayer().getDiscardPile().add(c);
		}
		playEndAction(current);
		//discard to discardpile
	}
	
	
	public void playCard(Card c) throws InterruptedException
	{
		if(c.getAction().equals("Move"))
		{
			move(c.getThisPlayer());
		}
		else if(c.getAction().equals("MoveVert"))
		{
			moveVert(c.getThisPlayer());
		}
		else if(c.getAction().equals("Shoot"))
		{
			shoot(c.getThisPlayer());
		}
		else if(c.getAction().equals("Pick"))
		{
			rob(c.getThisPlayer());
		}
		else if(c.getAction().equals("Sheriff"))
		{
			moveSheriff(c.getThisPlayer());
		}
		else if(c.getAction().equals("Punch"))
		{
			punch(c.getThisPlayer());
		}
		
	}
	
	public void playEndAction(StoryCard current)
	{
		int x = current.getEndAction();
		switch(x)
		{
			default:
				break;
			case 0:
				train.getCarList().get(sheriffPosition).getBotList().add(new Money(1000, "lockbox"));
				System.out.println(">Marshal dropped a new lockbox!");
				break;
			case 1:
				for(TrainCar c: train.getCarList())
				{
					for(Player p: c.getTopPlayers())
					{
						if(p.getIsTop())
						{
							p.move("", train.getCarList().size()-1);
						}
					}
						
				}
				train.update();
				System.out.println(">everyone on top of train moves to caboose");
				break;
			case 2:
				System.out.println(">nothing happens");
				break;
			case 3:
				System.out.println(">nothing happens");
				break;
			case 4:
				for(TrainCar c: train.getCarList())
				{
					for(Player p: c.getTopPlayers())
					{
						if(p.getIsTop()&&p.getTrainCarNum()!=0)
						{
							p.move("", p.getTrainCarNum()-1);
						}
					}
						
				}
				train.update();
				System.out.println(">everyone on top of train moves forward one space");
				break;
			case 5:
				for(TrainCar c: train.getCarList())
				{
					for(Player p: c.getBotPlayers())
					{
						if(!p.getIsTop())
						{
							p.getDrawPile().add(new Card("Bullet"));
							System.out.println(">"+p.getCharacterName()+" received damage(1point)");
						}
					}
						
				}
				train.update();
				break;
			case 6:
				for(Player p: train.getCarList().get(sheriffPosition).getTopPlayers()) {
					if(p.getIsTop()) {
						p.getDrawPile().add(new Card("Bullet"));
						System.out.println(">"+p.getCharacterName()+" received damage(1point)");
					}
				}
				break;
			case 7:
				for(TrainCar c: train.getCarList())
				{
					if(c.getTopPlayers().size()+c.getBotPlayers().size()==1)
					{
						
						if(c.getTopPlayers().size()==1)
						{
							Player p = c.getTopPlayers().get(0);
							for(int i=0; i<c.getTopList().size(); i++)
							{
								if(c.getTopList().get(i).getType().equals("bag"))
								{
									Money m = c.getTopList().remove(i);
									p.receiveMoney(m);
									System.out.println(">"+p.getCharacterName()+" robbed a "+m.getType()+"("+m.getValue()+")");
									break;
								}
							}
							
						}
						else
						{
							Player p = c.getBotPlayers().get(0);
							for(int i=0; i<c.getBotList().size(); i++)
							{
								if(c.getBotList().get(i).getType().equals("bag"))
								{
									Money m = c.getBotList().remove(i);
									p.receiveMoney(m);
									System.out.println(">"+p.getCharacterName()+" robbed a "+m.getType()+"("+m.getValue()+")");
									break;
								}
							}
							
						}
						
					}
				}
				break;
			case 8:
				for(Player p: train.getCarList().get(sheriffPosition).getTopPlayers())
				{
					if(p.getIsTop())
					{
						Money m = new Money(1000, "lockbox");//temp
						for(int i=0; i<p.getCash().size(); x++)
						{
							if(p.getCash().get(i).getType().equals("bag"))
							{
								m = p.getCash().remove(i);
								break;
							}
							
						}
						if(m!=null)
						{
							System.out.println(">"+p.getCharacterName()+" dropped a "+m.getType()+"("+m.getValue()+")");
						}
						else
							System.out.println(">"+p.getCharacterName()+" does not have a bag to drop");
							
					}
				}
				break;
			case 9:
				for(Player p: train.getCarList().get(0).getTopPlayers())
				{
					p.receiveMoney(new Money(250, "bag"));
					System.out.println(">"+p.getCharacterName()+" received money(250)");
				}
				for(Player p: train.getCarList().get(0).getBotPlayers())
				{
					p.receiveMoney(new Money(250, "bag"));
					System.out.println(">"+p.getCharacterName()+" received money(250)");
				}
				break;
				
		}
	}
	
	public void move(Player a) throws InterruptedException
	{
		int carNum = a.getTrainCarNum();
		int bot;
		int top;
		if(a.getIsTop())
		{
			bot = carNum-3;
			top = carNum+3;
		}
		else
		{
			bot = carNum-1;
			top = carNum+1;
		}
		ArrayList<String> moveOptions = new ArrayList<String>();
		for(int x=bot; x<top+1; x++)
		{
			if(!(x<0||x>4||x==carNum))
			{
				moveOptions.add(""+x);
				// arraylist and display in menu?
				//display options in dropdown menu
			}
		}
		GUIMain.askMove(a, moveOptions);
		while(!GUIMain.clicked)
		{
			TimeUnit.MILLISECONDS.sleep(100);
		}
		System.out.println(">"+a.getCharacterName()+" moved to traincar "+GUIMain.tempstr);
		a.setTrainCarNum(Integer.parseInt(GUIMain.tempstr));
		train.update();
		GUIMain.updatePosition(train);
		GUIMain.clicked = false;
		GUIMain.tempstr = "";
		
		//next
		//int target = mouseclicked option
		//a.move("", target);
		//refresh gui
		
		
	}
	public void moveVert(Player a)
	{
		a.move("vertical", 0);
		//refresh gui
	}
	public void rob(Player a)
	{
		TrainCar t = train.getCarList().get(a.getTrainCarNum());
		if(a.getIsTop())
		{
			int index = 0;
			if(t.getTopList().size()>0)
			{
				for(Money m: t.getTopList())
				{
					//maybe add these to a temp arraylist and display in menu?
					//display options in dropdown menu
					//m.getType()+m.getValue() as a string
				}
				//next
				//index = mouseclicked option
				a.receiveMoney(t.getTopList().remove(index));
			}
			else
			{
				//print a.name does not get any money in console
				return;
			}
		}
		else
		{
			int index = 0;
			if(t.getBotList().size()>0)
			{
				for(Money m: t.getBotList())
				{
					//maybe add these to a temp arraylist and display in menu?
					//display options in dropdown menu
					//m.getType()+m.getValue() as a string
				}
				//next
				//index = mouseclicked option
				a.receiveMoney(t.getBotList().remove(index));
			}
			else
			{
				//print a.name does not get any money in console
				return;
			}
		}
	}
	public void moveSheriff(Player a)
	{
		int carNum = sheriffPosition;
		int bot;
		int top;
		
		bot = carNum-1;
		top = carNum+1;
		
		
		for(int x=bot; x<top+1; x++)
		{
			if(!(x<0||x>4||x==carNum))
			{
				//maybe add these to a temp arraylist and display in menu?
				//display options in dropdown menu
			}
		}
		//next
		
		//int target = mouseclicked option
		//sheriffPosition = target
		//refresh gui
	}
	public void punch(Player a)
	{
		TrainCar t = train.getCarList().get(a.getTrainCarNum());
		ArrayList<Player> temp = new ArrayList<Player>();
		if(a.getIsTop())
		{
			for(Player p: t.getTopPlayers())
			{
				if(p.getIsTop()==a.getIsTop())
				{
					temp.add(p);
				}
			}
		}
		else
		{
			for(Player p: t.getBotPlayers())
			{
				if(p.getIsTop()==a.getIsTop())
				{
					temp.add(p);
				}
			}
		}
		
		if(temp.size()>1)
		{
			for(int x=0; x<temp.size(); x++)
			{
				if(temp.get(x).getCharacterName().equals("Belle"))
				{
					temp.remove(x);
				}
			}
		}
		//display options in dropdown menu
		//next
		//Player target// = mouseclicked option
		
		//for(Money m: target.getCash())
		{
			//display options in dropdown menu 
		}
		//next
		//int index// = mouseclicked option
		//Money temp = target.getCash().remove(index);
		if(a.getCharacterName().equals("Cheyenne"))
		{
		//	a.receiveMoney(temp);
		}
		else
		{
			if(a.getIsTop())
			{
		//		t.getTopList().add(temp);
			}
			else
			{
		//		t.getBotList().add(temp);
			}
		}
		int bot; //= target.getTrainCarNum()-1;
		int top; //= target.getTrainCarNum()+1;
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		if(bot>-1)
		{
			temp2.add(bot);
		}
		if(top<5)
		{
			temp2.add(top);
		}
		//display options in dropdown menu
		//next
		//int index = mouseclicked option
		//target.move("",index);
		//train.update();
		//refresh gui
	}
	public void shoot(Player a)//implement tuco
	{
		TrainCar t = train.getCarList().get(a.getTrainCarNum());
		int bot = a.getTrainCarNum()-1;
		int top = a.getTrainCarNum()+1;//check for index out of bounds
		//Player target//;
		//combine two elses?
		ArrayList<Player> temp = new ArrayList<Player>();
		if(bot>-1)
		{
			ArrayList<Player> tempplayer = new ArrayList<Player>();
			if(a.getIsTop())
			{
				tempplayer = train.getCarList().get(bot).getTopPlayers();
			}
			else
			{
				tempplayer = train.getCarList().get(bot).getBotPlayers();
			}
			for(Player p: tempplayer)
			{
				if(a.getIsTop()==p.getIsTop())
				{
					temp.add(p);
				}
			}
		}
		if(top<5)
		{
			ArrayList<Player> tempplayer = new ArrayList<Player>();
			if(a.getIsTop())
			{
				tempplayer = train.getCarList().get(top).getTopPlayers();
			}
			else
			{
				tempplayer = train.getCarList().get(top).getBotPlayers();
			}
			for(Player p: tempplayer)
			{
				if(a.getIsTop()==p.getIsTop())
				{
					temp.add(p);
				}
			}
		}
		bot--;
		top++;
		if(a.getIsTop())
		{
				while(bot>-1)
				{
					boolean hit = false;
					for(Player p: train.getCarList().get(bot).getTopPlayers())
					{
						if(p.getIsTop())
						{
							temp.add(p);
							hit = true;
						}
					}
					if(hit)
					{
						break;
					}
					bot--;
				}
				while(top<5)
				{
					boolean hit = false;
					for(Player p: train.getCarList().get(top).getTopPlayers())
					{
						if(p.getIsTop())
						{
							temp.add(p);
							hit = true;
						}
					}
					if(hit)
					{
						break;
					}
					top++;
				}
		}
		if(a.getCharacterName().equals("Tuco"))
		{
			if(a.getIsTop())
			{
				for(Player p: train.getCarList().get(a.getTrainCarNum()).getTopPlayers())
				{
					temp.add(p);
				}
			}
			else
			{
				for(Player p: train.getCarList().get(a.getTrainCarNum()).getBotPlayers())
				{
					temp.add(p);
				}
			}
			
		}
		if(temp.size()==0)
		{
			System.out.println(">There's nobody to shoot");
			return;
		}
		else
		{
			if(temp.size()>1)
			{
				for(int x=0; x<temp.size(); x++)
				{
					if(temp.get(x).getCharacterName().equals("Belle"))
					{
						temp.remove(x);
					}
				}
			}
			//display options in dropdown menu
			//next
			//target// = mouseclicked option
		}
		
			
		if(a.getCharacterName().equals("Django"))
		{
			int bot2;// = target.getTrainCarNum()-1;
			int top2;// = target.getTrainCarNum()+1;
			ArrayList<Integer> temp2 = new ArrayList<Integer>();
			//if(bot2>-1)
			{
				temp2.add(bot2);
			}
			if(top2<5)
			{
				temp2.add(top2);
			}
			//display options in dropdown menu
			//next
			//int index = mouseclicked option
			//target.move("",index);
			//train.update();
			//refresh gui
		}
		Card c = a.getShot();
		if(c != null)
		{
			//target.getDrawPile().add(c);
			//System.out.println
		}
			//review this(
		
		//refresh gui
	}
	
	
	public void checkSheriff()
	{
		for(Player p: playerOrder)
		{
			if (p.getTrainCarNum()==sheriffPosition&&!p.getIsTop())
			{
				p.getDrawPile().add(new Card("Bullet"));
				p.move("vertical", 0);
			}
		}
	}
	
	public void finish()
	{
		Player best = playerOrder.get(0);
		for(Player p: playerOrder)
		{
			if(p.getBulletCash())
			{
				//print name + (money+1000)
				p.setFinalScore(p.getMoneyCount()+1000);
			}
			else
			{
				//print player name + money
				p.setFinalScore(p.getMoneyCount());
			}
			if(p.getFinalScore()>best.getFinalScore())
			{
				best = p;
			}
		}
		//print best.getCharacterName() wins
		//allow user to click next button one more time
		//then return to main menu
	}
	
	public void switchPlayerOrder()
	{
		playerOrder.add(playerOrder.remove(0));
	}
}

