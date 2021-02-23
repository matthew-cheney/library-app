package Request;

import Entities.User;

public class EditUserRequest {

    private User user;

    public EditUserRequest() {}

    public EditUserRequest(User user) {
        setUser(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
