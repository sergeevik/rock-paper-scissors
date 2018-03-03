package game.entity.hand;

import game.entity.Result;
import org.junit.Test;

import static game.entity.Result.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PaperTest {
    private final Paper paper = new Paper();
    
    @Test
    public void paperFightRock() throws Exception {
        Result fight = paper.fight(new Rock());
        assertThat(fight).isEqualTo(WIN);
    }

    @Test
    public void paperFightScissors() throws Exception {
        Result fight = paper.fight(new Scissors());
        assertThat(fight).isEqualTo(LOSE);
    }

    @Test
    public void paperFightPaper() throws Exception {
        Result fight = paper.fight(new Paper());
        assertThat(fight).isEqualTo(DRAW);

    }
}