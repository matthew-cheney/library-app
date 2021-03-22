package Service;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IUserDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.LoginRequest;
import Response.LoginResponse;

public class LoginService implements ILoginService {
    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            User user = getUserDAO().getUserByCredentials(request.getUsername(), request.getPassword());
            return new LoginResponse(true, user);
        }
        catch (DatabaseException ex) {
            return new LoginResponse(false, ex.getErrorCodeAndMessage());
        }
    }

    public IUserDAO getUserDAO() {
        return DAOFactorySingleton.getInstance().makeUserDAO();
    }
}
