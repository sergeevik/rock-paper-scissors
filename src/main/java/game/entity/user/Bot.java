package game.entity.user;

import game.entity.hand.Hand;
import game.entity.hand.HandForm;

import java.util.Random;

public class Bot extends User {
    private final Random random = new Random();

    public Bot() {
        super("Bot");
    }

    private Hand randomHand() {
        int value = random.nextInt(3);
        return HandForm.getHandById(value);
    }

    @Override
    protected void updateAnotherHand() {
        setHand(randomHand());
    }
}
