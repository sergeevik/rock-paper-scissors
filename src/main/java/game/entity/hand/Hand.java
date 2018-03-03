package game.entity.hand;

import game.entity.Fight;
import game.entity.Result;

import java.util.List;
import java.util.Objects;

public abstract class Hand implements Fight<Hand> {
    public Result fight(Hand action) {
        if (getWins().contains(action.getThis())) {
            return Result.WIN;
        }else if (getLose().contains(action.getThis())){
            return Result.LOSE;
        }else {
            return Result.DRAW;
        }
    }

    abstract List<HandForm> getWins();
    abstract List<HandForm> getLose();
    abstract HandForm getThis();

    @Override
    public int hashCode() {
        return getThis().ordinal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Hand hand = (Hand) o;

        return Objects.equals(getThis(), hand.getThis());
    }

    @Override
    public String toString() {
        return "Hand{form=" + getThis().name() + "}";
    }
}
