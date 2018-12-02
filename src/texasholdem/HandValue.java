/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Gebruiker
 */
public class HandValue {
    private HandRanking handRanking;
    private ArrayList<Card> cards;
    private ArrayList<Rank> mainRanks;
    private ArrayList<Rank> extraRanks;

    public HandValue(ArrayList<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Hand size is not 5!");
        } 
        this.cards = cards;
        
        handRanking = highestRanking(cards);
        
        setCards(cards);
    }

    public HandRanking getHandRanking() {
        return handRanking;
    }
    
    public void setCards(ArrayList<Card> cards) {
        ArrayList<Rank> mainRanks = new ArrayList<>();
        ArrayList<Rank> extraRanks = new ArrayList<>();
        
        ArrayList<Rank> ranks = getRanks(cards);
        Map<Rank,Integer> rankFrequency = rankFrequency(ranks);
            
        if (handRanking == HandRanking.PAIR || 
            handRanking == HandRanking.TWOPAIR || 
            handRanking == HandRanking.THREEOFAKIND ||
            handRanking == HandRanking.FOUROFAKIND) 
        {
            
            for (Card card : cards) {
                Rank rank = card.getRank();
                
                if (rankFrequency.get(rank) >= 2) {
                    mainRanks.add(rank);
                } else {
                    extraRanks.add(rank);
                }
            }
            
        } else {
            mainRanks = ranks;
        }
        
        this.mainRanks = mainRanks;
        this.extraRanks = extraRanks;

    }
    public ArrayList<Rank> getMainCards() {
        return mainRanks;
    }

    public ArrayList<Rank> getExtraCards() {
        return extraRanks;
    }
       
    public HandRanking highestRanking(ArrayList<Card> cards) {
        if (hasRoyalFlush(cards)) {
            return HandRanking.ROYALFLUSH;
        }
        if (hasStraightFlush(cards)) {
            return HandRanking.STRAIGHTFLUSH;
        }
        if (hasFourOfAKind(cards)) {
            return HandRanking.FOUROFAKIND;
        }
        if (hasFullHouse(cards)) {
            return HandRanking.FULLHOUSE;
        }
        if (hasFlush(cards)) {
            return HandRanking.FLUSH;
        }
        if (hasStraight(cards)) {
            return HandRanking.STRAIGHT;
        }
        if (hasThreeOfAKind(cards)) {
            return HandRanking.THREEOFAKIND;
        }
        if (hasTwoPair(cards)) {
            return HandRanking.TWOPAIR;
        }
        if (hasPair(cards)) {
            return HandRanking.PAIR;
        }
        return HandRanking.HIGHCARD;
        
    }
    
    public ArrayList<Rank> getRanks(ArrayList<Card> cards) {
        ArrayList<Rank> ranks = new ArrayList<>();
        
        for (Card card : cards) {
            ranks.add(card.getRank());
        }
        return ranks;
    }
    
    public ArrayList<Suit> getSuits(ArrayList<Card> cards) {
        ArrayList<Suit> suits = new ArrayList<>();
        
        for (Card card : cards) {
            suits.add(card.getSuit());
        }
        
        return suits;
    }
    
    public Map<Rank,Integer> rankFrequency(ArrayList<Rank> ranks) {
        Set<Rank> uniqueRanks = new HashSet(ranks);
        
        Map<Rank,Integer> rankFrequency = new HashMap<>();
        
        for (Rank rank : uniqueRanks) {
            rankFrequency.put(rank,Collections.frequency(ranks, rank));
        }
        return rankFrequency;
    }
    
    public boolean hasHighCard(ArrayList<Card> cards) {
        return true;
    }
    
    public boolean hasPair(ArrayList<Card> cards) {
        ArrayList<Rank> ranks = getRanks(cards);
        
        Map<Rank,Integer> rankFrequency = rankFrequency(ranks);
        
        return Collections.max(rankFrequency.values()) >= 2;      
    }
    
    public boolean hasTwoPair(ArrayList<Card> cards) {
        ArrayList<Rank> ranks = getRanks(cards);
        
        Map<Rank,Integer> rankFrequency = rankFrequency(ranks);
        
        int pairCount = 0;
        
        for (int frequency : rankFrequency.values()) {
            if (frequency > 1) {
                pairCount++;
            }
        }
        return pairCount >= 2;
    }
    
    public boolean hasThreeOfAKind(ArrayList<Card> cards) {
        ArrayList<Rank> ranks = getRanks(cards);
        
        Map<Rank,Integer> rankFrequency = rankFrequency(ranks);
        
        return Collections.max(rankFrequency.values()) >= 3; 
    }
    
    public boolean hasStraight(ArrayList<Card> cards) {
        ArrayList<Rank> ranks = getRanks(cards);
        
        Map<Rank,Integer> rankFrequency = rankFrequency(ranks);
        
        if (rankFrequency.size() != 5) {
            return false;
        }
        
        Collections.sort(ranks);
        
        return ranks.get(0).getValue() + 4 == ranks.get(4).getValue();
    }
    
    public boolean hasFlush(ArrayList<Card> cards) {
        ArrayList<Suit> suits = getSuits(cards);
        
        return Collections.frequency(suits, suits.get(0)) >= 5;
    }
    
    public boolean hasFullHouse(ArrayList<Card> cards) {
        ArrayList<Rank> ranks = getRanks(cards);
        
        Map<Rank,Integer> rankFrequency = rankFrequency(ranks);
        
        return Collections.min(rankFrequency.values()) == 2 && Collections.max(rankFrequency.values()) == 3;
    }
    
    public boolean hasFourOfAKind(ArrayList<Card> cards) {
        ArrayList<Rank> ranks = getRanks(cards);
        
        Map<Rank,Integer> rankFrequency = rankFrequency(ranks);
        
        return Collections.max(rankFrequency.values()) >= 4; 
    }
    
    public boolean hasStraightFlush(ArrayList<Card> cards) {
        return hasStraight(cards) && hasFlush(cards);
    }
    
    public boolean hasRoyalFlush(ArrayList<Card> cards) {
        if (hasStraightFlush(cards)) {
            ArrayList<Rank> ranks = getRanks(cards);
            
            return ranks.contains(Rank.ACE);
        }
        return false;
    }
}
