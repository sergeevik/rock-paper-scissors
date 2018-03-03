package game.entity;

public enum Result {
    WIN,
    LOSE,
    DRAW;

    public Result revert() {
        if (this == WIN) {
            return LOSE;
        } else if (this == LOSE) {
            return WIN;
        } else {
            return DRAW;
        }
    }
}
