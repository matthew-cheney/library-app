package Request;

public class LogoutRequest {

    private String authToken;

    public LogoutRequest() {}

    public LogoutRequest(String authToken) {
        setAuthToken(authToken);
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
