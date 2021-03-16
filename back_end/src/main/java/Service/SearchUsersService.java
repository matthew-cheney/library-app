package Service;

import java.util.List;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IUserDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.SearchUsersRequest;
import Response.SearchUsersResponse;

public class SearchUsersService implements ISearchUsersService {
    @Override
    public SearchUsersResponse searchUsers(SearchUsersRequest request) {
        try {
            List<User> users = getUserDAO().getUsersMatchingCriteria(request.getSearchCriteria(), request.getOffset());
            return new SearchUsersResponse(true, users);
        }
        catch (DatabaseException ex) {
            return new SearchUsersResponse(false, ex.getMessage());
        }
    }

    public IUserDAO getUserDAO() {
        return DAOFactorySingleton.getInstance().makeUserDAO();
    }
}
