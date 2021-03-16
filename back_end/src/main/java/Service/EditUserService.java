package Service;

import DataAccess.DAO.DAOFactorySingleton;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.Interfaces.IUserDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Request.EditUserRequest;
import Response.EditUserResponse;

public class EditUserService implements IEditUserService {
    @Override
    public EditUserResponse editUser(EditUserRequest request) {
        try {
            return new EditUserResponse(getUserDAO().updateUser(request.getUser().getId(), request.getUser()));
        }
        catch (DatabaseException ex) {
            return new EditUserResponse(false, ex.getMessage());
        }
    }

    public IUserDAO getUserDAO() {
        return DAOFactorySingleton.getInstance().makeUserDAO();
    }
}
