package game.entity.hand;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class Paper extends Hand {
    private List<HandForm> wins = Collections.singletonList(HandForm.ROCK);
    private List<HandForm> lose = Collections.singletonList(HandForm.SCISSORS);

    @Override
    HandForm getThis() {
        return HandForm.PAPER;
    }
}
