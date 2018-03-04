package game.view.console;

import game.entity.Result;
import game.entity.hand.HandForm;

import java.util.Scanner;

import static game.view.console.Constants.*;

public class ConsoleView {
    private final static Scanner reader = new Scanner(System.in);
    private ConsoleController controller = new ConsoleController();

    private void printHead() {
        printLine("===============================================================");
        printLine("==================== Rock Paper Scissors ======================");
        printLine("===============================================================");
    }

    private void initGame() {
        controller.initFirstPlayer(requireUserName());
        int opponent = choiceOpponent();

        if (opponent == BOT){
            controller.playWithBot();
        }else if(opponent == USER){
            controller.playWithUser(requireUserName());
        }

        controller.initGame();
    }

    private void mainLoop() {
        int chooseMenu = -1;
        while (chooseMenu != EXIT) {
            showMenu();
            chooseMenu = readInt();
            menuChoiceReact(chooseMenu);
        }
    }

    private void showMenu() {
        String menu = NEW_GAME + " - Новая игра\n" +
                CONTINUE + " - Продолжить\n" +
                PRINT_SCORE + " - Показать счет\n" +
                EXIT + " - Выход";
        printLine(menu);
    }

    private void menuChoiceReact(int chooseMenu) {
        if (chooseMenu == NEW_GAME){
            controller.restart();
        }else if(chooseMenu == PRINT_SCORE){
            printScore();
        }else if (chooseMenu == EXIT){
            printLine("================ Thanks for game =====================");
        }

        if (chooseMenu == NEW_GAME || chooseMenu == CONTINUE){
            fight();
        }
    }

    private void showHandChoice() {
        String handForm = ROCK + " - Камень\n" +
                PAPER + " - Ножницы\n" +
                SCISSORS + " - Бумага";
        printLine(handForm);
    }

    private void reactChoiceHand(int handForm) {
        if (handForm == ROCK){
            controller.firstChoice(HandForm.ROCK);
        }else if (handForm == PAPER){
            controller.firstChoice(HandForm.PAPER);
        }else if (handForm == SCISSORS){
            controller.firstChoice(HandForm.SCISSORS);
        }
    }

    private void printScore() {
        printLine("=================== SCORE ===================");
        printLine(controller.firstPlayerScore());
        printLine(controller.secondPlayerScore());
        printLine("==============================================");
    }

    private void fight() {
        showHandChoice();
        int handForm = readInt();
        reactChoiceHand(handForm);
        Result fight = controller.fight();
        printLine(fight.name());
    }

    private int choiceOpponent() {
        printLine("Play With:");
        printLine(BOT + " - bot");
        printLine(USER + " - another player");
        return readInt();
    }

    private String requireUserName() {
        printLine("Write player name:");
        return readLine();
    }

    private String readLine(){
        return reader.nextLine();
    }

    private int readInt(){
        return reader.nextInt();
    }

    private void printLine(String line){
        System.out.println(line);
    }

    public void play() {
        printHead();
        initGame();
        mainLoop();
    }
}
