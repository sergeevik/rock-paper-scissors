package game.entity.hand;

import game.entity.Result;
import org.junit.Test;

import static game.entity.Result.DRAW;
import static game.entity.Result.LOSE;
import static game.entity.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

public class RockTest {
    private final Rock rock = new Rock();

    @Test
    public void rockFightRock() throws Exception {
        Result fight = rock.fight(new Rock());
        assertThat(fight).isEqualTo(DRAW);
    }

    @Test
    public void rockFightScissors() throws Exception {
        Result fight = rock.fight(new Scissors());
        assertThat(fight).isEqualTo(WIN);
    }

    @Test
    public void rockFightPaper() throws Exception {
        Result fight = rock.fight(new Paper());
        assertThat(fight).isEqualTo(LOSE);

    }

}