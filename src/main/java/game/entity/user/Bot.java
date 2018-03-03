package game.entity.user;

import game.entity.hand.Paper;
import game.entity.hand.Rock;
import game.entity.hand.Scissors;

public class Bot extends User {
    public void randomHand() {
        long random = System.currentTimeMillis() % 3;
        if (random == 0) {
            setHand(new Rock());
        } else if (random == 1) {
            setHand(new Paper());
        } else {
            setHand(new Scissors());
        }
    }
}
