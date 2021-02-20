package Response.Abstract;

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
}
