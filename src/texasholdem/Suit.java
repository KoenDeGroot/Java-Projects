/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

/**
 *
 * @author Gebruiker
 */
public enum Suit {
    Hearts("♥"),Spades("♠"),Diamonds("♦"),Clubs("♣");
    
    private String symbol;

    private Suit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
    
    
}
