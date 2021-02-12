package DataAccess.DAO;

import DataAccess.Entities.Item;

public interface IItemDAO {
    Item getItem(String id);
    boolean addItem(Item item);
    boolean updateItem(String id, Item item);
    boolean deleteItem(String id);
}
