package DataAccess.DAO.MySql;

import Config.Constants;
import DataAccess.DAO.MySql.Abstract.BaseMySqlDAO;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IItemDAO;
import Entities.Item;

import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlItemDAO extends BaseMySqlDAO implements IItemDAO {

    // region Get

    @Override
    public Item getItemById(String id) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT * FROM Items WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);

            resultSet = statement.executeQuery();
            Item item = null;
            int counter = 0;
            while (resultSet.next()) {
                assert(counter == 0); // Ensures only one user was returned
                item = new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getInt(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14)
                );

                counter++;
            }

            success = true;
            return item;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    @Override
    public List<Item> getItemsByOwner(String ownerId, int offset) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT * FROM Items WHERE OwnerId = ? "
                + "ORDER BY Title "
                + "LIMIT " + offset + ", " + Constants.BATCH_SIZE;

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, ownerId);

            resultSet = statement.executeQuery();
            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getInt(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14)
                );
                items.add(item);
            }

            success = true;
            return items;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    @Override
    public List<Item> getItemsMatchingCriteria(String ownerId, String searchCriteria, int offset) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        ResultSet resultSet;
        String sqlCommand = "SELECT * FROM Items WHERE "
                + getOwnerIdCommandChunk(ownerId)
                + "(Title LIKE ? OR "
                + "Description LIKE ? OR "
                + "NumPlayers LIKE ? OR "
                + "TimeToPlayInMins LIKE ? OR "
                + "ReleaseYear LIKE ? OR "
                + "Genre LIKE ? OR "
                + "ItemFormat LIKE ? OR "
                + "Author LIKE ? )"
                + "ORDER BY Title "
                + "LIMIT " + offset + ", " + Constants.BATCH_SIZE;

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            int startingInt = 1;
            if (!getOwnerIdCommandChunk(ownerId).equals("")) {
                statement.setString(startingInt, ownerId);
                startingInt++;
            }
            for (int i = startingInt; i < 8 + startingInt; i++) {
                statement.setString(i, searchCriteria);
            }

            resultSet = statement.executeQuery();
            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getInt(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14)
                );
                items.add(item);
            }

            success = true;
            return items;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    // endregion

    // region Add

    @Override
    public boolean addItem(Item item) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        String sqlCommand = "INSERT INTO Items VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, item.getId());
            statement.setString(2, item.getTitle());
            statement.setString(3, item.getCategory());
            statement.setString(4, item.getDateCreated());
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

            if (statement.executeUpdate() != 1) {
                throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, "Error adding item!");
            }
            success = true;
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    // endregion

    // region Update

    @Override
    public boolean updateItem(String id, Item item) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

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

            if (statement.executeUpdate() != 1) {
                throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, "Error updating item!");
            }
            success = true;
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    // endregion

    // region Delete

    @Override
    public boolean deleteItem(String id) throws DatabaseException {
        Connection connection = getConnectionPool().getConnection();

        boolean success = false;
        String sqlCommand = "DELETE FROM Items WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);

            if (statement.executeUpdate() != 1) {
                throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, "Error deleting item!");
            }
            success = true;
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new DatabaseException(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage());
        }
        finally {
            getConnectionPool().freeConnection(connection, success);
        }
    }

    @Override
    public void clearItemsTable() {
        String deleteTableCommand = "DROP TABLE Items";
        String createTableCommand = "CREATE TABLE Items (\n" +
                                        "\tId VARCHAR(36) NOT NULL,\n" +
                                        "\tTitle VARCHAR(50) NOT NULL,\n" +
                                        "\tCategory VARCHAR(25) NOT NULL,\n" +
                                        "\tDateCreated VARCHAR(30) NOT NULL,\n" +
                                        "\tAvailable BOOL NOT NULL,\n" +
                                        "\tOwnerId VARCHAR(36) NOT NULL,\n" +
                                        "\tImageUrl VARCHAR(50),\n" +
                                        "\tDescription VARCHAR(255),\n" +
                                        "\tNumPlayers INT,\n" +
                                        "\tTimeToPlayInMins INT,\n" +
                                        "\tReleaseYear INT,\n" +
                                        "\tGenre VARCHAR(50),\n" +
                                        "\tItemFormat VARCHAR(25),\n" +
                                        "\tAuthor VARCHAR(50),\n" +
                                        "\tPRIMARY KEY (Id),\n" +
                                        "\tFOREIGN KEY (OwnerId) REFERENCES Users(Id)\n" +
                                    ");";

        remakeTable(deleteTableCommand, createTableCommand);
    }

    // endregion

    private String getOwnerIdCommandChunk(String ownerId) {
        return ownerId == null ? "" : "OwnerId = ? AND ";
    }
}
