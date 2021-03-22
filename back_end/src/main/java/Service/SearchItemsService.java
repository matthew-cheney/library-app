package Service;

import java.net.HttpURLConnection;
import java.util.List;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IItemDAO;
import DataAccess.DAO.Interfaces.IUserDAO;
import Entities.Item;
import Entities.User;
import Request.SearchItemsRequest;
import Response.SearchItemsResponse;

public class SearchItemsService implements ISearchItemsService {
    @Override
    public SearchItemsResponse searchItems(SearchItemsRequest request) {
        try {
            if (request.getOwnerId() != null) {
                User validatedUser = getUserDAO().getUserById(request.getOwnerId());
                if (validatedUser == null) {
                    return new SearchItemsResponse(false,
                            HttpURLConnection.HTTP_BAD_REQUEST, "Invalid owner id!");
                }
            }
            List<Item> items = getItemDAO().getItemsMatchingCriteria(
                    request.getOwnerId(), request.getSearchCriteria(), request.getOffset());
            return new SearchItemsResponse(true, items);
        }
        catch (DatabaseException ex) {
            return new SearchItemsResponse(false, ex.getErrorCode(), ex.getMessage());
        }
    }

    public IItemDAO getItemDAO() {
        return DAOFactorySingleton.getInstance().makeItemDAO();
    }

    public IUserDAO getUserDAO() {
        return DAOFactorySingleton.getInstance().makeUserDAO();
    }
}
