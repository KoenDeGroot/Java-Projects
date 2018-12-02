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
public enum HandRanking implements Comparable<HandRanking> {
    HIGHCARD(0,"high card"),
    PAIR(1,"pair"),
    TWOPAIR(2,"two pair"),
    THREEOFAKIND(3,"three of a kind"),
    STRAIGHT(4,"straight"),
    FLUSH(5,"flush"),
    FULLHOUSE(6,"full house"),
    FOUROFAKIND(7,"four of a kind"),
    STRAIGHTFLUSH(8,"straight flush"),
    ROYALFLUSH(9,"royal flush");
    
    private int value;
    private String name;

    private HandRanking(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    

    
    
    
}
