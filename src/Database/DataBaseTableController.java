package Database;

import Users.UserInfo;
import Users.Users_DB;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DataBaseTableController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    ObservableList<UserInfo> info = FXCollections.observableArrayList();
    @FXML
    private ListView<String> DataLISTVIEW;
    @FXML
    private TableView<UserInfo> DataTABLEVIEW;
    @FXML
    private TextField id;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField expireDate;
    @FXML
    private TableColumn<UserInfo, Integer> idCol;
    @FXML
    private TableColumn<UserInfo, String> usernameCol;
    @FXML
    private TableColumn<UserInfo, String> passwordCol;
    @FXML
    private TableColumn<UserInfo, Date> expireCol;
    private Connection conn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn = SqlConnection.DBConnector();
        try {
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        expireCol.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
    }

    public void getData() throws SQLException {
        list.addAll(Users_DB.getNames());
        info.addAll(Users_DB.getList());
        DataLISTVIEW.getItems().addAll(list);
        DataTABLEVIEW.getItems().addAll(info);
    }

    @FXML
    public void selectUser() throws SQLException {
        UserInfo temp = Users_DB.getUser((String) DataLISTVIEW.getSelectionModel().getSelectedItem());
        id.setText(String.valueOf(temp.getId()));
        username.setText(temp.getUserName());
        password.setText(temp.getPassword());
        expireDate.setText(String.valueOf(temp.getExpireDate()));
    }

    @FXML
    public void updateUser() throws ParseException, SQLException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(expireDate.getText());
        if (Users_DB.updateUser(username.getText(), password.getText(), date1, Integer.parseInt(id.getText()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information dialog");
            alert.setHeaderText(null);
            alert.setContentText("User has been updated");
            alert.showAndWait();
        }
    }

    @FXML
    public void deleteUser() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setContentText("You want to delete?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Users_DB.deleteUser(Integer.parseInt(id.getText()));
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Information dialog");
            alert2.setContentText("User has been deleted");
        }
    }

    @FXML
    public void loadData() throws SQLException {
        list.clear();
        info.clear();
        DataLISTVIEW.getItems().clear();
        DataTABLEVIEW.getItems().clear();
        //loading the new
        getData();
    }

    @FXML
    public void refreshDataTable() throws SQLException {// removing inital table/list
        list.clear();
        info.clear();
        DataLISTVIEW.getItems().clear();
        DataTABLEVIEW.getItems().clear();
        //loading the new
        getData();
    }

}

