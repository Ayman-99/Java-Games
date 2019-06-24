package LoginAndRegister;

import Database.SqlConnection;
import otherStuff.AlterClass;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javax.swing.JOptionPane;
import Main.GamesMain;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController extends AlterClass implements Initializable {

    public static String tempName;
    Stage window;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    private Connection conn = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = SqlConnection.DBConnector();
        window = new Stage();
    }

    @FXML
    public void loggedIn() throws IOException, SQLException {
        PreparedStatement ps1 = conn.prepareStatement("SELECT `date` FROM `users` WHERE username=?");
        ps1.setString(1, username.getText());
        ResultSet rs1 = ps1.executeQuery();
        if (rs1.next()) {
            Date today = new Date();
            Date expire = rs1.getDate("date");
            if (today.compareTo(expire) > 0) {
                alertDialog("Program EXPIRED, if u wish to use it again.\n"
                        + "Send a email to \"test@test.com\"\n with your username and password. regards!", "ALERT", "WARNING");
                System.exit(0);
            }
        }
        tempName = username.getText();
        if (username.getText().equalsIgnoreCase("users") && password.getText().equalsIgnoreCase("users")) {
            String temp = JOptionPane.showInputDialog(null, "Enter Database password", JOptionPane.QUESTION_MESSAGE);
            if (Integer.parseInt(temp) == 123456) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Database/DataBaseTable.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                this.window.setTitle("DataBase");
                this.window.setScene(new Scene(root1));
                this.window.show();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/otherStuff/GameSelection.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                GamesMain.window.setTitle("DataBase");
                GamesMain.window.setScene(new Scene(root1));
                GamesMain.window.show();
            }
        } else {
            try {
                String query = "select * from `users` where username=? and password=?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/otherStuff/GameSelection.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    GamesMain.window.setTitle("DataBase");
                    GamesMain.window.setScene(new Scene(root1));
                    GamesMain.window.show();
                } else {
                    alertDialog("Wrong password or username", "ALERT", "WARNING");
                }
                pst.close();
                rs.close();
            } catch (Exception e1) {
                System.err.println(e1);
            }
        }
    }

    @FXML
    public void Registering() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/LoginAndRegister/Register.fxml"));
        Main.GamesMain.window.setScene(new Scene(root));
    }
}


