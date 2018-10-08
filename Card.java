import java.util.*;
public class Card
{
	private String Action,CharacterName,StorageName;
	private Player thisPlayer;
	//Constructors----------------------------------------------------------------------------------------------------------
	public Card(String a)
	{
		StorageName = a;
	}
	public Card(/*image thingy,*/String a, Player p)
	{
		Action = a;
		CharacterName = p.getCharacterName();
		StorageName = CharacterName+a;
		thisPlayer = p;
	}
	//get methods-----------------------------------------------------------------------------------------------------------
	public Player getThisPlayer()
	{
		return thisPlayer;
	}
	public String getCName()
	{
		return CharacterName;
	}
	public String getAction()
	{
		return Action;
	}
	public String getSName()
	{
		return StorageName;
	}
}