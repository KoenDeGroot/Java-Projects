
package texasholdem;

import java.util.ArrayList;
import java.util.Collections;

public class TexasHoldEm {

    public static void main(String[] args) {
        Player bert = new Player("Bert");
        Player ernie = new Player("Ernie");
        
        Dealer dealer = new Dealer();
        dealer.shuffle();
          
        bert.addCards(dealer.dealHand());
        ernie.addCards(dealer.dealHand());
        
        System.out.println(bert);
        System.out.println("Bert has: " + bert.getHandRanking());
        
        System.out.println("");
        
        System.out.println(ernie);
        System.out.println("Ernie has: " + ernie.getHandRanking());
        
        System.out.println("");
        
        if (bert.compareTo(ernie) > 0) {
            System.out.println("Bert wins!");
        } else if (bert.compareTo(ernie) < 0) {
            System.out.println("Ernie wins!");
        } else {
            System.out.println("hands are equal");
        }
        
    }
    
}
