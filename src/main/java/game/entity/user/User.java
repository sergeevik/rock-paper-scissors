package game.entity.user;

import game.entity.Fight;
import game.entity.Result;
import game.entity.hand.Hand;
import lombok.Setter;
import lombok.experimental.Delegate;

public class User implements Fight<User> {
    @Setter
    private Hand hand;
    @Delegate(types = CounterImpl.class)
    private Counter statistic = new CounterImpl();

    public Result fight(User anotherUser) {
        return hand.fight(anotherUser.hand);
    }
}
