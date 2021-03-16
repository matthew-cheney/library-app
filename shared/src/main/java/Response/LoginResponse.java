package Response;

import Entities.User;
import Response.Abstract.UserResponse;
import Utilities.EntityUtils;

public class LoginResponse extends UserResponse {

    public LoginResponse() {}

    public LoginResponse(boolean success, User user) {
        super(success, user);
    }

    public LoginResponse(boolean success, String message) {
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

        final LoginResponse other = (LoginResponse) obj;

        return this.isSuccess() == other.isSuccess() &&
                EntityUtils.checkNullableObjects(this.getMessage(), other.getMessage()) &&
                EntityUtils.checkNullableObjects(this.getUser(), other.getUser());
    }

    // endregion
}
