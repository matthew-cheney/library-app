package DataAccess.DAO.Interfaces;

import java.util.List;

import DataAccess.DAO.DatabaseException;
import Entities.Item;

public interface IItemDAO {
    Item getItemById(String id) throws DatabaseException;
    List<Item> getItemsByOwner(String ownerId) throws DatabaseException;
    boolean addItem(Item item) throws DatabaseException;
    boolean updateItem(String id, Item item) throws DatabaseException;
    boolean deleteItem(String id) throws DatabaseException;
}
