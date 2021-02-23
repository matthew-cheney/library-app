package DataAccess.DAO;

import DataAccess.DAO.DatabaseException;

public interface IAuthTokenDAO {
    boolean createAuthToken(String userId) throws DatabaseException;
    void TokenRefresh(String token) throws DatabaseException;
    boolean destroyAuthToken(String token) throws DatabaseException;
}
