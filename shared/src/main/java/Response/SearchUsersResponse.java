package Response;

import java.util.List;

import Entities.User;
import Response.Abstract.BaseResponse;

public class SearchUsersResponse extends BaseResponse {

    private List<User> users;

    public SearchUsersResponse() {}

    public SearchUsersResponse(boolean success, List<User> users) {
        super(success);
        setUsers(users);
    }

    public SearchUsersResponse(boolean success, String message) {
        super(success, message);
    }

    public SearchUsersResponse(boolean success, int errorCode, String message) {
        super(success, errorCode, message);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
