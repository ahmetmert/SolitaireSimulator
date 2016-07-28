import java.util.Scanner;
public class main {
	public static void CarryPile(PileOfCards firstPile,int locationOfFirstPile,PileOfCards secondPile,int locationOfSecondPile)
	{//Carry piles from stated location
		int i=locationOfFirstPile;
		int j=locationOfSecondPile;
		while(firstPile.GetCard(i)!=null)//Carry pile until it reaches to null
		{
			secondPile.SetCard(j,firstPile.GetCard(i));
			firstPile.DeleteCard(i);//delete carried cards
			i++;
			j++;
		}
		if(firstPile.LocationOfLastCard()!=-1)//Face the card up if it is the last card
		{
			firstPile.GetCard(firstPile.LocationOfLastCard()).FaceUp();
		}
	}
	static boolean CheckPileAsBlock(PileOfCards firstPile,PileOfCards secondPile)
	{//Checks the table if there is appropriate move
		if(firstPile.LocationOfLastCard()==-1)//If pile is null then terminate
		{
			return false;
		}
		int firstCardValue=firstPile.GetCard(firstPile.GetFirstTrueCardLocation()).CardValue();
		//Take first card value which is first faced up card in the pile
		if(secondPile.LocationOfLastCard()==-1)//If pile is empty
		{
			if(firstCardValue==13)
			{//If card is 'K'
				if(firstPile.GetFirstTrueCardLocation()!=0)
				{//If this 'K' is not on the first
				CarryPile(firstPile,firstPile.GetFirstTrueCardLocation(),secondPile,secondPile.LocationOfLastCard()+1);
				return true;
				}//this 'if' prevent game to carry 'K' again and again 
			}
			return false;
		}
		char lastCardSuit=secondPile.GetLastCard().CardSuit();
		int lastCardValue=secondPile.GetLastCard().CardValue();
		char firstCardSuit=firstPile.GetCard(firstPile.GetFirstTrueCardLocation()).CardSuit();
		if(((lastCardSuit=='S'|| lastCardSuit=='C')&&(firstCardSuit=='D'||firstCardSuit=='H'))||((lastCardSuit=='D'||lastCardSuit=='H')&&(firstCardSuit=='S'||firstCardSuit=='C')))
		{//If first Card is black and second one is red or fist is red and second one is black 
			if(firstCardValue+1==lastCardValue)
			{//Carry the pile
				CarryPile(firstPile,firstPile.GetFirstTrueCardLocation(),secondPile,secondPile.LocationOfLastCard()+1);
				return true;
			}
		}
		return false;
	}
	static boolean isSuitableForTable(PileOfCards leftDeck,PileOfCards[] table)//Checks the table if last card in leftDeck is appropriate for it
	{
		Card temp=leftDeck.GetCard(leftDeck.LocationOfLastCard());
		char tempSuit=temp.CardSuit();
		for(int i=0;i<7;i++)//Checks all columns
		{
			if(table[i].GetCard(0)==null)//If column is null and and Card is King
			{
				if(temp.CardValue()==13)
				{
					table[i].SetCard(0,leftDeck.TakeTopCard());
					return true;
				}
				return false;
			}
			char tableSuit=table[i].GetCard(table[i].LocationOfLastCard()).CardSuit();
			if(((tempSuit=='H'||tempSuit=='D')&&(tableSuit=='S'||tableSuit=='C'))||((tempSuit=='S'||tempSuit=='C')&&(tableSuit=='H'||tableSuit=='D')))
			{//If first Card is black and second one is red or fist is red and second one is black 
				if(temp.CardValue()+1==table[i].GetCard(table[i].LocationOfLastCard()).CardValue())
				{
					table[i].SetCard(table[i].LocationOfLastCard()+1, leftDeck.TakeTopCard());
					return true;
				}
			}
			
		}
		return false;
	}
	public static void PrintTable(PileOfCards[] table)
	{
		for(int i=0;i<15;i++)
		{//This for prints rows
			for(int j=0;j<7;j++)
			{//This for prints columns
				if(table[j].GetCard(i)==null)
				{
					System.out.print("|   ");
					continue;
				}
				System.out.print("|"+table[j].GetCard(i).PrintCardForTable());
			}
			System.out.print('\n');
		}
		
	}
	public static void main(String[] args)
	{
		int COUNT_OF_DECK=52;
		PileOfCards mainDeck = new PileOfCards(COUNT_OF_DECK);
		mainDeck.MakeDeck();//Create a deck and fill it
		mainDeck.Shuffle();
		PileOfCards[] tableArray=new PileOfCards[7];//Creates columns of my Card array
		PileOfCards[] aimArray=new PileOfCards[4];//This arrays for the aim array of the game
		for(int i=0;i<4;i++)
		{
			aimArray[i]=new PileOfCards(14);//Create and fill with nulls
		}
		for(int i=0;i<7;i++)
		{
			tableArray[i]= new PileOfCards(25);
		}
		int locationOfCard=0;
		for(int i=0;i<7;i++)//This two for allocate Cards in the table
		{
			for(int j=0;j<i+1;j++)
			{
			tableArray[i].SetCard(j, mainDeck.GetCard(locationOfCard));
			if(i==j)
			{
				tableArray[i].FaceUp(j);//Face the card up if it is last card in a column
			}
			locationOfCard++;
			}
		}             
		int countOfLeftPile=COUNT_OF_DECK-locationOfCard;
		PileOfCards leftPile=new PileOfCards(countOfLeftPile+2);//Creates pile located left 
		PileOfCards leftPileFacedUp = new PileOfCards(countOfLeftPile+2);
		int j=0;
		for(int i=locationOfCard;i<COUNT_OF_DECK;i++)//allocate cards to leftPile
		{
			leftPile.SetCard(j,mainDeck.GetCard(i));
			j++;
		}
		//@ This boolean variables for determine if game is stuck or not
		boolean leftToAimCheck=false;
		boolean leftToTableCheck=false;
		boolean tableToAimCheck=false;
		boolean tableInItselfCheck=false;
		boolean stuck=false;
		int stuckCounter=0;
		Scanner in=new Scanner(System.in);//For key inputs
		PrintGame(leftPileFacedUp,aimArray,tableArray);//Prints first status of game
		in.nextLine();//Pause the game until Enter key input
		while(!stuck)//!stuck|| isWin
		{
			if(leftToAimCheck||leftToTableCheck||tableToAimCheck||tableInItselfCheck)//If there is a move occurred, stuckCounter=0
			{
				stuckCounter=0;
			}
			if(LeftToAimCheck(leftPileFacedUp,aimArray))//Check if there is a possible move from left pile to aim 
			{
				leftToAimCheck=true;
				PrintGame(leftPileFacedUp,aimArray,tableArray);
				in.nextLine();
				continue;
			}
			if(leftPileFacedUp.GetLastCard()!=null)//If LeftPileFacedUp is not empty
			{
				if(isSuitableForTable(leftPileFacedUp,tableArray))//Check if leftFacedUp has a suitable card for table
				{
					leftToTableCheck=true;						   // If there is a move occur
					PrintGame(leftPileFacedUp,aimArray,tableArray);// Print the whole game
					in.nextLine();								   // and wait till Enter
					continue;
				}
			}
			if(TableToAimCheck(tableArray,aimArray))//Check lower table to aim deck 
			{
				tableToAimCheck=true;
				PrintGame(leftPileFacedUp,aimArray,tableArray);
				in.nextLine();
				continue;
			}
			if(CheckTableInItself(tableArray)) 
			{
				tableInItselfCheck=true;						// If there is a move occur
				PrintGame(leftPileFacedUp,aimArray,tableArray); // Print the whole game
				in.nextLine();									// and wait till Enter
				continue;
			}
			if(leftPile.GetCard(0)==null)
			{//If there is no card left in leftpile fill it back
				leftPile.fillBack(leftPileFacedUp);
			}
			leftPile.GiveThree(leftPileFacedUp);//Give three card from leftPile to leftPileFacedUp
			PrintGame(leftPileFacedUp,aimArray,tableArray);
			
			if(!(leftToAimCheck||leftToTableCheck||tableToAimCheck||tableInItselfCheck))
			{//there is no move occurred in this while loop 
				stuckCounter++;
			}
			
			if(stuckCounter>50)
			{//If there is no move occurred more than 50 times, Quit the loop
				stuck=true;
			}
			leftToAimCheck=false;
			leftToTableCheck=false;
			tableToAimCheck=false;
			tableInItselfCheck=false;
		}
		System.out.println("________________________________________");
		System.out.println("               GAME HAS STUCK                 ");
		System.out.print("       THERE IS NO POSSIBLE MOVE LEFT       ");
	}
	public static boolean LeftToAimCheck(PileOfCards leftPileFacedUp,
			PileOfCards[] aimArray)
	{
		for(int i=0;i<4;i++)//LeftToAimCheck
		{
			if(aimArray[i].isSuitable(leftPileFacedUp))
			{
				return true;
			}
		}
		return false;
	}
	public static boolean TableToAimCheck(PileOfCards[] tableArray,
			PileOfCards[] aimArray)
	{
		for(int i=0;i<7;i++)
		{
			for(int j1=0;j1<4;j1++)
			{
				if(aimArray[j1].isSuitable(tableArray[i]))
				{
					return true;
				}
			}
		}
		return false;
	}
	public static boolean CheckTableInItself(PileOfCards[] tableArray)
	{
		for(int i=0;i<7;i++)			//Checks each columns 
		{								//
			for(int j1=0;j1<7;j1++)		//
			{
				if(i==j1)
				{
					continue;
				}
				if(CheckPileAsBlock(tableArray[i],tableArray[j1]))
				{
					return true;
				}
			}
			
		}
		return false;
	}
	public static void PrintGame(PileOfCards left,PileOfCards[] aim,PileOfCards[] table)
	{
		System.out.println("________________________________________");
		if(left.LocationOfLastCard()==-1)//I leftFacedUp is empty
		{
			System.out.print("|   |   |   |");
		}
		if(left.LocationOfLastCard()==0) //LeftFacedUp has one card
		{
			System.out.print("|");
			System.out.print(left.GetCard(left.LocationOfLastCard()).PrintCardForTable());
			System.out.print("|   |   |");
		}
		if(left.LocationOfLastCard()==1)//leftFacedUp has two cards
		{
			System.out.print("|");
			System.out.print(left.GetCard(left.LocationOfLastCard()-1).PrintCardForTable());
			System.out.print("|");
			System.out.print(left.GetCard(left.LocationOfLastCard()).PrintCardForTable());
			System.out.print("|   |");
		}
		if(left.LocationOfLastCard()>1)
		{
			System.out.print("|");
			System.out.print(left.GetCard(left.LocationOfLastCard()-2).PrintCardForTable());
			System.out.print("|");
			System.out.print(left.GetCard(left.LocationOfLastCard()-1).PrintCardForTable());
			System.out.print("|");
			System.out.print(left.GetCard(left.LocationOfLastCard()).PrintCardForTable());
			System.out.print("|   |");
		}
		System.out.print("       ");
		for(int i=0;i<4;i++)//Print aims
		{
			System.out.print("|");
			if(aim[i].LocationOfLastCard()==-1)
			{
				System.out.print("   |");
				continue;
			}
			System.out.print(aim[i].GetCard(aim[i].LocationOfLastCard()).PrintCardForTable());
			System.out.print("|");
		}
		System.out.print("\n");
		System.out.print("\n");
		PrintTable(table);//Print lower arrays
	}
}
