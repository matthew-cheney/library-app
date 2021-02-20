package Response;


import Entities.User;
import Response.Abstract.UserResponse;

public class LoginResponse extends UserResponse {

    public LoginResponse() {}

    public LoginResponse(boolean success, User user) {
        super(success, user);
    }

    public LoginResponse(boolean success, String message) {
        super(success, message);
    }
}
