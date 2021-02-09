package DataAccess.DAO;

import DataAccess.Entities.Item;

public interface IItemDAO {
    Item getItem(String id);
    void addItem(Item item);
    void updateItem(String id, Item item);
    void deleteItem(String id);
}
