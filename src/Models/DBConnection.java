import java.sql.*;
public class DBConnection {

    private Connection conn;
    public Connection getConn(){return conn;}
    public DBConnection() throws SQLException {
        String connectionUrl;
        connectionUrl = "jdbc:postgresql://localhost:5432/pharmacy";
        String user = "postgres";
        String password = "1234";
        conn = DriverManager.getConnection(connectionUrl, user, password);
        if (conn == null) {
            System.out.println("Did not connect!");
        }
    }
}
