package Response.Abstract;

import Entities.User;

public abstract class UserResponse extends BaseResponse {

    private User user;

    /**
     * Empty constructor to work with the lambdas
     */
    public UserResponse() {}

    /**
     * Typically for a successful operation - no message will be necessary
     * @param success
     */
    public UserResponse(boolean success, User user) {
        super(success);
        setUser(user);
    }

    /**
     * Typically for a failed operation - an error message will be included
     * and no user object will be necessary
     * @param success
     * @param message
     */
    public UserResponse(boolean success, String message) {
        super(success, message);
    }

    /**
     * Typically for a failed operation - an error message will be included and an HTTP error code
     * @param success
     * @param errorCode
     * @param message
     */
    public UserResponse(boolean success, int errorCode, String message) {
        setSuccess(success);
        setErrorCode(errorCode);
        setMessage(message);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
