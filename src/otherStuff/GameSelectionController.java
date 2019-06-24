package otherStuff;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import Game2.AsteroidsApp;
import Game3.MinesweeperApp;
import javafx.scene.image.ImageView;

public class GameSelectionController {

    public static Stage temp;
    @FXML
    private ImageView RockPaper;
    @FXML
    private ImageView MathGame;
    @FXML
    private ImageView MathGame1;
    @FXML
    private ImageView RockPaper1;

    public GameSelectionController() {
        temp = new Stage();
    }

    @FXML
    public void loadRock() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Game1/Game1.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        temp.setTitle("Rock-Paper-Scissor");
        temp.setScene(new Scene(root1));
        temp.show();
    }
    @FXML
    private void loadAsteroids() {
        new AsteroidsApp();
    }
    @FXML
    private void loadMine(){
        new MinesweeperApp();
    }

    @FXML
    private void loadMath() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Game4/MathGame.fxml"));
        temp.setTitle("Math");
        temp.setScene(new Scene(root));
        temp.show();
    }
}


