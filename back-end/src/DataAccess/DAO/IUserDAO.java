package DataAccess.DAO;

import DataAccess.Entities.User;

public interface IUserDAO {
    User getUser(String id);
    boolean addUser(User user);
    boolean updateUser(String id, User user);
    boolean deleteUser(String id);
}
