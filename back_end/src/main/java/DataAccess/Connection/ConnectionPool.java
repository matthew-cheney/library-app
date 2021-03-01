package DataAccess.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    // region Properties

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DEFAULT_USER = "tadhgcra_lopdev";
    private static final String DEFAULT_PASSWORD = "57vnAWD4gwyn3i6";
    private static final String CONNECTION_URL = "jdbc:mysql://159.203.100.250:3306/";

    private static ConnectionPool dataSource;
    private ComboPooledDataSource comboPooledDataSource;

    // endregion

    // region Singleton Private Constructor

    private ConnectionPool(String user, String password) {
        try {
            comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass(DRIVER);
            comboPooledDataSource.setJdbcUrl(CONNECTION_URL + user);
            comboPooledDataSource.setUser(user);
            comboPooledDataSource.setPassword(password);
        }
        catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    // endregion

    // region Public

    public static ConnectionPool getInstance() {
        if (dataSource == null)
            dataSource = new ConnectionPool(DEFAULT_USER, DEFAULT_PASSWORD);
        return dataSource;
    }

    public static ConnectionPool getInstance(String user, String password) {
        if (dataSource == null)
            dataSource = new ConnectionPool(user, password);
        return dataSource;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = comboPooledDataSource.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void freeConnection(Connection connection, boolean commit) {
        try {
            connection.setAutoCommit(false);
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

    // endregion
}
