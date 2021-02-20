package Service;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.IUserDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.RegisterRequest;
import Response.RegisterResponse;

public class RegisterService {
    public RegisterResponse register(RegisterRequest request) {
        User user = new User(
                request.getUsername(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getImageUrl()
        );

        try {
            return new RegisterResponse(getUserDAO().addUser(user));
        }
        catch (DatabaseException ex) {
            return new RegisterResponse(false, ex.getMessage());
        }
    }

    private IUserDAO getUserDAO() {
        return new MySqlUserDAO();
    }
}
