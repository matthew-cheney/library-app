package Service;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IItemDAO;
import DataAccess.DAO.MySql.MySqlItemDAO;
import Entities.Item;
import Request.AddItemRequest;
import Response.AddItemResponse;

public class AddItemService implements IAddItemService {
    @Override
    public AddItemResponse addItem(AddItemRequest request) {
        Item item = new Item(
                request.getTitle(),
                request.getCategory(),
                request.isAvailable(),
                request.getOwnerId(),
                request.getImageUrl(),
                request.getDescription(),
                request.getNumPlayers(),
                request.getTimeToPlayInMins(),
                request.getReleaseYear(),
                request.getGenre(),
                request.getItemFormat(),
                request.getAuthor()
        );

        try {
            return new AddItemResponse(getItemDAO().addItem(item));
        }
        catch (DatabaseException ex) {
            return new AddItemResponse(false, ex.getMessage());
        }
    }

    public IItemDAO getItemDAO() {
        return DAOFactorySingleton.getInstance().makeItemDAO();
    }
}
