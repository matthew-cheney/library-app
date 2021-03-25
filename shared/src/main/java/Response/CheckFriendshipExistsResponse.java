package Response;

import Response.Abstract.BaseResponse;

public class CheckFriendshipExistsResponse extends BaseResponse {

    private boolean friendshipExists;

    public CheckFriendshipExistsResponse() {}

    public CheckFriendshipExistsResponse(boolean success, boolean friendshipExists) {
        super(success);
        setFriendshipExists(friendshipExists);
    }

    public CheckFriendshipExistsResponse(boolean success, String message) {
        super(success, message);
    }

    public CheckFriendshipExistsResponse(boolean success, int errorCode, String message) {
        super(success, errorCode, message);
    }

    public boolean isFriendshipExists() {
        return friendshipExists;
    }

    public void setFriendshipExists(boolean friendshipExists) {
        this.friendshipExists = friendshipExists;
    }
}
