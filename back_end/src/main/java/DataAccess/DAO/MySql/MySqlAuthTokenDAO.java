package DataAccess.DAO.MySql;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IAuthTokenDAO;

import java.text.SimpleDateFormat;

public class MySqlAuthTokenDAO implements IAuthTokenDAO {

    @Override
    public boolean createAuthToken(String userId) throws DatabaseException {
        return false;
    }

    @Override
    public void TokenRefresh(String token) throws DatabaseException {

    }

    @Override
    public boolean destroyAuthToken(String token) throws DatabaseException {
        return false;
    }
}
