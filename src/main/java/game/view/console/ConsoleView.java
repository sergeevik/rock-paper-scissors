package game.view.console;

import game.controller.GameController;
import game.entity.Result;
import game.entity.hand.HandForm;
import game.entity.user.Bot;
import game.entity.user.User;

import java.util.Scanner;

import static game.view.console.Constants.*;

public class ConsoleView {
    private final static Scanner reader = new Scanner(System.in);
    private static GameController game;
    private static User first;
    private static User second;

    public static void main(String[] args) {
        printHead();
        initGame();
        mainLoop();
    }

    private static void printHead() {
        printLine("===============================================================");
        printLine("==================== Rock Paper Scissors ======================");
        printLine("===============================================================");
    }

    private static void initGame() {
        first = initUser();

        int opponent = choiceOpponent();

        if (opponent == BOT){
            second = new Bot();
        }else if(opponent == USER){
            second = initUser();
        }

        game = new GameController(first, second);

    }

    private static void mainLoop() {
        int chooseMenu = -1;
        while (chooseMenu != EXIT) {
            showMenu();
            chooseMenu = readInt();
            menuChoiceReact(chooseMenu);
        }
    }

    private static void showMenu() {
        String menu = NEW_GAME + " - Новая игра\n" +
                CONTINUE + " - Продолжить\n" +
                PRINT_SCORE + " - Показать счет\n" +
                EXIT + " - Выход";
        printLine(menu);
    }

    private static void menuChoiceReact(int chooseMenu) {
        if (chooseMenu == NEW_GAME){
            game.restart();
        }else if(chooseMenu == PRINT_SCORE){
            printScore();
        }else if (chooseMenu == EXIT){
            printLine("================ Thanks for game =====================");
        }

        if (chooseMenu == NEW_GAME || chooseMenu == CONTINUE){
            fight();
        }
    }

    private static void showHandChoice() {
        String handForm = ROCK + " - Камень\n" +
                PAPER + " - Ножницы\n" +
                SCISSORS + " - Бумага";
        printLine(handForm);
    }

    private static void reactChoiceHand(int handForm) {
        if (handForm == ROCK){
            game.firstChoice(HandForm.ROCK);
        }else if (handForm == PAPER){
            game.firstChoice(HandForm.PAPER);
        }else if (handForm == SCISSORS){
            game.firstChoice(HandForm.SCISSORS);
        }
    }

    private static void printScore() {
        printLine("=================== SCORE ===================");
        printLine(playerScore(first));
        printLine(playerScore(second));
        printLine("==============================================");
    }

    private static void fight() {
        showHandChoice();
        int handForm = readInt();
        reactChoiceHand(handForm);
        Result fight = game.fight();
        printLine(fight.name());
    }

    private static int choiceOpponent() {
        printLine("Play With:");
        printLine(BOT + " - bot");
        printLine(USER + " - another player");
        return readInt();
    }

    private static User initUser() {
        printLine("Write player name:");
        String name = readLine();
        return new User(name);
    }

    private static String playerScore(User user) {
        return user.getName() + ": " + user.scoreToString();
    }

    private static String readLine(){
        return reader.nextLine();
    }

    private static int readInt(){
        return reader.nextInt();
    }

    private static void printLine(String line){
        System.out.println(line);
    }
}
