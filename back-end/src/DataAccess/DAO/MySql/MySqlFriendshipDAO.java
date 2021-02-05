package DataAccess.DAO.MySql;

import DataAccess.DAO.IFriendshipDAO;
import DataAccess.Entities.Friendship;

public class MySqlFriendshipDAO implements IFriendshipDAO {
    @Override
    public boolean friendshipExists(Friendship friendship) {
        return false;
    }

    @Override
    public void addFriendship(Friendship friendship) {

    }

    @Override
    public void updateFriendship(Friendship friendship) {

    }

    @Override
    public void deleteFriendship(Friendship friendship) {

    }
}
