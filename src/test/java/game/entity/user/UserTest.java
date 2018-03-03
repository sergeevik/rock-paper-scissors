package game.entity.user;

import game.entity.Result;
import game.entity.hand.Hand;
import game.entity.hand.Paper;
import game.entity.hand.Rock;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserTest {
    private User firstUser;
    private User secondUser;


    @Before
    public void setUp() throws Exception {
        firstUser = new User();
        secondUser = new User();

    }

    @Test
    public void fightMock() throws Exception {
        Hand firstHand = mock(Hand.class);
        Hand secondHand = mock(Hand.class);
        firstUser.setHand(firstHand);
        secondUser.setHand(secondHand);

        firstUser.fight(secondUser);

        verify(firstHand, times(1)).fight(secondHand);

        secondUser.fight(firstUser);

        verify(secondHand, times(1)).fight(firstHand);
    }
}