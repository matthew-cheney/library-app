package Response;

import Response.Abstract.BaseResponse;

public class LogoutResponse extends BaseResponse {

    public LogoutResponse() {}

    public LogoutResponse(boolean success) {
        super(success);
    }

    public LogoutResponse(boolean success, String message) {
        super(success, message);
    }

    public LogoutResponse(boolean success, int errorCode, String message) {
        super(success, errorCode, message);
    }
}
