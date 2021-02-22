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

    // region Overrides

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final RegisterResponse other = (RegisterResponse) obj;

        return this.isSuccess() == other.isSuccess() &&
                EntityUtils.checkNullableObjects(this.getMessage(), other.getMessage());
    }

    // endregion
}
