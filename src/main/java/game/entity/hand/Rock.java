package game.entity.hand;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class Rock extends Hand {
    private List<HandForm> wins = Collections.singletonList(HandForm.SCISSORS);
    private List<HandForm> lose = Collections.singletonList(HandForm.PAPER);

    @Override
    HandForm getThis() {
        return HandForm.ROCK;
    }
}
