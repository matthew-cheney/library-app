package Service;

import java.util.List;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IItemDAO;
import Entities.Item;
import Request.SearchItemsRequest;
import Response.SearchItemsResponse;

public class SearchItemsService implements ISearchItemsService {
    @Override
    public SearchItemsResponse searchItems(SearchItemsRequest request) {
        try {
            List<Item> items = getItemDAO().getItemsMatchingCriteria(
                    request.getOwnerId(), request.getSearchCriteria(), request.getOffset());
            return new SearchItemsResponse(true, items);
        }
        catch (DatabaseException ex) {
            return new SearchItemsResponse(false, ex.getMessage());
        }
    }

    public IItemDAO getItemDAO() {
        return DAOFactorySingleton.getInstance().makeItemDAO();
    }
}
