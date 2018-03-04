package game.entity.user;

import game.entity.Fight;
import game.entity.Result;
import game.entity.hand.Hand;
import lombok.*;
import lombok.experimental.Delegate;

public class User implements Fight<User> {
    @Setter
    private Hand hand;

    @Getter
    private String name;

    @Delegate(types = CounterImpl.class, excludes = SetScore.class)
    private Counter statistic = new CounterImpl();

    public User(String name) {
        this.name = name;
    }

    public Result fight(User anotherUser) {
        Result fight = this.hand.fight(anotherUser.hand);
        updateMyScore(fight);
        updateAnotherScore(fight, anotherUser);
        return fight;
    }

    private void updateAnotherScore(Result result, User user) {
        Result anotherResult = result.revert();
        updateScore(anotherResult, user);
    }

    private void updateMyScore(Result result) {
        updateScore(result, this);
    }

    private void updateScore(Result result, User user) {
        if (result == Result.WIN) {
            user.statistic.win();
        } else if (result == Result.LOSE) {
            user.statistic.lose();
        } else if (result == Result.DRAW) {
            user.statistic.draw();
        }
    }

    public String scoreToString() {
        return statistic.toString();
    }
}
