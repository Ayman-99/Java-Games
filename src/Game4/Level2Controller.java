/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game4;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ayman
 */
public class Level2Controller implements Initializable {

    @FXML
    private CheckBox answer1;
    @FXML
    private CheckBox answer3;
    @FXML
    private CheckBox answer4;
    @FXML
    private CheckBox answer2;
    private CheckBox[] Boxs = new CheckBox[4];
    @FXML
    private Label Timer;
    private int num1;
    private int num2;
    private char mO2;
    private int count = 0;
    @FXML
    private Label question;
    @FXML
    private MenuItem gameInfo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startCountDown();
        Boxs[0] = answer1;
        Boxs[1] = answer2;
        Boxs[2] = answer3;
        Boxs[3] = answer4;
        Generate_Question();
    }

    @FXML
    private void helpMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RULES");
        alert.setHeaderText(null);
        alert.setContentText("it's obvious how it works idiot!");
        alert.showAndWait();
    }

    public void startCountDown() {
        try {
            Timer.setText("");
            count = 15;
            KeyFrame key = new KeyFrame(
                    Duration.millis(1000), e -> updateText());
            Timeline time = new Timeline(key);
            time.setCycleCount(count);
            time.play();
        } catch (Exception e) {
            Timer.setText("Error");
        }
    }

    public void updateText() {
        count--;
        Timer.setText(count + "");
        if (count == 0) {
            Generate_Question();
            startCountDown();
        }
    }

    @FXML
    public void correctAnswer() {
        answer2.setTextFill(Color.GREEN);
        answer1.disableProperty().setValue(Boolean.TRUE);
        answer3.disableProperty().setValue(Boolean.TRUE);
        answer4.disableProperty().setValue(Boolean.TRUE);
    }

    @FXML
    public void wrongAnswer() {
        answer2.setTextFill(Color.GREEN);
        answer2.disableProperty().setValue(Boolean.TRUE);
        answer1.disableProperty().setValue(Boolean.TRUE);
        answer3.disableProperty().setValue(Boolean.TRUE);
        answer4.disableProperty().setValue(Boolean.TRUE);
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
        mO2 = getOperator2(); // Generate Math operator (* | /)
        question.setText((num1 + " " + mO2 + " " + num2 + " ? ")); // displaying question;
        switch (mO2) {
            case '*':
                answer1.setText(String.valueOf(num1 * num2) + 4);
                answer2.setText(String.valueOf((num1 * num2)));
                answer3.setText(String.valueOf((num1 * num2) + 2));
                answer4.setText(String.valueOf((num1 * num2) + 3));
                break;
            case '/':
                answer1.setText(String.valueOf(num1 / num2) + 4);
                answer2.setText(String.valueOf((num1 / num2)));
                answer3.setText(String.valueOf((num1 / num2) + 2));
                answer4.setText(String.valueOf((num1 / num2) + 3));
                break;
        }
    }

    public char getOperator2() {

        int mathOperators2 = (int) (Math.random() * 5) + 42; // Generate mathematical operator from unicode
        // Changing 2 unicode which are not related to mathematical operator thus i had to convert them to mathematical operator
        switch (mathOperators2) {
            case 44:
                mathOperators2 -= 2;
                break;
            case 46:
                mathOperators2++;
                break;
            case 43:
                mathOperators2--;
                break;
            case 45:
                mathOperators2 += 2;
        }
        // Changing unicode to a character
        return ((char) mathOperators2);
    }

}
