package Entities;

import Utilities.EntityUtils;
import org.jetbrains.annotations.NotNull;

public class Friendship {

    // region Properties

    private String id;
    private String userIdA;
    private String userIdB;

    // endregion

    // region Constructors

    /**
     * To ensure we don't end up with duplicate friendships in the database,
     * this constructor sorts the userIds alphabetically before assigning them
     * to their interior properties.
     * @param userIdA
     * @param userIdB
     */
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

    public String getSortedUserIdA() {
        return userIdA;
    }

    public String getSortedUserIdB() {
        return userIdB;
    }

    // endregion
}
