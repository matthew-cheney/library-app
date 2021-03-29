package Request;

import Entities.Friendship;
import Request.Abstract.FriendRequest;

public class AddFriendRequest extends FriendRequest {

    public AddFriendRequest() {}

    public AddFriendRequest(Friendship friendship) {
        setFriendship(friendship);
    }
}
