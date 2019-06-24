/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game4;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import otherStuff.GameSelectionController;

/**
 * FXML Controller class
 *
 * @author Ayman
 */
public class MathGameController implements Initializable {

    @FXML
    private CheckBox answer1;
    @FXML
    private CheckBox answer3;
    @FXML
    private CheckBox answer4;
    @FXML
    private CheckBox answer2;
    private CheckBox[] Boxs = new CheckBox[4];
    private int num1;
    private int num2;
    private char mO1;
    private int count = 0;
    private int points = 0;
    @FXML
    private Label question;
    @FXML
    private MenuItem gameInfo;
    @FXML
    private Button level2;
    @FXML
    private Label Points;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Boxs[0] = answer1;
        Boxs[1] = answer2;
        Boxs[2] = answer3;
        Boxs[3] = answer4;
        Generate_Question();
    }

    @FXML
    public void helpMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RULES");
        alert.setHeaderText(null);
        alert.setContentText("it's obvious how it works!");
        alert.showAndWait();
    }

    @FXML
    public void correctAnswer() {
        answer2.setTextFill(Color.GREEN);
        answer1.disableProperty().setValue(Boolean.TRUE);
        answer3.disableProperty().setValue(Boolean.TRUE);
        answer4.disableProperty().setValue(Boolean.TRUE);
        points++;
        Points.setText("Points: " + points);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setHeaderText(null);
        alert.setContentText("Correct");
        alert.showAndWait();

        Boxs[0] = answer1;
        Boxs[1] = answer2;
        Boxs[2] = answer3;
        Boxs[3] = answer4;
        Generate_Question();
    }

    @FXML
    public void wrongAnswer() {
        answer2.setTextFill(Color.GREEN);
        answer2.disableProperty().setValue(Boolean.TRUE);
        answer1.disableProperty().setValue(Boolean.TRUE);
        answer3.disableProperty().setValue(Boolean.TRUE);
        answer4.disableProperty().setValue(Boolean.TRUE);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setHeaderText(null);
        alert.setContentText("wrong");
        alert.showAndWait();

        Boxs[0] = answer1;
        Boxs[1] = answer2;
        Boxs[2] = answer3;
        Boxs[3] = answer4;
        Generate_Question();
    }

    @FXML
    public void Generate_Question() {
        count = 15;
        for (int i = 0; i < 4; i++) {
            Boxs[i].setSelected(false);
            Boxs[i].setTextFill(Color.rgb(171, 8, 8));
            Boxs[i].disableProperty().setValue(Boolean.FALSE);
        }
        Random rnd = new Random();
        num1 = rnd.nextInt(100); // Generate random number
        num2 = rnd.nextInt(100); // Generate random number
        mO1 = getOperator1(); // Generate Math operator (+ | -)
        question.setText((num1 + " " + mO1 + " " + num2 + " ? ")); // displaying question;
        switch (mO1) {
            case '+':
                answer1.setText(String.valueOf(num1 + num2) + 4);
                answer2.setText(String.valueOf((num1 + num2)));
                answer3.setText(String.valueOf((num1 + num2) + 2));
                answer4.setText(String.valueOf((num1 + num2) + 3));
                break;
            case '-':
                answer1.setText(String.valueOf(num1 - num2) + 4);
                answer2.setText(String.valueOf((num1 - num2)));
                answer3.setText(String.valueOf((num1 - num2) + 2));
                answer4.setText(String.valueOf((num1 - num2) + 3));
                break;
        }
    }

    public char getOperator1() {
        int mathOperators = (int) (Math.random() * 3) + 43; // Generate mathematical operator from unicode
        // Changing 2 unicode which are not related to mathematical operator thus i had to convert them to mathematical operator
        switch (mathOperators) {
            case 44:
                mathOperators--;
                break;
            case 46:
                mathOperators--;
                break;
        }
        // Changing unicode to a character 
        return ((char) mathOperators);
    }

    @FXML
    private void nextLevel() {
        if (points < 15) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rule");
            alert.setHeaderText(null);
            alert.setContentText("You need at least 15 to advance to the next level");
            alert.showAndWait();
        } else {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/Game4/level2.fxml"));
                GameSelectionController.temp.setScene(new Scene(root));
                GameSelectionController.temp.show();

            } catch (IOException ex) {
                Logger.getLogger(MathGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}





