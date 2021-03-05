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
}
