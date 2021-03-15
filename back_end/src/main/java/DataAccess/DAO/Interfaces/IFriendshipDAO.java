package DataAccess.DAO.Interfaces;

import java.util.List;

import DataAccess.DAO.DatabaseException;
import Entities.Friendship;

public interface IFriendshipDAO {
    boolean friendshipExists(Friendship friendship) throws DatabaseException;
    List<Friendship> getFriendsOfUser(String userId, int offset) throws DatabaseException;
    boolean addFriendship(Friendship friendship) throws DatabaseException;
    boolean deleteFriendship(Friendship friendship) throws DatabaseException;
    void clearFriendshipsTable();
}
