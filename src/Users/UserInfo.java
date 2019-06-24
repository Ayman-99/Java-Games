package Users;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserInfo {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty password;
    private Date date;
    
    public UserInfo(String userName, String password) {
        this.id = null;
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        this.date = null;
    }
    public UserInfo(String userName, String password, Date date) {
        this.id = null;
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        this.date = date;
    }
    public UserInfo(int id,String username, String password, Date date) {
        this.id = new SimpleIntegerProperty(id);
        this.userName = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.date = date;
    }

    public String getUserName() {
        return userName.get();
    }
    public String getPassword() {
        return password.get();
    }
    public void setUsername(String userName) {
        this.userName.set(userName);
    }
    public void setPassword(String password) {
        this.password.set(password);
    }
    public int getId() {
        return id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public Date getExpireDate() {
        return date;
    }
    public void setExpireDate(Date date) {
       this.date = date;
    }
}    
    
