package game.entity.hand;

import lombok.Getter;

import java.util.List;

@Getter
public class Paper extends Hand {
    private final List<HandForm> wins = immutableList(HandForm.ROCK);
    private final List<HandForm> lose = immutableList(HandForm.SCISSORS);

    @Override
    HandForm getForm() {
        return HandForm.PAPER;
    }
}
