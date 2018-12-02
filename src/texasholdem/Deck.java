/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Gebruiker
 */
public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        
        for(Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
    }
    
    public Card getCard() {
        if (cards.isEmpty()) {
            throw new IndexOutOfBoundsException("Not enough cards in the deck!");
        }
        return cards.remove(0);
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
    
    
    
}
