package DataAccess.DAO.MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionFactory {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://159.203.100.250:3306/tadhgcra_lopdev?user=tadhgcra_lopdev&password=57vnAWD4gwyn3i6";

    private static Connection connection;

    // Set up the MySql Driver...
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load driver");
            e.printStackTrace();
        }
    }

    public static Connection openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(CONNECTION_URL);
                connection.setAutoCommit(false);
            }
            return connection;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Error connecting to the Database!");
        }
    }

    public static void closeConnection(boolean commit) {
        try {
            if (commit) connection.commit();
            else connection.rollback();

            connection.close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            connection = null;
        }
    }
}
