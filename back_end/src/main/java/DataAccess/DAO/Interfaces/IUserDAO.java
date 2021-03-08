package DataAccess.DAO.Interfaces;

import java.util.List;

import DataAccess.DAO.DatabaseException;
import Entities.User;

public interface IUserDAO {
    User getUserById(String id) throws DatabaseException;
    User getUserByCredentials(String username, String password) throws DatabaseException;
    List<User> getUsersMatchingCriteria(String searchCriteria, int offset) throws DatabaseException;
    boolean addUser(User user) throws DatabaseException;
    boolean updateUser(String id, User user) throws DatabaseException;
    boolean deleteUser(String id) throws DatabaseException;
}
