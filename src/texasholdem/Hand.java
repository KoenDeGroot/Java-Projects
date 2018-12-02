/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Gebruiker
 */
public class Hand implements Comparable<Hand>{
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }
    
    public void addCards(ArrayList<Card> cards) {
        hand.addAll(cards);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public HandRanking getHandRanking() {
        return new HandValue(hand).getHandRanking();
    }

    @Override
    public int compareTo(Hand hand) {
        HandRanking handRanking1 = this.getHandRanking();
        HandRanking handRanking2 = hand.getHandRanking();
        
        if (handRanking1.compareTo(handRanking2) == 0) {
            ArrayList<Rank> mainRanks1 = new HandValue(this.getHand()).getMainCards();
            ArrayList<Rank> mainRanks2 = new HandValue(hand.getHand()).getMainCards();
            
            Collections.sort(mainRanks1,Collections.reverseOrder());
            Collections.sort(mainRanks2,Collections.reverseOrder());
 
            if (handRanking1 == HandRanking.HIGHCARD || 
                handRanking1 == HandRanking.STRAIGHT || 
                handRanking1 == HandRanking.FLUSH ||
                handRanking1 == HandRanking.STRAIGHTFLUSH ||
                handRanking1 == HandRanking.ROYALFLUSH)
            {
                for (int i=0;i<mainRanks1.size();i++) {
                    if (mainRanks1.get(i).compareTo(mainRanks2.get(i)) > 0) {
                        return 1;
                    } else if (mainRanks1.get(i).compareTo(mainRanks2.get(i)) < 0) {
                        return -1;
                    }
                }
                return 0;
                
            } 
            ArrayList<Rank> extraRanks1 = new HandValue(this.getHand()).getExtraCards();
            ArrayList<Rank> extraRanks2 = new HandValue(hand.getHand()).getExtraCards();
            
            Collections.sort(extraRanks1,Collections.reverseOrder());
            Collections.sort(extraRanks2,Collections.reverseOrder());
            
            if (handRanking1 == HandRanking.PAIR || 
                handRanking1 == HandRanking.TWOPAIR || 
                handRanking1 == HandRanking.THREEOFAKIND ||
                handRanking1 == HandRanking.FOUROFAKIND)
            {
                for (int i=0;i<mainRanks1.size();i++) {
                    if (mainRanks1.get(i).compareTo(mainRanks2.get(i)) > 0) {
                        return 1;
                    } else if (mainRanks1.get(i).compareTo(mainRanks2.get(i)) < 0) {
                        return -1;
                    }
                }
                
                for (int i=0;i<extraRanks1.size();i++) {
                    if (extraRanks1.get(i).compareTo(extraRanks2.get(i)) > 0) {
                        return 1;
                    } else if (extraRanks1.get(i).compareTo(extraRanks2.get(i)) < 0) {
                        return -1;
                    }
                }
                return 0;
            } 
            if (handRanking1 == HandRanking.FULLHOUSE) {
                Set<Rank> uniqueRanks1 = new HashSet(mainRanks1);
                Set<Rank> uniqueRanks2 = new HashSet(mainRanks2);
                
                for (Rank rank1 : uniqueRanks1) {
                    if (Collections.frequency(mainRanks1, rank1) == 3) {
                        for (Rank rank2 : uniqueRanks2) {
                            if (Collections.frequency(mainRanks2, rank2) == 3) {
                                if (rank1.compareTo(rank2) > 0) {
                                    return 1;
                                } else if (rank1.compareTo(rank2) < 0) {
                                    return -1;
                                }
                            }
                        }
                    }
                }
                
                for (Rank rank1 : uniqueRanks1) {
                    if (Collections.frequency(mainRanks1, rank1) == 2) {
                        for (Rank rank2 : uniqueRanks2) {
                            if (Collections.frequency(mainRanks2, rank2) == 2) {
                                if (rank1.compareTo(rank2) > 0) {
                                    return 1;
                                } else if (rank1.compareTo(rank2) < 0) {
                                    return -1;
                                }
                            }
                        }
                    }
                }
                return 0;
            }
           
        }
        return handRanking1.compareTo(handRanking2);
    }

    @Override
    public String toString() {
        return "" + hand;
    }
    
    
    
    
}
