package DataAccess.Entities;

import DataAccess.Utilities.EntityUtils;
import org.jetbrains.annotations.NotNull;

public class ItemCategory {

    // region Required Properties

    private String id;
    private String name;

    // endregion

    // region Foreign Keys

    private String ownerId;

    // endregion

    // region Constructors

    public ItemCategory(@NotNull String name, @NotNull String ownerId) {
        id = EntityUtils.generateId();

        setName(name);

        this.ownerId = ownerId; // readonly property, no setter available
    }

    // endregion

    // region Getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    // endregion

    // region Setters

    public void setName(String name) {
        this.name = name;
    }

    // endregion
}
