package Service;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IFriendshipDAO;
import Request.RemoveFriendRequest;
import Response.RemoveFriendResponse;

public class RemoveFriendService implements IRemoveFriendService {
    @Override
    public RemoveFriendResponse removeFriend(RemoveFriendRequest request) {
        try {
            return new RemoveFriendResponse(getFriendshipDAO().deleteFriendship(request.getFriendship()));
        }
        catch (DatabaseException ex) {
            return new RemoveFriendResponse(false, ex.getErrorCode(), ex.getMessage());
        }
    }

    public IFriendshipDAO getFriendshipDAO() {
        return DAOFactorySingleton.getInstance().makeFriendshipDAO();
    }
}
