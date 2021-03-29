package Response;

import Response.Abstract.BaseResponse;

public class DeleteItemResponse extends BaseResponse {

    public DeleteItemResponse() {}

    public DeleteItemResponse(boolean success) {
        super(success);
    }

    public DeleteItemResponse(boolean success, String message) {
        super(success, message);
    }
}
