package Service;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IItemDAO;
import DataAccess.DAO.MySql.MySqlItemDAO;
import Request.EditItemRequest;
import Response.EditItemResponse;

public class EditItemService implements IEditItemService {
    @Override
    public EditItemResponse editItem(EditItemRequest request) {
        try {
            return new EditItemResponse(getItemDAO().updateItem(request.getItem().getId(), request.getItem()));
        }
        catch (DatabaseException ex) {
            return new EditItemResponse(false, ex.getErrorCode(), ex.getMessage());
        }
    }

    public IItemDAO getItemDAO() {
        return DAOFactorySingleton.getInstance().makeItemDAO();
    }
}
