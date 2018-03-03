package game.entity.user;

import game.entity.Fight;
import game.entity.Result;
import game.entity.hand.Hand;
import lombok.Setter;
import lombok.experimental.Delegate;

public class User implements Fight<User> {
    @Setter
    private Hand hand;
    @Delegate(types = CounterImpl.class, excludes = SetScore.class)
    private Counter statistic = new CounterImpl();

    public Result fight(User anotherUser) {
        Result fight = hand.fight(anotherUser.hand);
        updateMyScore(fight);
        updateAnotherScore(fight, anotherUser);
        return fight;
    }

    private void updateAnotherScore(Result fight, User user) {
        updateScore(fight, user);
    }

    private void updateMyScore(Result fight) {
        updateScore(fight, this);
    }

    private void updateScore(Result result, User user) {
        if (result == Result.WIN) {
            statistic.win();
        } else if (result == Result.LOSE) {
            statistic.lose();
        } else if (result == Result.DRAW) {
            statistic.draw();
        }
    }
}
