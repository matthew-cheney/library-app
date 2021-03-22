package Response.Abstract;

import Utilities.EntityUtils;

public abstract class BaseResponse {

    private boolean success;
    private int errorCode;
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

    public int getErrorCode() {
        return errorCode;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
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

        final BaseResponse other = (BaseResponse) obj;

        return this.isSuccess() == other.isSuccess() &&
                EntityUtils.checkNullableObjects(this.getMessage(), other.getMessage());
    }

    // endregion
}
