package Service;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IFriendshipDAO;
import Request.CheckFriendshipExistsRequest;
import Response.CheckFriendshipExistsResponse;

public class CheckFriendshipExistsService implements ICheckFriendshipExistsService {
    @Override
    public CheckFriendshipExistsResponse friendshipExists(CheckFriendshipExistsRequest request) {
        try {
            return new CheckFriendshipExistsResponse(true, getFriendshipDAO().friendshipExists(request.getFriendship()));
        }
        catch (DatabaseException ex) {
            return new CheckFriendshipExistsResponse(false, ex.getMessage());
        }
    }

    public IFriendshipDAO getFriendshipDAO() {
        return DAOFactorySingleton.getInstance().makeFriendshipDAO();
    }
}
