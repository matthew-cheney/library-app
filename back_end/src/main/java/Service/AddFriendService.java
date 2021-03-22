package Service;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IFriendshipDAO;
import Request.AddFriendRequest;
import Response.AddFriendResponse;

public class AddFriendService implements IAddFriendService {
    @Override
    public AddFriendResponse addFriend(AddFriendRequest request) {
        try {
            return new AddFriendResponse(getFriendshipDAO().addFriendship(request.getFriendship()));
        }
        catch (DatabaseException ex) {
            return new AddFriendResponse(false, ex.getMessage());
        }
    }

    public IFriendshipDAO getFriendshipDAO() {
        return DAOFactorySingleton.getInstance().makeFriendshipDAO();
    }
}
