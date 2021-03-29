package Request.Abstract;

import Entities.Friendship;

public abstract class FriendRequest {

    private Friendship friendship;

    public FriendRequest() {}

    public FriendRequest(Friendship friendship) {
        setFriendship(friendship);
    }

    public Friendship getFriendship() {
        return friendship;
    }

    public void setFriendship(Friendship friendship) {
        this.friendship = friendship;
    }
}
