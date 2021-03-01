package DataAccess.DAO.MySql;

import DataAccess.Connection.ConnectionPool;
import DataAccess.DAO.IItemDAO;
import Entities.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlItemDAO implements IItemDAO {

    private ConnectionPool connectionPool;

    public MySqlItemDAO() {
        connectionPool = ConnectionPool.getInstance();
    }

    public MySqlItemDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Item getItem(String id) {
        Connection connection = connectionPool.getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT Title, Category, DateCreated, Available, OwnerId, "
                + "ImageUrl, Description, NumPlayers, TimeToPlayInMins, ReleaseYear, "
                + "Genre, ItemFormat, Author FROM Items WHERE Id = ?";

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
            connectionPool.freeConnection(connection, success);
        }

        return null;
    }

    @Override
    public boolean addItem(Item item) {
        Connection connection = connectionPool.getConnection();

        boolean success = false;
        String sqlCommand = "INSERT INTO Items VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, item.getId());
            statement.setString(2, item.getTitle());
            statement.setString(3, item.getCategory());
            statement.setString(4, item.getDateCreated().toString());
            statement.setBoolean(5, item.isAvailable());
            statement.setString(6, item.getOwnerId());
            statement.setString(7, item.getImageUrl());
            statement.setString(8, item.getDescription());
            statement.setInt(9, item.getNumPlayers());
            statement.setInt(10, item.getTimeToPlayInMins());
            statement.setInt(11, item.getReleaseYear());
            statement.setString(12, item.getGenre());
            statement.setString(13, item.getItemFormat());
            statement.setString(14, item.getAuthor());

            success = true;
            return statement.executeUpdate() == 1;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            connectionPool.freeConnection(connection, success);
        }

        return false;
    }

    @Override
    public boolean updateItem(String id, Item item) {
        Connection connection = connectionPool.getConnection();

        boolean success = false;
        String sqlCommand = "UPDATE Items SET "
                + "Title = ?, "
                + "Category = ?, "
                + "DateCreated = ?, "
                + "Available = ?, "
                + "OwnerId = ?, "
                + "ImageUrl = ?, "
                + "Description = ?, "
                + "NumPlayers = ?, "
                + "TimeToPlayInMins = ?, "
                + "ReleaseYear = ?, "
                + "Genre = ?, "
                + "ItemFormat = ?, "
                + "Author = ? "
                + "WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getCategory());
            statement.setString(3, item.getDateCreated().toString());
            statement.setBoolean(4, item.isAvailable());
            statement.setString(5, item.getOwnerId());
            statement.setString(6, item.getImageUrl());
            statement.setString(7, item.getDescription());
            statement.setInt(8, item.getNumPlayers());
            statement.setInt(9, item.getTimeToPlayInMins());
            statement.setInt(10, item.getReleaseYear());
            statement.setString(11, item.getGenre());
            statement.setString(12, item.getItemFormat());
            statement.setString(13, item.getAuthor());
            statement.setString(14, item.getId());

            success = true;
            return statement.executeUpdate() == 1;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            connectionPool.freeConnection(connection, success);
        }

        return false;
    }

    @Override
    public boolean deleteItem(String id) {
        Connection connection = connectionPool.getConnection();

        boolean success = false;
        String sqlCommand = "DELETE FROM Items WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);

            success = true;
            return statement.executeUpdate() == 1;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            connectionPool.freeConnection(connection, success);
        }

        return false;
    }
}
