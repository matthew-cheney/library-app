package DataAccess.DAO.MySql;

import DataAccess.Connection.ConnectionFactory;
import DataAccess.DAO.IUserDAO;
import DataAccess.Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserDAO implements IUserDAO {

    @Override
    public User getUser(String id) {
        Connection connection = ConnectionFactory.openConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT "
                + "Users.Username, "
                + "Users.PasswordHash, "
                + "Users.PasswordSalt, "
                + "Users.FirstName, "
                + "Users.LastName, "
                + "Users.Email, "
                + "Users.PhoneNumber, "
                + "Users.ImageUrl "
                + "WHERE Users.Id = ?";

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
        }
        finally {
            ConnectionFactory.closeConnection(success);
        }

        return null;
    }

    @Override
    public void addUser(User user) {
        Connection connection = ConnectionFactory.openConnection();

        boolean success = false;
        String sqlCommand = "INSERT OR IGNORE INTO Users VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

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
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(success);
        }
    }

    @Override
    public void updateUser(String id, User user) {
        Connection connection = ConnectionFactory.openConnection();

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
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(success);
        }
    }

    @Override
    public void deleteUser(String id) {
        Connection connection = ConnectionFactory.openConnection();

        boolean success = false;
        String sqlCommand = "DELETE FROM Users WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);

            success = true;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(success);
        }
    }
}
