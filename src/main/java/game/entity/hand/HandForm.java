package game.entity.hand;

import lombok.Getter;

public enum HandForm {
    ROCK(new Rock()),
    PAPER(new Paper()),
    SCISSORS(new Scissors());

    @Getter
    private Hand hand;

    HandForm(Hand hand) {
        this.hand = hand;
    }

    public static Hand getHandById(int id){
        return values()[id].getHand();
    }
}
