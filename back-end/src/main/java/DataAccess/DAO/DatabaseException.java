package DataAccess.DAO;

public class DatabaseException extends Exception {

    private int errorCode;
    private String message;

    public DatabaseException(String message) {
        setMessage(message);
    }

    public DatabaseException(int errorCode, String message) {
        setErrorCode(errorCode);
        setMessage(message);
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
