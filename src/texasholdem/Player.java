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
public class Player implements Comparable<Player>{
    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
    
    public void addCards(ArrayList<Card> cards) {
        hand.addCards(cards);
    }
    
    public HandRanking getHandRanking() {
        return hand.getHandRanking();
    }

    @Override
    public String toString() {
        return name + ":" + hand;
    }

    @Override
    public int compareTo(Player player) {
        return hand.compareTo(player.hand);
    }
    
    
    
    
}
