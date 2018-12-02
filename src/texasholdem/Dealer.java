/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

import java.util.ArrayList;

/**
 *
 * @author Gebruiker
 */
public class Dealer {
    private Deck deck;

    public Dealer() {
        deck = new Deck();
    }
    
    public ArrayList<Card> dealHand() {
        return deal(5);
    }
    
    public ArrayList<Card> deal(int amountOfCards) {
        ArrayList<Card> cards = new ArrayList<>();
        
        for (int i=0;i<amountOfCards;i++) {
            cards.add(deck.getCard());
        }
        
        return cards;
    }
    
    public ArrayList<Card> flop() {
        return deal(3);
    }
    
    public ArrayList<Card> turn() {
        return deal(1);
    }
    
    public ArrayList<Card> river() {
        return deal(1);
    }
    
    public void shuffle() {
        deck.shuffle();
    }

    public Deck getDeck() {
        return deck;
    }
    
    
    
    
}
