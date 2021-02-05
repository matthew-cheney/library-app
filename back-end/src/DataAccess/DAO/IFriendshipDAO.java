package DataAccess.DAO;

import DataAccess.Entities.Friendship;

import java.sql.SQLException;

public interface IFriendshipDAO {
    boolean friendshipExists(Friendship friendship);
    void addFriendship(Friendship friendship);
    void updateFriendship(Friendship friendship);
    void deleteFriendship(Friendship friendship);
}
