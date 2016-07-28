
public class Card {
	private char s;
	private int v;
	private boolean f;
	public Card(char s, int v, boolean f)
	{
		this.s=s;
		this.v=v;
		this.f=f;
	}
	public void FaceUp()
	{
		f=true;
	}
	public void FaceDown()
	{
		f=false;
	}
	public boolean CardStatus()
	{
		return f;
	}
	public int CardValue()
	{
		return v;
	}
	public char CardSuit()
	{
		return s;
	}
	public void CardCopy(Card myCard)
	{
		s=myCard.s;
		v=myCard.v;
		f=myCard.f;
	}
	public void PrintCard()
	{
		System.out.println(s+" "+v+" "+f);
	}
	public String PrintCardForTable()
	{
		if(f==false)
		{
			return "XXX";
		}
		if(v==10)
		{
			return s+""+v;
		}
		if(v==1)
		{
			return s+" A";
		}
		if(v==11)
		{
			return s+" J";
		}
		if(v==12)
		{
			return s+" Q";
		}
		if(v==13)
		{
			return s+" K";
		}
		return s+" "+v;
	}
}
