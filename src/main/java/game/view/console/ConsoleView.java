package game.view.console;

import game.entity.Result;
import game.entity.hand.HandForm;

import java.util.ResourceBundle;
import java.util.Scanner;

import static game.view.console.Constants.*;

public class ConsoleView {
    private final static Scanner reader = new Scanner(System.in);
    private ConsoleController controller = new ConsoleController();
    private ResourceBundle msg = ResourceBundle.getBundle("headMessages");

    private void printHead() {
        printLine("===============================================================");
        printLine("==================== "+msg.getString("gameName")+" ======================");
        printLine("===============================================================");
    }

    private void initGame() {
        controller.initFirstPlayer(requireUserName());
        int opponent = choiceOpponent();

        if (opponent == BOT){
            controller.playWithBot();
        }else if(opponent == USER){
            printLine(msg.getString("notSupported"));
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
        String menu = msg.getString("menuChoice")+":\n" +
                RESTART + " - "+ msg.getString("restart")+"\n" +
                CONTINUE + " - "+ msg.getString("continue")+"\n" +
                PRINT_SCORE + " - "+ msg.getString("printScore")+"\n" +
                EXIT + " - " + msg.getString("exit");
        printLine(menu);
    }

    private void menuChoiceReact(int chooseMenu) {
        if (chooseMenu == RESTART){
            controller.restart();
        }else if(chooseMenu == PRINT_SCORE){
            printScore();
        }else if (chooseMenu == EXIT){
            printLine("================ " + msg.getString("thanksForGame") + " =====================");
        }else {
            printLine(msg.getString("notSupported"));
        }

        if (chooseMenu == RESTART || chooseMenu == CONTINUE){
            fight();
        }
    }

    private void showHandChoice() {
        String handForm = msg.getString("choiceHandForm") + ":\n" +
                ROCK + " - "+ msg.getString("rock") +"\n" +
                PAPER + " - "+ msg.getString("paper") +"\n" +
                SCISSORS + " - "+ msg.getString("scissors");
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
        printLine("=================== "+msg.getString("score")+" ===================");
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
            return "================ "+ msg.getString("win") +" ================";
        else if (result == Result.LOSE)
            return "================ "+ msg.getString("lose") +" ================";
        else
            return "=================== "+ msg.getString("draw") +" ===================";
    }

    private int choiceOpponent() {
        printLine(msg.getString("choiceOpponent") + ":");
        printLine(BOT + " - "+ msg.getString("bot"));
        printLine(USER + " - "+ msg.getString("anotherPlayer"));
        return readInt();
    }

    private String requireUserName() {
        printLine(msg.getString("writeName")+":");
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
