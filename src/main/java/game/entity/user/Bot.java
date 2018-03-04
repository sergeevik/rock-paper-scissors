package game.entity.user;

import game.entity.Result;
import game.entity.hand.*;

public class Bot extends User {
    public Bot() {
        super("Bot");
    }

    private Hand randomHand() {
        int random = (int)System.currentTimeMillis() % 3;
        return HandForm.getHandById(random);
    }

    @Override
    public Result fight(User anotherUser) {
        setHand(randomHand());
        return super.fight(anotherUser);
    }
}
