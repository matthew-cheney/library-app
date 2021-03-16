package Request;

import Entities.Friendship;
import Request.Abstract.FriendRequest;

public class RemoveFriendRequest extends FriendRequest {

    public RemoveFriendRequest() {}

    public RemoveFriendRequest(Friendship friendship) {
        setFriendship(friendship);
    }
}
