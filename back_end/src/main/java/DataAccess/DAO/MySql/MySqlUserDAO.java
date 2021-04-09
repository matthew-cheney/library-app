package DataAccess.DAO.MySql;

import Config.Constants;
import DataAccess.DAO.MySql.Abstract.BaseMySqlDAO;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IUserDAO;
import Entities.User;
import Utilities.EntityUtils;

import java.net.HttpURLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDAO extends BaseMySqlDAO implements IUserDAO {

    // region Get

    @Override
    public User getUserById(String id) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT * FROM Users WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);

            resultSet = statement.executeQuery();
            User user = null;
            int counter = 0;
            while (resultSet.next()) {
                assert(counter == 0); // Ensures only one user was returned

                user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                );

                counter++;
            }

            success = true;
            return user;
        }
        catch (SQLException ex) {
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    @Override
    public List<User> getUsersByIds(List<String> ids, int offset) throws DatabaseException {

        if (ids.isEmpty()) {
            return new ArrayList<>();
        }

        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = buildGetUsersByIdsQuery(ids, offset);

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            for (int i = 1; i <= ids.size(); i++) {
                statement.setString(i, ids.get(i - 1));
            }

            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                );

                users.add(user);
            }

            success = true;
            return users;
        }
        catch (SQLException ex) {
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    @Override
    public User getUserByCredentials(String username, String password) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT * FROM Users WHERE Username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, username);

            resultSet = statement.executeQuery();
            User user = null;
            int counter = 0;
            while (resultSet.next()) {
                assert(counter == 0); // Ensures only one user was returned
                String passwordHash = resultSet.getString(3);
                String passwordSalt = resultSet.getString(4);

                if (!EntityUtils.verifyPassword(password, passwordHash, passwordSalt)) {
                    throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, "Invalid Password!");
                }

                user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        passwordHash,
                        passwordSalt,
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                );

                counter++;
            }

            if (user == null) {
                throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, "Invalid Username!");
            }
            success = true;
            return user;
        }
        catch (SQLException ex) {
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    @Override
    public List<User> getUsersMatchingCriteria(String searchCriteria, int offset) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT * FROM Users WHERE "
                + getColumnPartiallyContainsCommandChunk(searchCriteria)
                + " OR "
                + getColumnFullyContainsCommandChunk(searchCriteria)
                + getSearchResultsOrderByCommandChunk(searchCriteria)
                + "LIMIT " + offset + ", " + Constants.BATCH_SIZE;

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                );
                users.add(user);
            }

            success = true;
            return users;
        }
        catch (SQLException ex) {
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    // endregion

    // region Add

    @Override
    public boolean addUser(User user) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        String sqlCommand = "INSERT INTO Users VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getPasswordSalt());
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            statement.setString(7, user.getEmail());
            statement.setString(8, user.getPhoneNumber());
            statement.setString(9, user.getImageUrl());

            if (statement.executeUpdate() != 1) {
                throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, "Error adding user!");
            }
            success = true;
            return true;
        }
        catch (SQLException ex) {
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    // endregion

    // region Update

    @Override
    public boolean updateUser(String id, User user) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        String sqlCommand = "UPDATE Users SET "
                + "Username = ?, "
                + "PasswordHash = ?, "
                + "PasswordSalt = ?, "
                + "FirstName = ?, "
                + "LastName = ?, "
                + "Email = ?, "
                + "PhoneNumber = ?, "
                + "ImageUrl = ? "
                + "WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getPasswordSalt());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getPhoneNumber());
            statement.setString(8, user.getImageUrl());
            statement.setString(9, user.getId());

            if (statement.executeUpdate() != 1) {
                throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, "Error updating user!");
            }
            success = true;
            return true;
        }
        catch (SQLException ex) {
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    // endregion

    // region Delete

    @Override
    public boolean deleteUser(String id) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        String sqlCommand = "DELETE FROM Users WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);

            if (statement.executeUpdate() != 1) {
                throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, "Error deleting user!");
            }
            success = true;
            return true;
        }
        catch (SQLException ex) {
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    @Override
    public void clearUsersTable() {
        String deleteTableCommand = "DROP TABLE IF EXISTS Users";
        String createTableCommand = "CREATE TABLE IF NOT EXISTS Users (\n" +
                                        "\tId VARCHAR(36) NOT NULL,\n" +
                                        "\tUsername VARCHAR(50) NOT NULL UNIQUE,\n" +
                                        "\tPasswordHash VARCHAR(255) NOT NULL,\n" +
                                        "\tPasswordSalt VARCHAR(24) NOT NULL,\n" +
                                        "\tFirstName VARCHAR(25) NOT NULL,\n" +
                                        "\tLastName VARCHAR(25) NOT NULL,\n" +
                                        "\tEmail VARCHAR(50),\n" +
                                        "\tPhoneNumber VARCHAR(25),\n" +
                                        "\tImageUrl VARCHAR(50),\n" +
                                        "\tPRIMARY KEY (Id)\n" +
                                    ");";

        remakeTable(deleteTableCommand, createTableCommand);
    }

    // endregion

    private String buildGetUsersByIdsQuery(List<String> ids, int offset) {
        StringBuilder sqlCommand = new StringBuilder("SELECT * FROM Users WHERE Id = ");
        for (String id : ids) {
            if (id.equals(ids.get(0))) {
                sqlCommand.append(" ? ");
            }
            else {
                sqlCommand.append("OR Id = ? ");
            }
        }

        sqlCommand.append("LIMIT ").append(offset).append(", ").append(Constants.BATCH_SIZE);
        return sqlCommand.toString();
    }

    private String getColumnPartiallyContainsCommandChunk(String searchCriteria) {
        return "('" + searchCriteria + "' LIKE Concat(Concat('%', Username), '%') OR " +
                "'" + searchCriteria + "' LIKE Concat(Concat('%', FirstName), '%') OR " +
                "'" + searchCriteria + "' LIKE Concat(Concat('%', LastName), '%'))";
    }

    private String getColumnFullyContainsCommandChunk(String searchCriteria) {
        return "(Username LIKE '%" + searchCriteria + "%' OR "
                + "FirstName LIKE '%" + searchCriteria + "%' OR "
                + "LastName LIKE '%" + searchCriteria + "%')";
    }

    private String getSearchResultsOrderByCommandChunk(String searchCriteria) {
        return "ORDER BY (('" + searchCriteria + "' LIKE Concat(Concat('%', Username), '%')) + " +
                        "('" + searchCriteria + "' LIKE Concat(Concat('%', LastName), '%')) + " +
                        "('" + searchCriteria + "' LIKE Concat(Concat('%', FirstName), '%'))" +
                        ") DESC, FirstName, LastName ";
    }
}
