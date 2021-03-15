package Request;

import Request.Abstract.PaginatedRequest;

public class FriendsRequest extends PaginatedRequest {

    private String userId;

    public FriendsRequest() {}

    public FriendsRequest(String ownerId, int offset) {
        setUserId(ownerId);
        setOffset(offset);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}