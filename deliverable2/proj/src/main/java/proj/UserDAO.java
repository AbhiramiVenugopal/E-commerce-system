package proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private Connection getConnection() throws SQLException {
        // Replace with your actual connection code
        return DatabaseConnection.connect();
    }

    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO User (Username, Password, Email, FirstName, LastName, Address, PhoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword()); // Ensure the password is hashed
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getAddress());
            pstmt.setString(7, user.getPhoneNumber());
            return pstmt.executeUpdate() > 0;
        }
    }

    public User getUser(int userID) throws SQLException {
        String sql = "SELECT * FROM User WHERE UserID = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber")
                    );
                }
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("UserID"),
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("Email"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Address"),
                    rs.getString("PhoneNumber")
                ));
            }
        }
        return users;
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE User SET Username = ?, Password = ?, Email = ?, FirstName = ?, LastName = ?, Address = ?, PhoneNumber = ? WHERE UserID = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword()); // Ensure the password is hashed
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getAddress());
            pstmt.setString(7, user.getPhoneNumber());
            pstmt.setInt(8, user.getUserID());
            return pstmt.executeUpdate() > 0;
        }
    }

   
    
    public User authenticate(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM User WHERE Username = ? AND Password = ?";
        User user = null;

        try (Connection conn = DatabaseConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.setString(2, password); // In a real application, hash the password before comparing

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    

   
}
