package Response.Abstract;

import Response.RegisterResponse;
import Utilities.EntityUtils;

public abstract class BaseResponse {

    private boolean success;
    private String message;

    /**
     * Empty constructor to work with the lambdas
     */
    public BaseResponse() {}

    /**
     * Typically for a successful operation - no message will be necessary
     * @param success
     */
    public BaseResponse(boolean success) {
        setSuccess(success);
    }

    /**
     * Typically for a failed operation - an error message will be included
     * @param success
     * @param message
     */
    public BaseResponse(boolean success, String message) {
        setSuccess(success);
        setMessage(message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
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
