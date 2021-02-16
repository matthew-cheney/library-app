package DataAccess.DAO.MySql;

import DataAccess.DAO.IItemDAO;
import DataAccess.Entities.Item;

public class MySqlItemDAO implements IItemDAO {

    @Override
    public Item getItem(String id) {
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
