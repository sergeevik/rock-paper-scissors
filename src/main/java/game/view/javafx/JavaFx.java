package game.view.javafx;

import game.controller.GameController;
import game.entity.Result;
import game.entity.hand.HandForm;
import game.entity.user.Bot;
import game.entity.user.User;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class JavaFx extends Application {

    @FXML
    private ProgressBar resultBar;
    @FXML
    private Label resultLabel;
    @FXML
    private ImageView paper;
    @FXML
    private ImageView rock;
    @FXML
    private ImageView scissors;
    @FXML
    private Label choiceHand;

    @FXML
    private Label nameColumn;
    @FXML
    private Label nameFirst;
    @FXML
    private Label nameSecond;
    @FXML
    private Label winsColumn;
    @FXML
    private Label winsFirst;
    @FXML
    private Label winsSecond;
    @FXML
    private Label loseColumn;
    @FXML
    private Label loseFirst;
    @FXML
    private Label loseSecond;
    @FXML
    private Label drawColumn;
    @FXML
    private Label drawFirst;
    @FXML
    private Label drawSecond;
    @FXML
    private Label scoreColumn;
    @FXML
    private Label scoreFirst;
    @FXML
    private Label scoreSecond;

    @FXML
    private Label blockClick;
    @FXML
    private Region blockOnLoad;

    private ColorAdjust colorize = new ColorAdjust(0, 0, 0, 0);
    private ColorAdjust gray = new ColorAdjust(0, -1, 0, 0);
    private ResourceBundle msg = ResourceBundle.getBundle("headMessages");
    private Map<Integer, String> jokes = new HashMap<>();
    private Random random = new Random();

    private User firstUser = new User("Terry Pratchett");
    private User secondUser = new Bot();

    private GameController game;

    public void startFx(String... args) {
        launch(args);
    }

    public void initialize() throws Exception {
        paper.setEffect(gray);
        rock.setEffect(gray);
        scissors.setEffect(gray);
        choiceHand.setText(msg.getString("choiceHandForm"));
        choiceHand.setAlignment(Pos.CENTER);
        game = new GameController(firstUser, secondUser);
        initResultColumns();
        resultBar.setProgress(-1.0);
        initJokesMap();
    }

    private void initJokesMap() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("waitMessages.properties"));
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            int number = Integer.parseInt(String.valueOf(entry.getKey()));
            String joke = entry.getValue().toString();
            jokes.put(number, joke);
        }
    }

    private void initResultColumns() {
        nameColumn.setText(msg.getString("nameColumn"));
        nameFirst.setText(firstUser.getName());
        nameSecond.setText(secondUser.getName());
        winsColumn.setText(msg.getString("winsColumn"));
        loseColumn.setText(msg.getString("loseColumn"));
        scoreColumn.setText(msg.getString("scoreColumn"));
        drawColumn.setText(msg.getString("drawColumn"));
        resultLabel.setAlignment(Pos.CENTER);
        blockClick.setAlignment(Pos.CENTER);
        updateResultTable();
    }

    private void updateResultTable() {
        updateFirstResults();
        updateSecondResults();
    }

    private void updateSecondResults() {
        winsSecond.setText(String.valueOf(secondUser.getWinCount()));
        loseSecond.setText(String.valueOf(secondUser.getLoseCount()));
        drawSecond.setText(String.valueOf(secondUser.getDrawCount()));
        scoreSecond.setText(String.valueOf(secondUser.getScore()));
    }

    private void updateFirstResults() {
        winsFirst.setText(String.valueOf(firstUser.getWinCount()));
        loseFirst.setText(String.valueOf(firstUser.getLoseCount()));
        drawFirst.setText(String.valueOf(firstUser.getDrawCount()));
        scoreFirst.setText(String.valueOf(firstUser.getScore()));
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void backToBlack(MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getSource();
        source.setEffect(gray);
    }

    public void colorize(MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getSource();
        source.setEffect(colorize);
    }

    public void choice(MouseEvent mouseEvent) {
        blockOnLoad.setVisible(true);
        ImageView source = (ImageView) mouseEvent.getSource();
        String id = source.getId().toUpperCase();
        game.firstChoice(HandForm.valueOf(id));
        Result fight = game.fight();
        printResult(fight);
    }

    private void printResult(Result result) {
        getServiceOfTask(updateResultThread()).start();
        if (result == Result.WIN){
            resultLabel.setText(msg.getString("win"));
            resultLabel.setStyle("-fx-background-color:#4dff4d;");
        }else if(result == Result.LOSE){
            resultLabel.setText(msg.getString("lose"));
            resultLabel.setStyle("-fx-background-color:#ff4d4d;");
        }else {
            resultLabel.setText(msg.getString("draw"));
            resultLabel.setStyle("-fx-background-color:#ffff4d;");
        }
    }

    private Service<Void> getServiceOfTask(Task<Void> task) {
        return new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return task;
            }
        };
    }

    private Task<Void> updateResultThread() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                resultBar.setVisible(true);
                sleep(3000);
                resultBar.setVisible(false);
                blockOnLoad.setVisible(false);
                blockClick.setVisible(false);
                return null;
            }

            @Override
            protected void succeeded() {
                updateResultTable();
            }
        };
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignore) {
        }
    }

    public void blockReact() {
        blockClick.setVisible(true);
        blockClick.setText(getRandomText());
    }

    private String getRandomText(){
        int jokeNumber = random.nextInt(jokes.size()-1);
        return jokes.get(jokeNumber);
    }
}
