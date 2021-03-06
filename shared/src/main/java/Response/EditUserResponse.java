package Response;

import Response.Abstract.BaseResponse;

public class EditUserResponse extends BaseResponse {

    public EditUserResponse() {}

    public EditUserResponse(boolean success) {
        super(success);
    }

    public EditUserResponse(boolean success, String message) {
        super(success, message);
    }

    public EditUserResponse(boolean success, int errorCode, String message) {
        super(success, errorCode, message);
    }
}
