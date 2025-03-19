package edu.ntnu.idatt2003.cardgame;

import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
    private final char[] suit = {'S','H','D','C'};

    ArrayList<PlayingCard> cards = new ArrayList<>();
    ArrayList<PlayingCard> hand = new ArrayList<>();

    public DeckOfCards(){
        for(int i = 1; i<=13; i++){
            cards.add(new PlayingCard(suit[0],i));
            cards.add(new PlayingCard(suit[1],i));
            cards.add(new PlayingCard(suit[2],i));
            cards.add(new PlayingCard(suit[3],i));
        }
    }
    public ArrayList<PlayingCard> getCards(){
        return cards;
    }

    public ArrayList<PlayingCard> dealHand(int n){
        if (cards.size() < n) {
            throw new IllegalArgumentException("You don't have enough cards to deal a hand.");
        }

        hand.clear();

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int r = rand.nextInt(cards.size());
            PlayingCard c = cards.get(r);
            hand.add(c);
            cards.remove(r);
        }

        return hand;
    }

    public ArrayList<PlayingCard> getDealtCards(){
        return hand;
    }

    public Boolean checkFlush(ArrayList<PlayingCard> handCards) {
        char suit = handCards.get(0).getSuit();
        for (int i = 1; i < handCards.size(); i++) {
            if (handCards.get(i).getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

}
