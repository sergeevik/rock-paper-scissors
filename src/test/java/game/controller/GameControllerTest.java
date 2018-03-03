package game.controller;

import game.entity.hand.*;
import game.entity.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    private User first;
    @Mock
    private User second;

    private GameController game;

    @Before
    public void setUp() throws Exception {
        game = new GameController(first, second);
    }

    @Test
    public void restart() throws Exception {
        game.restart();
        verify(first, times(1)).reset();
        verify(second, times(1)).reset();
    }

    @Test
    public void firstChoiceTest() throws Exception {
        firstChoice(HandForm.ROCK, new Rock());
        firstChoice(HandForm.SCISSORS, new Scissors());
        firstChoice(HandForm.PAPER, new Paper());
    }

    @Test
    public void secondChoiceTest() throws Exception {
        secondChoice(HandForm.ROCK, new Rock());
        secondChoice(HandForm.SCISSORS, new Scissors());
        secondChoice(HandForm.PAPER, new Paper());

    }

    @Test
    public void fightTest() throws Exception {
        game.fight();
        verify(first, times(1)).fight(second);
    }

    private void secondChoice(HandForm form, Hand hand) {
        game.secondChoice(form);
        verify(second, times(1)).setHand(hand);
    }

    private void firstChoice(HandForm form, Hand hand) {
        game.firstChoice(form);
        verify(first, times(1)).setHand(hand);
    }




}