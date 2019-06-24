/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginAndRegister;

import Database.SqlConnection;
import otherStuff.AlterClass;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class RegisterController extends AlterClass implements Initializable {

    @FXML
    private JFXPasswordField regPass;

    @FXML
    private JFXTextField regUser;
    
    private final Connection conn = SqlConnection.DBConnector();

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void signUp() {
        Date today = new Date();
        Date expire = addExpireDate(today, 10);
        try {
            if (validatePassword()) {
                java.sql.Date sqlDate = new java.sql.Date(expire.getTime());
                //To create bigger database, do same format
                String query = "INSERT INTO users (username, password, date) VALUES(?,?,?)";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, regUser.getText());
                pst.setString(2, regPass.getText());
                pst.setDate(3, sqlDate);
                pst.execute();
                pst.close();
                alertDialog("User has been created. Your account is valid for 10 days", "Information dialog", "INFORMATION");
            }
        } catch (SQLException e1) {
            System.err.println(e1);
        }
    }
    public static Date addExpireDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public boolean validatePassword() {
        /* 
        ?=.9\\d : Must contains one digit from 0-9 rule
        ?=.[a-z] : must conatins one lowercase char
        ?=.*[A-Z] : must conatins one Upper char
        ?=.*[@#$%] : must contains one of @#$%
        .{4,10} : minium and maximum chara
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{4,10})"); 
         */
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[A-Z]).{4,10})");
        Matcher m = p.matcher(regPass.getText());
        if (m.matches()) {
            return true;
        } else {
            alertDialog("Password must contain at least one(Digit, uppercase)"
                    + " and length must be between 4- 10, e.g: 1234A", "ALERT", "WARNING");
            return false;
        }
    }

}
