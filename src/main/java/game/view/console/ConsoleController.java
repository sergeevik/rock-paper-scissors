package game.view.console;

import game.controller.GameController;
import game.entity.Result;
import game.entity.hand.HandForm;
import game.entity.user.Bot;
import game.entity.user.User;

class ConsoleController {
    private User first;
    private User second;
    private GameController game;


    void initFirstPlayer(String userName) {
        first = new User(userName);
    }

    void playWithBot() {
        second = new Bot();
    }

    void playWithUser(String userName) {
        second = new User(userName);
    }

    void restart() {
        first.reset();
        second.reset();
    }

    private String playerScore(User user) {
        return user.getName() + ": " + user.scoreToString();
    }

    String firstPlayerScore() {
        return playerScore(first);
    }

    String secondPlayerScore() {
        return playerScore(second);
    }

    void firstChoice(HandForm form) {
        first.setHand(form.getHand());
    }

    Result fight() {
        return game.fight();
    }

    void initGame() {
        game = new GameController(first, second);
    }
}
