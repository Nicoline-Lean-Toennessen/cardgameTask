import edu.ntnu.idatt2003.cardgame.DeckOfCards;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckOfCardsTest {

    @Test
    public void count52Cards(){
        DeckOfCards deck = new DeckOfCards();
        assertEquals(52,deck.getCards().size());
    }

    @Test
    public void countHand(){
        DeckOfCards deck = new DeckOfCards();
        int handSize = deck.dealHand(20).size();
        assertEquals(20,handSize);
    }

}
