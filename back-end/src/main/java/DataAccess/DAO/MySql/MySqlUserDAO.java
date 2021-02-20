package DataAccess.DAO.MySql;

import DataAccess.Connection.ConnectionPool;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.IUserDAO;
import Entities.User;
import Utilities.EntityUtils;

import java.sql.*;

public class MySqlUserDAO implements IUserDAO {

    @Override
    public User getUserById(String id) throws DatabaseException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT Username, PasswordHash, PasswordSalt, FirstName, LastName, "
                + "Email, PhoneNumber, ImageUrl FROM Users WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);

            resultSet = statement.executeQuery();
            User user = null;
            int counter = 0;
            while (resultSet.next()) {
                assert(counter == 0); // Ensures only one user was returned

                user = new User(
                        id,
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
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
            ConnectionPool.getInstance().freeConnection(connection, success);
        }
    }

    @Override
    public User getUserByCredentials(String username, String password) throws DatabaseException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT Id, PasswordHash, PasswordSalt, FirstName, LastName, "
                + "Email, PhoneNumber, ImageUrl FROM Users WHERE Username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, username);

            resultSet = statement.executeQuery();
            User user = null;
            int counter = 0;
            while (resultSet.next()) {
                assert(counter == 0); // Ensures only one user was returned
                String passwordHash = resultSet.getString(2);
                String passwordSalt = resultSet.getString(3);

                if (!EntityUtils.verifyPassword(password, passwordHash, passwordSalt)) {
                    throw new DatabaseException("Invalid Password!");
                }

                user = new User(
                        resultSet.getString(1),
                        username,
                        passwordHash,
                        passwordSalt,
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
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
            ConnectionPool.getInstance().freeConnection(connection, success);
        }
    }

    @Override
    public boolean addUser(User user) throws DatabaseException {
        Connection connection = ConnectionPool.getInstance().getConnection();

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

            success = true;
            return statement.executeUpdate() == 1;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(ex.getErrorCode(), ex.getMessage());
        }
        finally {
            ConnectionPool.getInstance().freeConnection(connection, success);
        }
    }

    @Override
    public boolean updateUser(String id, User user) throws DatabaseException {
        Connection connection = ConnectionPool.getInstance().getConnection();

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

            success = true;
            return statement.executeUpdate() == 1;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(ex.getErrorCode(), ex.getMessage());
        }
        finally {
            ConnectionPool.getInstance().freeConnection(connection, success);
        }
    }

    @Override
    public boolean deleteUser(String id) throws DatabaseException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        boolean success = false;
        String sqlCommand = "DELETE FROM Users WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);

            success = true;
            return statement.executeUpdate() == 1;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(ex.getErrorCode(), ex.getMessage());
        }
        finally {
            ConnectionPool.getInstance().freeConnection(connection, success);
        }
    }
}
