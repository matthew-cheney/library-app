package DataAccess.DAO.MySql;

import DataAccess.DAO.IFriendshipDAO;
import DataAccess.Entities.Friendship;

public class MySqlFriendshipDAO implements IFriendshipDAO {

    @Override
    public boolean friendshipExists(Friendship friendship) {
        return false;
    }

    @Override
    public boolean addFriendship(Friendship friendship) {
        return false;
    }

    @Override
    public boolean deleteFriendship(Friendship friendship) {
        return false;
    }
}
