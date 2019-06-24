package Game1;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Game1Controller implements Initializable {

    @FXML
    private Text result1;
    @FXML
    private Button rockChoice;
    @FXML
    private Button paperChoice;
    @FXML
    private Button scissorChoice;
    private int userSelection;
    private int compSelection;
    private String result;
    private String player1Pick;
    private String computerPick;
    static int userWins1 = 0;
    static int compWins1 = 0;
    static int coins = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image rockImage = new Image("rock.png");
        Image paperImage = new Image("paper.png");
        Image scissorsImage = new Image("scissors.png");
        rockChoice.setGraphic(new ImageView(rockImage));
        paperChoice.setGraphic(new ImageView(paperImage));
        scissorChoice.setGraphic(new ImageView(scissorsImage));
    }

    @FXML
    private void rock(ActionEvent event) {
        userSelection = 1;
        player1Pick = "Rock";
        displayResults();
    }

    @FXML
    private void paper(ActionEvent event) {
        userSelection = 2;
        player1Pick = "Paper";
        displayResults();
    }

    @FXML
    private void scissor(ActionEvent event) {
        userSelection = 3;
        player1Pick = "Scissors";
        displayResults();
    }

    // sets a random number for the computer selection
    public int getCompSelection() {
        Random random = new Random();
        compSelection = random.nextInt(3) + 1;
        switch (compSelection) {
            case 1:
                computerPick = "Rock";
                break;
            case 2:
                computerPick = "Paper";
                break;
            case 3:
                computerPick = "Scissors";
                break;
            default:
                break;
        }
        return compSelection;
    }

    // applies the game logic
    public String runGame() {
        int temp = getCompSelection();
        if ((userSelection == 1 && temp == 3)
                || (userSelection == 2 && temp == 1)
                || (userSelection == 3 && temp == 2)) {
            result = "Congratulations. You win!!";
            userWins1++;
            coins += 2;
        } else if ((userSelection == 1 && temp == 2)
                || (userSelection == 2 && temp == 3)
                || (userSelection == 3 && temp == 1)) {
            result = "The computer wins. Better luck next time!";
            compWins1++;
        } else {
            result = "The game was a tie! Please try again.";
        }
        return result;
    }

    // displays the outcome of the game
    public void displayResults() {
        String temp = runGame();
        result1.setText("You picked " + player1Pick
                + " and the computer picked " + computerPick + ".\n" + temp + "\n"
                + "Your current coins: " + coins + "\nUser wins: " + userWins1 + "  ||  Computer wins: " + compWins1);
    }

    @FXML
    private void rules(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RULES");
        alert.setHeaderText(null);
        alert.setContentText("The rules of Rock, Paper, Scissors are simple. \n"
                + "Rock beats Scissors, Paper beats Rock and Scissors beats Paper. \n"
                + "In order to make your choice you simply click on the relevant button. \n"
                + "You are playing against the computer and the computer's "
                + "choice is randomly selected. \n" + "Enjoy the game!!");
        alert.showAndWait();
    }

}
