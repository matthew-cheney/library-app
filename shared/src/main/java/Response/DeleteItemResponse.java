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

    public DeleteItemResponse(boolean success, int errorCode, String message) {
        super(success, errorCode, message);
    }
}
