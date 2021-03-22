package Service;

import java.util.List;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IFriendshipDAO;
import DataAccess.DAO.Interfaces.IUserDAO;
import Request.FriendsRequest;
import Response.FriendsResponse;

public class GetFriendsService implements IGetFriendsService {
    @Override
    public FriendsResponse getFriends(FriendsRequest request) {
        try {
            List<String> friendIds = getFriendshipDAO().getFriendIdsOfUser(
                    request.getUserId(), request.getOffset());
            return new FriendsResponse(true, getUserDAO().getUsersByIds(friendIds, 0));
        }
        catch (DatabaseException ex) {
            return new FriendsResponse(false, ex.getErrorCodeAndMessage());
        }
    }

    public IFriendshipDAO getFriendshipDAO() {
        return DAOFactorySingleton.getInstance().makeFriendshipDAO();
    }

    public IUserDAO getUserDAO() {
        return DAOFactorySingleton.getInstance().makeUserDAO();
    }
}
