import edu.ntnu.idatt2003.cardgame.DeckOfCards;
import edu.ntnu.idatt2003.cardgame.PlayingCard;
import org.junit.Test;

import java.util.ArrayList;

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

    @Test
    public void checkTrueFlushTest(){
        DeckOfCards deck = new DeckOfCards();
        PlayingCard p1 = new PlayingCard('H',1);
        PlayingCard p2 = new PlayingCard('H',2);
        PlayingCard p3 = new PlayingCard('H',3);
        PlayingCard p4 = new PlayingCard('H',4);
        PlayingCard p5 = new PlayingCard('H',5);
        ArrayList<PlayingCard> playingCards = new ArrayList<>();
        playingCards.add(p1);
        playingCards.add(p2);
        playingCards.add(p3);
        playingCards.add(p4);
        playingCards.add(p5);

        assertEquals(true,deck.checkFlush(playingCards));

    }
    @Test
    public void checkFalseFlushTest(){
        DeckOfCards deck = new DeckOfCards();
        PlayingCard p1 = new PlayingCard('S',1);
        PlayingCard p2 = new PlayingCard('H',2);
        PlayingCard p3 = new PlayingCard('D',3);
        PlayingCard p4 = new PlayingCard('H',4);
        PlayingCard p5 = new PlayingCard('C',5);
        ArrayList<PlayingCard> playingCards = new ArrayList<>();
        playingCards.add(p1);
        playingCards.add(p2);
        playingCards.add(p3);
        playingCards.add(p4);
        playingCards.add(p5);

        assertEquals(false,deck.checkFlush(playingCards));
    }

}
