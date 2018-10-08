import java.util.*;
public class Money
{
	private int value;
	private String type;
	public Money(int v, String t)
	{
		value = v;
		type = t;
	}
  
  public void setValue(int v)
  {
	  value = v;
  }
  
  public void setType(String t)
  {
	  type = t;
  }
	
  public int getValue()
  {
	  return value;
  }
  public String getType()
  {
	  return type;
  }
	
}