package Response;

public class RegisterResponse {

    private boolean success;
    private String message;

    public RegisterResponse() {}

    public RegisterResponse(boolean success) {
        setSuccess(success);
    }

    public RegisterResponse(boolean success, String message) {
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
