package game.entity.hand;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class Scissors extends Hand {
    private List<HandForm> wins = Collections.singletonList(HandForm.PAPER);
    private List<HandForm> lose = Collections.singletonList(HandForm.ROCK);

    @Override
    HandForm getThis() {
        return HandForm.SCISSORS;
    }
}
