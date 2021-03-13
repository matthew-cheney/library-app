package DataAccess.DAO.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IFriendshipDAO;
import DataAccess.DAO.MySql.Abstract.BaseMySqlDAO;
import Entities.Friendship;
import Entities.Item;

public class MySqlFriendshipDAO extends BaseMySqlDAO implements IFriendshipDAO {

    @Override
    public boolean friendshipExists(Friendship friendship) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT * FROM Friendships WHERE "
                + " UserIdA = ? AND "
                + " UserIdB = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, friendship.getSortedUserIdA());
            statement.setString(2, friendship.getSortedUserIdB());

            resultSet = statement.executeQuery();
            Friendship retrievedFriendship = null;
            while (resultSet.next()) {
                retrievedFriendship = new Friendship(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }

            success = true;
            return retrievedFriendship == null;
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
    public boolean addFriendship(Friendship friendship) throws DatabaseException {
        return false;
    }

    @Override
    public boolean deleteFriendship(Friendship friendship) {
        return false;
    }
}
