package Service;

import java.util.List;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IFriendshipDAO;
import Entities.Friendship;
import Request.FriendsRequest;
import Response.FriendsResponse;

public class GetFriendsService implements IGetFriendsService {
    @Override
    public FriendsResponse getFriends(FriendsRequest request){
        try {
            List<Friendship> friendships = getFriendshipDAO().getFriendsOfUser(
                    request.getUserId(), request.getOffset());
            return new FriendsResponse(true, friendships);
        }
        catch (DatabaseException ex) {
            return new FriendsResponse(false, ex.getMessage());
        }
    }

    public IFriendshipDAO getFriendshipDAO() {
        return DAOFactorySingleton.getInstance().makeFriendshipDAO();
    }
}
