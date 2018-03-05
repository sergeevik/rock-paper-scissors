package game.view.javafx;

import game.entity.hand.HandForm;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class JavaFx extends Application {

    @FXML
    private ImageView paper;
    @FXML
    private ImageView rock;
    @FXML
    private ImageView scissors;
    @FXML
    private Label choiceHand;

    private ColorAdjust colorize = new ColorAdjust(0,0,0,0);
    private ColorAdjust gray = new ColorAdjust(0,-1,0,0);
    private ResourceBundle msg = ResourceBundle.getBundle("headMessages", Locale.ENGLISH);

    public static void main(String[] args) {
        launch(args);
    }

    public void initialize() throws Exception {
        paper.setEffect(gray);
        rock.setEffect(gray);
        scissors.setEffect(gray);
        choiceHand.setText(msg.getString("choiceHandForm"));
        choiceHand.setAlignment(Pos.CENTER);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        stage.setScene(new Scene(root));
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
        System.out.println(HandForm.valueOf(id));
    }

}
