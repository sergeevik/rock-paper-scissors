package game.entity.hand;

import game.entity.Fight;
import game.entity.Result;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Hand implements Fight<Hand> {

    List<HandForm> immutableList(HandForm... elements){
        return Collections.unmodifiableList(Arrays.asList(elements));
    }

    public Result fight(Hand action) {
        if (getWins().contains(action.getForm())) {
            return Result.WIN;
        }else if (getLose().contains(action.getForm())){
            return Result.LOSE;
        }else {
            return Result.DRAW;
        }
    }
    abstract List<HandForm> getWins();
    abstract List<HandForm> getLose();
    abstract HandForm getForm();

    @Override
    public int hashCode() {
        return getForm().ordinal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hand hand = (Hand) o;

        return Objects.equals(getForm(), hand.getForm());
    }

    @Override
    public String toString() {
        return "Hand{form=" + getForm().name() + "}";
    }
}
