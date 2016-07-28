
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PileOfCardsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSwap() {
		Card newCard=new Card('S',5,false);
		Card newCard2=new Card('D',2,true);
		PileOfCards pile=new PileOfCards(2);
		Card[] cardArray=new Card[2];
		cardArray[0]=newCard;
		cardArray[1]=newCard2;
		
		pile.SetCard(0, newCard);
		pile.SetCard(1,newCard2);
		pile.Swap(cardArray, 0, 1);
		assertEquals(cardArray[1].CardSuit(),'S');
		//fail("Not yet implemented");
	}

	@Test
	public void testTakeTopCard() {
		Card newCard=new Card('S',5,false);
		Card newCard2=new Card('D',2,true);
		PileOfCards pile=new PileOfCards(2);
		pile.SetCard(0, newCard);
		pile.SetCard(1,newCard2);
		assertEquals(pile.TakeTopCard().CardValue(),2);
		//fail("Not yet implemented");
	}

	@Test
	public void testGetCard() {
		Card newCard=new Card('S',5,false);
		Card newCard2=new Card('D',2,true);
		PileOfCards pile=new PileOfCards(2);
		pile.SetCard(0, newCard);
		pile.SetCard(1,newCard2);
		assertEquals(pile.GetCard(0).CardStatus(),false);
		//fail("Not yet implemented");
	}

	@Test
	public void testFaceUp() {
		Card newCard=new Card('S',5,false);
		Card newCard2=new Card('D',2,true);
		PileOfCards pile=new PileOfCards(2);
		pile.SetCard(0, newCard);
		pile.SetCard(1,newCard2);
		newCard.FaceUp();
		assertEquals(newCard.CardStatus(),true);
		//fail("Not yet implemented");
	}

	@Test
	public void testLocationOfLastCard() {
		Card newCard=new Card('S',5,true);
		Card newCard2=new Card('D',2,true);
		Card newCard3=new Card('H',12,true);
		PileOfCards pile=new PileOfCards(4);
		pile.SetCard(0, newCard);
		pile.SetCard(1,newCard2);
		pile.SetCard(2,newCard3);
		assertEquals(pile.LocationOfLastCard(),2);
		//fail("Not yet implemented");
	}

	@Test
	public void testGetFirstTrueCardLocation() {
		Card newCard=new Card('S',5,false);
		Card newCard2=new Card('D',2,true);
		Card newCard3=new Card('H',12,true);
		PileOfCards pile=new PileOfCards(4);
		pile.SetCard(0, newCard);
		pile.SetCard(1,newCard2);
		pile.SetCard(2,newCard3);
		assertEquals(pile.GetFirstTrueCardLocation(),1);
		//fail("Not yet implemented");
	}

	@Test
	public void testGetLastCard() {
		Card newCard=new Card('S',5,false);
		Card newCard2=new Card('D',2,true);
		Card newCard3=new Card('H',12,true);
		PileOfCards pile=new PileOfCards(4);
		pile.SetCard(0, newCard);
		pile.SetCard(1,newCard2);
		pile.SetCard(2,newCard3);
		assertEquals(pile.GetLastCard().CardValue(),12);
		//fail("Not yet implemented");
	}

	@Test
	public void testDeleteCard() {
		Card newCard=new Card('S',5,false);
		Card newCard2=new Card('D',2,true);
		Card newCard3=new Card('H',12,true);
		PileOfCards pile=new PileOfCards(4);
		pile.SetCard(0, newCard);
		pile.SetCard(1,newCard2);
		pile.SetCard(2,newCard3);
		pile.DeleteCard(1);
		assertEquals(pile.GetCard(1),null);
		//fail("Not yet implemented");
	}
	
}
