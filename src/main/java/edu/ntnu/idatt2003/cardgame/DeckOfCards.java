package edu.ntnu.idatt2003.cardgame;

import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
    private final char[] suit = {'S','H','D','C'};

    ArrayList<PlayingCard> cards = new ArrayList<>();

    //Making the deck of cards with 13 cards per shape
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
        if(cards.size()<n){
            throw new IllegalArgumentException("no can do");
        }
        ArrayList<PlayingCard> hand = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            int r = rand.nextInt(52);
            while(hand.contains(cards.get(r))){
                r = rand.nextInt(52);
            }
            PlayingCard c = cards.get(r);
            hand.add(c);
        }
        for(PlayingCard c : hand){
            cards.remove(c);
        }

        return hand;
    }

}
