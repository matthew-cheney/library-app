package Service;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.IUserDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.EditUserRequest;
import Response.EditUserResponse;
import Response.RegisterResponse;

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

    private IUserDAO getUserDAO() {
        return new MySqlUserDAO();
    }
}
