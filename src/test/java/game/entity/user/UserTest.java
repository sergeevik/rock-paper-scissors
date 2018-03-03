package game.entity.user;

import game.entity.Result;
import game.entity.hand.Hand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    private User firstUser;
    private User secondUser;

    @Mock
    private Hand firstHand;
    @Mock
    private Hand secondHand;

    @Before
    public void setUp() throws Exception {
        firstUser = new User("Boby");
        secondUser = new User("Mike");
        firstUser.setHand(firstHand);
        secondUser.setHand(secondHand);
        when(firstHand.fight(secondHand)).thenReturn(Result.WIN);
        when(secondHand.fight(firstHand)).thenReturn(Result.LOSE);
    }

    @Test
    public void fightMock() throws Exception {
        firstUser.fight(secondUser);
        verify(firstHand, times(1)).fight(secondHand);

        secondUser.fight(firstUser);
        verify(secondHand, times(1)).fight(firstHand);
    }

    @Test
    public void statisticTest() throws Exception {
        firstUser.fight(secondUser);
        checkStatistic(firstUser, 1, 0, 0);
        checkStatistic(secondUser, 0, 1, 0);
    }

    private void checkStatistic(User user, int win, int lose, int draw) {
        assertThat(user.getWinCount()).isEqualTo(win);
        assertThat(user.getLoseCount()).isEqualTo(lose);
        assertThat(user.getDrawCount()).isEqualTo(draw);
    }
}