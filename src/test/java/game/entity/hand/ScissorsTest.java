package game.entity.hand;

import game.entity.Result;
import org.junit.Test;

import static game.entity.Result.DRAW;
import static game.entity.Result.LOSE;
import static game.entity.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

public class ScissorsTest {
    private final Scissors scissors = new Scissors();

    @Test
    public void scissorsFightRock() throws Exception {
        Result fight = scissors.fight(new Rock());
        assertThat(fight).isEqualTo(LOSE);
    }

    @Test
    public void scissorsFightScissors() throws Exception {
        Result fight = scissors.fight(new Scissors());
        assertThat(fight).isEqualTo(DRAW);
    }

    @Test
    public void scissorsFightPaper() throws Exception {
        Result fight = scissors.fight(new Paper());
        assertThat(fight).isEqualTo(WIN);
    }

}