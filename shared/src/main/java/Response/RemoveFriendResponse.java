package Response;

import Response.Abstract.BaseResponse;

public class RemoveFriendResponse extends BaseResponse {

    public RemoveFriendResponse() {}

    public RemoveFriendResponse(boolean success) {
        super(success);
    }

    public RemoveFriendResponse(boolean success, String message) {
        super(success, message);
    }
}
