package Service;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IItemDAO;
import DataAccess.DAO.MySql.MySqlItemDAO;
import Request.DeleteItemRequest;
import Response.DeleteItemResponse;

public class DeleteItemService implements IDeleteItemService {
    @Override
    public DeleteItemResponse deleteItem(DeleteItemRequest request) {
        try {
            return new DeleteItemResponse(getItemDAO().deleteItem(request.getId()));
        }
        catch (DatabaseException ex) {
            return new DeleteItemResponse(false, ex.getMessage());
        }
    }

    public IItemDAO getItemDAO() {
        return DAOFactorySingleton.getInstance().makeItemDAO();
    }
}
