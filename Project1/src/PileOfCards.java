import java.util.Random;
public class PileOfCards {
	private int SIZE;
	private Card[] myCardArray;
	PileOfCards(int SIZE)
	{
		myCardArray=new Card[SIZE];
		this.SIZE=SIZE;
	}
	public void MakeDeck()//Create cards 
	{
		char[] suit={'S','C','D','H'};
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<13;j++)
			{
				int locationOfDeck=i*13+j;
			    myCardArray[locationOfDeck]=new Card(suit[i],j+1,false);
			}
		}
	}
	public void Swap(Card[] myCardArray,int firstLocation,int secondLocation)//Swap the cards 
	{
		Card temp = new Card('\0',0,false);
		temp.CardCopy(myCardArray[secondLocation]);
		myCardArray[secondLocation].CardCopy(myCardArray[firstLocation]);
		myCardArray[firstLocation].CardCopy(temp);
	}
	public void Shuffle()//Shuffle the deck
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);//Seed a random int
		for(int i=0;i<randomInt;i++)//Swap two card randomly many times
		{
			int first = randomGenerator.nextInt(100)%52;
			int second = randomGenerator.nextInt(100)%52;
			Swap(myCardArray,first,second);
		}
	}
	public Card TakeTopCard()//Returns last card if the pile is empty return null
	{
		int i=SIZE-1;
		while(myCardArray[i]==null)
		{
			i--;
		}
		Card lastCard =myCardArray[i];
		myCardArray[i]=null;
		return lastCard;
	}
	public void AddCard(Card inCard)
	{	
		int i=SIZE;
		while(myCardArray[i]!=null)
		{
			i--;
		}
		myCardArray[i+1]=inCard;
	}
	public void SetCard(int location,Card myCard)//Set a card in a specific location
	{
		myCardArray[location]=myCard;
	}
	public Card GetCard(int location)//Get a card
	{
		return myCardArray[location];
	}
	public void FaceUp(int location)//Face up the card
	{
		myCardArray[location].FaceUp();
	}
	public void PrintPile()//I create this to test my arrays 
	{
	    int i=0;
		
	    while(myCardArray[i]!=null)
	    {
			myCardArray[i].PrintCard();
			i++;
		}
	}
	public void GiveThree(PileOfCards facedUpPile)//Gives three card to leftDeckFacedUp
	{
		for(int i=0;i<3;i++)
		{
			if(myCardArray[0]==null)
			{
				continue;
			}
			facedUpPile.SetCard(facedUpPile.LocationOfLastCard()+1, myCardArray[0]);
			facedUpPile.GetCard(facedUpPile.LocationOfLastCard()).FaceUp();//Face the card up 
			for(int j=0,k=1;j<52;j++)//Slide the cards
			{
				
				myCardArray[j]=myCardArray[k];
				if(myCardArray[k]==null)
				{
					break;
				}
				k++;
			}
		}
	}
	int LocationOfLastCard()
	{
		int i=0;
		while(myCardArray[i]!=null)
	    {
			i++;
	    }
		return (i-1);
	}
	int GetFirstTrueCardLocation()//returns location of first faced up card
	{
		if(LocationOfLastCard()==-1)
		{
			return -1;
		}
		int i=myCardArray.length-1;
		int j=0;
		while(myCardArray[j]!=null)//increase j till end of the array
		{
			j++;
		}
		j=j-1;
		while(myCardArray[j].CardStatus())//decrease j until card status is true
		{
			j--;
			
			if(j==-1)
			{
				break;
			}
		}
		return j+1;
	}
	Card GetLastCard()
	{
		if(LocationOfLastCard()==-1)
		{
			return null;
		}
		return myCardArray[LocationOfLastCard()];
	}
	public void fillBack(PileOfCards facedUp)//Fill leftDeck back
	{
		int lastFacedUp=facedUp.LocationOfLastCard();//take the location of last face up card
		for(int i=0;i<52;i++)
		{
			if(lastFacedUp==-1)
			{
				break;
			}
			myCardArray[i]=facedUp.GetCard(i);
			facedUp.DeleteCard(i);
			myCardArray[i].FaceDown();
			lastFacedUp--;
		
		}
	}
   /* public boolean isSuitableForLeftToAim(PileOfCards FacedUp)
    {
    	Card lastCard = FacedUp.GetLastCard();
    	if(lastCard!=null)
    	{
    		if(LocationOfLastCard()==(-1))
        	{
    			if(lastCard.CardValue()==1)
        		{
        			myCardArray[0]=FacedUp.TakeTopCard();
        			if(FacedUp.LocationOfLastCard()!=-1)
        			{//I dont need this but stable
        				FacedUp.GetCard(FacedUp.LocationOfLastCard()).FaceUp();
        			}
        			return true;
        		}
        	}
    		
    	}
    }*/
	public boolean isSuitable(PileOfCards Input)// If the card suitable to carry to aim arrays
    {
    	Card lastCard = Input.GetLastCard();
    	if(lastCard!=null)
    	{
    	if(LocationOfLastCard()==(-1))//If aim is empty
    	{
    		if(lastCard.CardValue()==1)// The card is Ace
    		{
    			myCardArray[0]=Input.TakeTopCard();
    			if(Input.LocationOfLastCard()!=-1)
    			{//If there is any card in deck, then make the last car face up
    			Input.GetCard(Input.LocationOfLastCard()).FaceUp();
    			}
    			return true;
    		}
    	}
    	else if(this.GetLastCard().CardSuit()==lastCard.CardSuit())//The card has the same suit with cards which is in the aim array
    	{
       		if((this.GetLastCard().CardValue()+1)==lastCard.CardValue())
    		{
    			myCardArray[LocationOfLastCard()+1]=Input.TakeTopCard();//;
    			if(Input.LocationOfLastCard()!=-1)
    			{
    			Input.GetCard(Input.LocationOfLastCard()).FaceUp();
    			}
    			return true;
    		}
    	}
    	}
    	return false;
	}
	public void DeleteCard(int i)
	{
		myCardArray[i]=null;
	}
	public void PrintRow()
	{
		for(int i=0;i<7;i++)
		{
			if(myCardArray[i]==null)
			{
				System.out.print("   |");
				continue;
			}
			System.out.print(myCardArray[i].PrintCardForTable()+"|");
				
		
		}
		System.out.print('\n');
	}

}

