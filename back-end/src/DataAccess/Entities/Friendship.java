package DataAccess.Entities;

import org.jetbrains.annotations.NotNull;

public class Friendship {

    // region Properties

    private String userIdA;
    private String userIdB;

    // endregion

    // region Constructors

    public Friendship(@NotNull String userIdA, @NotNull String userIdB) {
        this.userIdA = userIdA;
        this.userIdB = userIdB;
    }

    // endregion

    // region Getters

    public String getUserIdA() {
        return userIdA;
    }

    public String getUserIdB() {
        return userIdB;
    }

    // endregion
}
