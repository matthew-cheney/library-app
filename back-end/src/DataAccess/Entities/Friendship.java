package DataAccess.Entities;

import DataAccess.Utilities.EntityUtils;
import org.jetbrains.annotations.NotNull;

public class Friendship {

    // region Properties

    private String id;
    private String userIdA;
    private String userIdB;

    // endregion

    // region Constructors

    public Friendship(@NotNull String userIdA, @NotNull String userIdB) {
        id = EntityUtils.generateId();

        if (EntityUtils.idAIsLessThanIdB(userIdA, userIdB)) {
            this.userIdA = userIdA;
            this.userIdB = userIdB;
        }
        else {
            this.userIdA = userIdB;
            this.userIdB = userIdA;
        }
    }

    // endregion

    // region Getters

    public String getId() {
        return id;
    }

    public String getUserIdA() {
        return userIdA;
    }

    public String getUserIdB() {
        return userIdB;
    }

    // endregion
}
