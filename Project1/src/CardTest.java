import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CardTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFaceUp() {
		Card newCard=new Card('S',5,false);
		newCard.FaceUp();
		assertEquals(newCard.CardStatus(),true);
		//	fail("Not yet implemented");
	}

	@Test
	public void testFaceDown() {
		Card newCard=new Card('S',5,true);
		newCard.FaceDown();
		assertEquals(newCard.CardStatus(),false);
		//fail("Not yet implemented");
	}

	@Test
	public void testCardStatus() {
		Card newCard=new Card('S',5,true);
		assertEquals(newCard.CardStatus(),true);
		//	fail("Not yet implemented");
	}

	@Test
	public void testCardValue() {
		Card newCard=new Card('S',5,true);
		assertEquals(newCard.CardValue(),5);
		//	fail("Not yet implemented");
	}

	@Test
	public void testCardCopy() {
		Card newCard=new Card('S',5,true);
		Card newCard2=new Card('D',8,false);
		newCard.CardCopy(newCard2);
		assertEquals(newCard.CardStatus(),false);
		assertEquals(newCard.CardValue(),8);
		assertEquals(newCard.CardSuit(),'D');
		//fail("Not yet implemented");
	}

}
