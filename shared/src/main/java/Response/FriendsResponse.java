package Response;

import java.util.List;

import Entities.Friendship;
import Response.Abstract.BaseResponse;

public class FriendsResponse extends BaseResponse {

    private List<Friendship> friendships;

    public FriendsResponse() {}

    public FriendsResponse(boolean success, List<Friendship> friendships) {
        super(success);
        setFriendships(friendships);
    }

    public FriendsResponse(boolean success, String message) {
        super(success, message);
    }

    public List<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(List<Friendship> friendships) {
        this.friendships = friendships;
    }
}
