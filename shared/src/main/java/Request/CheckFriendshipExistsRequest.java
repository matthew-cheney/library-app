package Request;

import Entities.Friendship;
import Request.Abstract.FriendRequest;

public class CheckFriendshipExistsRequest extends FriendRequest {

    public CheckFriendshipExistsRequest() {}

    public CheckFriendshipExistsRequest(Friendship friendship) {
        setFriendship(friendship);
    }
}
