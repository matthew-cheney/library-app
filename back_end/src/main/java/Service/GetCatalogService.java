package Service;

import java.util.List;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IItemDAO;
import DataAccess.DAO.MySql.MySqlItemDAO;
import Entities.Item;
import Request.CatalogRequest;
import Response.CatalogResponse;

public class GetCatalogService implements IGetCatalogService {
    @Override
    public CatalogResponse getCatalog(CatalogRequest request) {
        try {
            List<Item> items = getItemDAO().getItemsByOwner(request.getOwnerId(), request.getOffset());
            return new CatalogResponse(true, items);
        }
        catch (DatabaseException ex) {
            return new CatalogResponse(false, ex.getErrorCodeAndMessage());
        }
    }

    public IItemDAO getItemDAO() {
        return DAOFactorySingleton.getInstance().makeItemDAO();
    }
}
