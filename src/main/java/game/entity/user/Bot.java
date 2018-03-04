package game.entity.user;

import game.entity.Result;
import game.entity.hand.Hand;
import game.entity.hand.Paper;
import game.entity.hand.Rock;
import game.entity.hand.Scissors;

public class Bot extends User {
    public Bot() {
        super("Bot");
    }

    private Hand randomHand() {
        long random = System.currentTimeMillis() % 3;
        if (random == 0) {
            return new Rock();
        } else if (random == 1) {
            return new Paper();
        } else {
            return new Scissors();
        }
    }

    @Override
    public Result fight(User anotherUser) {
        setHand(randomHand());
        return super.fight(anotherUser);
    }
}
