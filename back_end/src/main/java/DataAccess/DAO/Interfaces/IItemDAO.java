package DataAccess.DAO.Interfaces;

import DataAccess.DAO.DatabaseException;
import Entities.Item;

public interface IItemDAO {
    Item getItem(String id) throws DatabaseException;
    boolean addItem(Item item) throws DatabaseException;
    boolean updateItem(String id, Item item) throws DatabaseException;
    boolean deleteItem(String id) throws DatabaseException;
}
