package Entities;

import Utilities.EntityUtils;
import org.jetbrains.annotations.NotNull;

public class ItemCategory {

    // region Required Properties

    private String id;
    private String categoryName;

    // endregion

    // region Foreign Keys

    private String ownerId;

    // endregion

    // region Constructors

    public ItemCategory() {}

    public ItemCategory(@NotNull String categoryName, @NotNull String ownerId) {
        id = EntityUtils.generateId();

        setCategoryName(categoryName);

        this.ownerId = ownerId; // readonly property, no setter available
    }

    // endregion

    // region Getters

    public String getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    // endregion

    // region Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    // endregion
}
