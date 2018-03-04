package game.entity.hand;

import lombok.Getter;

import java.util.List;

@Getter
public class Rock extends Hand {
    private final List<HandForm> wins = immutableList(HandForm.SCISSORS);
    private final List<HandForm> lose = immutableList(HandForm.PAPER);

    @Override
    HandForm getForm() {
        return HandForm.ROCK;
    }
}
