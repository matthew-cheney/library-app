package Response;

import Response.Abstract.BaseResponse;
import Utilities.EntityUtils;

public class RegisterResponse extends BaseResponse {

    public RegisterResponse() {}

    public RegisterResponse(boolean success) {
        super(success);
    }

    public RegisterResponse(boolean success, String message) {
        super(success, message);
    }
}
