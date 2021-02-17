package DataAccess.DAO;

import DataAccess.Entities.User;

public interface IUserDAO {
    User getUser(String id) throws DatabaseException;
    boolean addUser(User user) throws DatabaseException;
    boolean updateUser(String id, User user) throws DatabaseException;
    boolean deleteUser(String id) throws DatabaseException;
}
