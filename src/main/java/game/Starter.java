package game;

import game.view.console.ConsoleView;
import game.view.javafx.JavaFx;

public class Starter {
    public static void main(String[] args) {
        if (args.length == 0){
            new JavaFx().startFx(args);
        }else {
            new ConsoleView().play();
        }
    }
}
