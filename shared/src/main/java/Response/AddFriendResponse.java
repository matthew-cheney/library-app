package Response;

import Response.Abstract.BaseResponse;

public class AddFriendResponse extends BaseResponse {

    public AddFriendResponse() {}

    public AddFriendResponse(boolean success) {
        super(success);
    }

    public AddFriendResponse(boolean success, String message) {
        super(success, message);
    }
}
