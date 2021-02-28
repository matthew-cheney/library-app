package DataAccess.DAO;

import Entities.Friendship;

import java.sql.SQLException;

public interface IFriendshipDAO {
    boolean friendshipExists(Friendship friendship);
    boolean addFriendship(Friendship friendship);
    boolean deleteFriendship(Friendship friendship);
}