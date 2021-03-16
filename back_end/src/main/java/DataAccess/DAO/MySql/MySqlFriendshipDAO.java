package DataAccess.DAO.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.Constants;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IFriendshipDAO;
import DataAccess.DAO.MySql.Abstract.BaseMySqlDAO;
import Entities.Friendship;
import Utilities.EntityUtils;

public class MySqlFriendshipDAO extends BaseMySqlDAO implements IFriendshipDAO {

    // region Get

    @Override
    public boolean friendshipExists(Friendship friendship) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT * FROM Friendships WHERE "
                + "UserIdA = ? AND "
                + "UserIdB = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, friendship.getSortedUserIdA());
            statement.setString(2, friendship.getSortedUserIdB());

            resultSet = statement.executeQuery();
            Friendship retrievedFriendship = null;
            while (resultSet.next()) {
                retrievedFriendship = new Friendship(
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }

            success = true;
            return retrievedFriendship != null;
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
    public List<String> getFriendIdsOfUser(String userId, int offset) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT * FROM Friendships WHERE "
                + "(UserIdA = ? OR "
                + "UserIdB = ?) "
                + "ORDER BY UserIdA "
                + "LIMIT " + offset + ", " + Constants.BATCH_SIZE;

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, userId);
            statement.setString(2, userId);

            resultSet = statement.executeQuery();
            List<String> friendIds = new ArrayList<>();
            while (resultSet.next()) {
                String userIdA = resultSet.getString(2);
                String userIdB = resultSet.getString(3);

                if (userIdA.equals(userId)) {
                    friendIds.add(userIdB);
                }
                else {
                    friendIds.add(userIdA);
                }
            }

            success = true;
            return friendIds;
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
    public boolean addFriendship(Friendship friendship) throws DatabaseException {
        if (friendshipExists(friendship)) {
            throw new DatabaseException("Friendship already exists!");
        }
        if (friendship.getSortedUserIdA().equals(friendship.getSortedUserIdB())) {
            throw new DatabaseException("You cannot befriend yourself!");
        }

        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        String sqlCommand = "INSERT INTO Friendships VALUES(?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, EntityUtils.generateId());
            statement.setString(2, friendship.getSortedUserIdA());
            statement.setString(3, friendship.getSortedUserIdB());

            if (statement.executeUpdate() != 1) {
                throw new DatabaseException("Error adding friendship!");
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
    public boolean deleteFriendship(Friendship friendship) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        String sqlCommand = "DELETE FROM Friendships WHERE "
                + "UserIdA = ? AND "
                + "UserIdB = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, friendship.getSortedUserIdA());
            statement.setString(2, friendship.getSortedUserIdB());

            if (statement.executeUpdate() != 1) {
                throw new DatabaseException("Error deleting friendship!");
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

    @Override
    public void clearFriendshipsTable() {
        String deleteTableCommand = "DROP TABLE Friendships";
        String createTableCommand = "CREATE TABLE IF NOT EXISTS Friendships (\n" +
                                        "\tId VARCHAR(36) NOT NULL,\n" +
                                        "\tUserIdA VARCHAR(36) NOT NULL,\n" +
                                        "\tUserIdB VARCHAR(36) NOT NULL,\n" +
                                        "\tPRIMARY KEY (Id),\n" +
                                        "\tFOREIGN KEY (UserIdA) REFERENCES Users(Id),\n" +
                                        "\tFOREIGN KEY (UserIdB) REFERENCES Users(Id)\n" +
                                    ");";

        remakeTable(deleteTableCommand, createTableCommand);
    }

    // endregion
}
