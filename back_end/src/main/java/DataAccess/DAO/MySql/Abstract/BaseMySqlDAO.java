package DataAccess.DAO.MySql.Abstract;

import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataAccess.Connection.ConnectionPool;
import DataAccess.DAO.DatabaseException;

public abstract class BaseMySqlDAO {

    public ConnectionPool getConnectionPool() {
        return ConnectionPool.getInstance();
    }

    public void remakeTable(String deleteTableCommand, String createTableCommand) {
        try {
            alterTable(deleteTableCommand);
        }
        catch (DatabaseException ignored) {}
        try {
            alterTable(createTableCommand);
        }
        catch (DatabaseException ignored) {}
    }

    public void alterTable(String sqlCommand) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();
        boolean success = false;

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

            statement.executeUpdate();
            success = true;
        }
        catch (SQLException ex) {
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }
}
