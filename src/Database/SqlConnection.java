package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    
    public static Connection DBConnector(){
       
        try {
             Connection conn = null;
             Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/games","Ayman","root");
             return conn;
         }catch(ClassNotFoundException | SQLException e) {
             System.out.println(e);
         }
         return null;
    }
}
