package DataAccess.DAO.Interfaces;

import DataAccess.DAO.DatabaseException;
import Entities.Friendship;

import java.sql.SQLException;

public interface IFriendshipDAO {
    boolean friendshipExists(Friendship friendship) throws DatabaseException;
    boolean addFriendship(Friendship friendship) throws DatabaseException;
    boolean deleteFriendship(Friendship friendship) throws DatabaseException;
}
