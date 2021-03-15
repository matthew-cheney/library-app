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

    public Friendship() {}

    /**
     * To use when creating a friendship in the app.
     * To ensure we don't end up with duplicate friendships in the database,
     * this constructor uses the setters to sort the userIds alphabetically
     * before assigning them to their interior properties.
     * @param userIdA
     * @param userIdB
     */
    public Friendship(@NotNull String userIdA, @NotNull String userIdB) {
        setId(EntityUtils.generateId());
        setUserIdA(userIdA);
        setUserIdB(userIdB);
    }

    /**
     * To use when getting a friendship from the database.
     * @param id
     * @param userIdA
     * @param userIdB
     */
    public Friendship(String id, String userIdA, String userIdB) {
        setId(id);
        setUserIdA(userIdA);
        setUserIdB(userIdB);
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

    // region Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setUserIdA(String userIdA) {
        if (this.userIdB != null) {
            if (!EntityUtils.idAIsLessThanIdB(userIdA, this.userIdB)) {
                this.userIdA = this.userIdB;
                this.userIdB = userIdA;
                return;
            }
        }

        this.userIdA = userIdA;
    }

    public void setUserIdB(String userIdB) {
        if (this.userIdA != null) {
            if (!EntityUtils.idAIsLessThanIdB(this.userIdA, userIdB)) {
                this.userIdB = this.userIdA;
                this.userIdA = userIdB;
                return;
            }
        }

        this.userIdB = userIdB;
    }

    // endregion
}
