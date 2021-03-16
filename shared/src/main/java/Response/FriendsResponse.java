package Response;

import java.util.List;

import Entities.Friendship;
import Entities.User;
import Response.Abstract.BaseResponse;

public class FriendsResponse extends BaseResponse {

    private List<User> friends;

    public FriendsResponse() {}

    public FriendsResponse(boolean success, List<User> friends) {
        super(success);
        setFriends(friends);
    }

    public FriendsResponse(boolean success, String message) {
        super(success, message);
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
