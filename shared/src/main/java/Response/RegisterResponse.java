package Response;

import Response.Abstract.BaseResponse;

public class RegisterResponse extends BaseResponse {

    public RegisterResponse() {}

    public RegisterResponse(boolean success) {
        super(success);
    }

    public RegisterResponse(boolean success, String message) {
        super(success, message);
    }

    public RegisterResponse(boolean success, int errorCode, String message) {
        super(success, errorCode, message);
    }
}
