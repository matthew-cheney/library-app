package Request;

public class AddFriendRequest {

    private String userIdA;
    private String userIdB;

    public AddFriendRequest() {}

    public AddFriendRequest(String userIdA, String userIdB) {
        setUserIdA(userIdA);
        setUserIdB(userIdB);
    }

    public String getUserIdA() {
        return userIdA;
    }

    public String getUserIdB() {
        return userIdB;
    }

    public void setUserIdA(String userIdA) {
        this.userIdA = userIdA;
    }

    public void setUserIdB(String userIdB) {
        this.userIdB = userIdB;
    }
}
