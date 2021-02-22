package Request;

public class LoginRequest {

    // region Properties

    private String username;
    private String password;

    // endregion

    // region Constructors

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    // endregion

    // region Getters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // endregion

    // region Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // endregion
}
