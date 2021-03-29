package Response;

import Response.Abstract.BaseResponse;

public class EditItemResponse extends BaseResponse {

    public EditItemResponse() {}

    public EditItemResponse(boolean success) {
        super(success);
    }

    public EditItemResponse(boolean success, String message) {
        super(success, message);
    }

    public EditItemResponse(boolean success, int errorCode, String message) {
        super(success, errorCode, message);
    }
}
