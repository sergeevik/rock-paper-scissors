package game.entity.hand;

import java.util.Collections;
import java.util.List;

public class Scissors extends Hand {
    private List<HandForm> wins = Collections.singletonList(HandForm.PAPER);
    private List<HandForm> lose = Collections.singletonList(HandForm.ROCK);

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
        return HandForm.SCISSORS;
    }
}
