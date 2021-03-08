package DataAccess.DAO.MySql;

import Config.Constants;
import DataAccess.Connection.ConnectionPool;
import DataAccess.DAO.Abstract.BaseDAO;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IUserDAO;
import Entities.Item;
import Entities.User;
import Utilities.EntityUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDAO extends BaseDAO implements IUserDAO {

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
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(ex.getErrorCode(), ex.getMessage());
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
                    throw new DatabaseException("Invalid Password!");
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
                throw new DatabaseException("Invalid Username!");
            }
            success = true;
            return user;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(ex.getErrorCode(), ex.getMessage());
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
        String sqlCommand = "SET @SEARCH_CRITERIA = ? "
                + "SELECT * FROM Users WHERE "
                + "FirstName LIKE @SEARCH_CRITERIA OR "
                + "LastName LIKE @SEARCH_CRITERIA OR "
                + "Email LIKE @SEARCH_CRITERIA OR "
                + "PhoneNumber LIKE @SEARCH_CRITERIA "
                + "ORDER BY FirstName, LastName "
                + "OFFSET " + offset + " ROWS FETCH NEXT " + Constants.BATCH_SIZE + " ROWS ONLY";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, searchCriteria);

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
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(ex.getErrorCode(), ex.getMessage());
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
                throw new DatabaseException("Error adding user!");
            }
            success = true;
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(ex.getErrorCode(), ex.getMessage());
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
                throw new DatabaseException("Error updating user!");
            }
            success = true;
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(ex.getErrorCode(), ex.getMessage());
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
                throw new DatabaseException("Error deleting user!");
            }
            success = true;
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(ex.getErrorCode(), ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    // endregion
}
