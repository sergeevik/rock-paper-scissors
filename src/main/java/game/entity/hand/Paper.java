package game.entity.hand;

import java.util.Collections;
import java.util.List;

public class Paper extends Hand {
    private List<HandForm> wins = Collections.singletonList(HandForm.ROCK);
    private List<HandForm> lose = Collections.singletonList(HandForm.SCISSORS);

    @Override
    List<HandForm> getWins() {
        return wins;
    }

    @Override
    List<HandForm> getLose() {
        return lose;
    }

    @Override
    HandForm getThis() {
        return HandForm.PAPER;
    }
}
