package DB;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class UserAuthentication {
    private static final String USERS_TABLE = "users";

    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static void registerUser(String username, String password) throws SQLException {
        String hashedPassword = hashPassword(password);

        if (isUserExist(username)) {
            System.out.println("Пользователь с таким логином уже существует!");
            return;
        }

        try (Connection connection = connect()) {
            String sql = "INSERT INTO " + USERS_TABLE + " (username, password) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, hashedPassword);
                stmt.executeUpdate();
                System.out.println("Пользователь успешно зарегистрирован!");
            }
        }
    }

    private static boolean isUserExist(String username) throws SQLException {
        String sql = "SELECT 1 FROM " + USERS_TABLE + " WHERE username = ?";
        try (Connection connection = connect(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public static boolean loginUser(String username, String password) throws SQLException {
        String sql = "SELECT password FROM " + USERS_TABLE + " WHERE username = ?";
        try (Connection connection = connect(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return checkPassword(password, storedPassword);
            }
        }
        return false;
    }

    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/LMS";
        String user = "postgres";
        String password = "1111";

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        }
    }
}
