package game.view.javafx;

import game.controller.GameController;
import game.entity.Result;
import game.entity.hand.HandForm;
import game.entity.user.Bot;
import game.entity.user.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaFx extends Application {

    @FXML private ProgressBar resultBar;
    @FXML private ProgressIndicator pi;
    @FXML private Label resultLabel;
    @FXML private ImageView paper;
    @FXML private ImageView rock;
    @FXML private ImageView scissors;
    @FXML private Label choiceHand;

    @FXML private Label nameColumn;
    @FXML private Label nameFirst;
    @FXML private Label nameSecond;
    @FXML private Label winsColumn;
    @FXML private Label winsFirst;
    @FXML private Label winsSecond;
    @FXML private Label loseColumn;
    @FXML private Label loseFirst;
    @FXML private Label loseSecond;
    @FXML private Label drawColumn;
    @FXML private Label drawFirst;
    @FXML private Label drawSecond;
    @FXML private Label scoreColumn;
    @FXML private Label scoreFirst;
    @FXML private Label scoreSecond;

    private ColorAdjust colorize = new ColorAdjust(0,0,0,0);
    private ColorAdjust gray = new ColorAdjust(0,-1,0,0);
    private ResourceBundle msg = ResourceBundle.getBundle("headMessages");
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private User firstUser = new User("Terry Pratchett");
    private User secondUser = new Bot();

    private GameController game;

    public void startFx(String... args){
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
        updateAllResults();
    }

    private void updateAllResults() {
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
        stage.setOnCloseRequest(a -> executorService.shutdownNow());
        stage.show();
    }

    public void backToBlack(MouseEvent mouseEvent) {
        ImageView source = (ImageView)mouseEvent.getSource();
        source.setEffect(gray);
    }

    public void colorize(MouseEvent mouseEvent) {
        ImageView source = (ImageView)mouseEvent.getSource();
        source.setEffect(colorize);
    }

    public void choice(MouseEvent mouseEvent) {
        ImageView source = (ImageView)mouseEvent.getSource();
        String id = source.getId().toUpperCase();
        game.firstChoice(HandForm.valueOf(id));
        Result fight = game.fight();
        printResult(fight);
        updateAllResults();
    }

    private void printResult(Result result) {
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
}
