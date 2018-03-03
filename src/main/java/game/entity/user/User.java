package game.entity.user;

import game.entity.Fight;
import game.entity.Result;
import game.entity.hand.Hand;

public class User implements Fight<User> {
    private Hand hand;

    public Result fight(User anotherUser) {
        return hand.fight(anotherUser.hand);
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
