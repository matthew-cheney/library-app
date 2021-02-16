package DataAccess.DAO.MySql;

import DataAccess.Connection.ConnectionPool;
import DataAccess.DAO.IItemDAO;
import DataAccess.Entities.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlItemDAO implements IItemDAO {

    @Override
    public Item getItem(String id) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT Title, Category, DateCreated, Available, OwnerId, "
                + "ImageUrl, Description, NumPlayers, TimeToPlayInMins, ReleaseYear, "
                + "Genre, ItemFormat, Author FROM Users WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);

            resultSet = statement.executeQuery();
            Item item = null;
            int counter = 0;
            while (resultSet.next()) {
                assert(counter == 0); // Ensures only one user was returned
                item = new Item(
                        id,
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getBoolean(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13)
                );

                counter++;
            }

            success = true;
            return item;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            ConnectionPool.getInstance().freeConnection(connection, success);
        }

        return null;
    }

    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public boolean updateItem(String id, Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }
}
