package DataAccess.DAO;

import DataAccess.Entities.User;

public interface IUserDAO {
    User getUser(String id);
    void addUser(User user);
    void updateUser(String id, User user);
    void deleteUser(String id);
}
