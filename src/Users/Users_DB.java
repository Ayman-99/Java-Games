package Users;

import Database.SqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Users_DB {

    private static Connection conn = SqlConnection.DBConnector();
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static List<UserInfo> getList() throws SQLException {
        List<UserInfo> list = new ArrayList<UserInfo>();
        String sql = "SELECT * FROM `users`";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new UserInfo(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getDate("date")));
        }
        return list;
    }

    public static UserInfo getUser(String username) throws SQLException {
        String sql = "SELECT * FROM `users` WHERE `username`=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new UserInfo(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getDate("date"));
        } else {
            ps.close();
            rs.close();
            return null;
        }
    }

    public static boolean updateUser(String username, String password, Date date, int id) throws SQLException {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "UPDATE `users` SET `username`=?, `password`=?, `date`=? WHERE `id`=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setDate(3, sqlDate);
        ps.setInt(4, id);
        ps.executeUpdate();
        return true;
    }

    public static void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM `users` WHERE `id`=?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public static List<String> getNames() throws SQLException {
        List<String> list = new ArrayList<String>();
        String sql = "SELECT `username` FROM `users` WHERE 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("username"));
        }
        ps.close();
        rs.close();
        return list;
    }

}
