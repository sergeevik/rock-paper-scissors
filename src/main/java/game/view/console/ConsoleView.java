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
            printLine("Сорян. Пока доступна только игра с ботом");
//            TODO: убрать когда доделаю
//            controller.playWithUser(requireUserName());
            controller.playWithBot();
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
        String menu = "Что делаем?:\n" +
                RESTART + " - Рестарт игры\n" +
                CONTINUE + " - Продолжить\n" +
                PRINT_SCORE + " - Показать счет\n" +
                EXIT + " - Выход";
        printLine(menu);
    }

    private void menuChoiceReact(int chooseMenu) {
        if (chooseMenu == RESTART){
            controller.restart();
        }else if(chooseMenu == PRINT_SCORE){
            printScore();
        }else if (chooseMenu == EXIT){
            printLine("================ Спасибо за игру =====================");
        }

        if (chooseMenu == RESTART || chooseMenu == CONTINUE){
            fight();
        }
    }

    private void showHandChoice() {
        String handForm = "Выбери свою фигуру:\n" +
                ROCK + " - Камень\n" +
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
        printLine(fightResultToString(fight));
    }

    private String fightResultToString(Result result) {
        if (result == Result.WIN)
            return "================ Ты выиграл ================";
        else if (result == Result.LOSE)
            return "================ Ты проиграл ================";
        else
            return "=================== Ничья ===================";
    }

    private int choiceOpponent() {
        printLine("С кем будешь играть:");
        printLine(BOT + " - бот");
        printLine(USER + " - другой игрок");
        return readInt();
    }

    private String requireUserName() {
        printLine("Введи имя:");
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
