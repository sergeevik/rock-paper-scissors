package game.entity.hand;

import lombok.Getter;

import java.util.List;

@Getter
public class Scissors extends Hand {
    private final List<HandForm> wins = immutableList(HandForm.PAPER);
    private final List<HandForm> lose = immutableList(HandForm.ROCK);

    @Override
    HandForm getForm() {
        return HandForm.SCISSORS;
    }
}
