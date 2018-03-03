package game.entity.hand;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Rock extends Hand {
    private List<HandForm> wins = Collections.singletonList(HandForm.SCISSORS);
    private List<HandForm> lose = Collections.singletonList(HandForm.PAPER);

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
        return HandForm.ROCK;
    }
}
