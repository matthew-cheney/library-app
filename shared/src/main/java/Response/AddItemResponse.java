package Response;

import Response.Abstract.BaseResponse;

public class AddItemResponse extends BaseResponse {

    public AddItemResponse() {}

    public AddItemResponse(boolean success) {
        super(success);
    }

    public AddItemResponse(boolean success, String message) {
        super(success, message);
    }

    public AddItemResponse(boolean success, int errorCode, String message) {
        super(success, errorCode, message);
    }
}
