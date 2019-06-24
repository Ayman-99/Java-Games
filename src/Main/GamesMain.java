package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class GamesMain extends Application {

    /* public void additionalStuff(){
       // Hover text
       /*Tooltip tooltipsShow = new Tooltip("Hghhhh");
       Label test = new Label("TEXT");
       test.setTooltip(tooltipsShow);
    } */

    public static Stage window;

    @Override
    public void start(Stage primaryStage) throws IOException {       
        window = primaryStage;
        window.setTitle("MiniGames");
        AnchorPane root = FXMLLoader.load(getClass().getResource("/LoginAndRegister/Login1.fxml"));
        Scene temp = new Scene(root);
        window.setScene(temp);
        window.show();
    }

    public static void main(String[] args) {     

        launch(args);
    }

}
