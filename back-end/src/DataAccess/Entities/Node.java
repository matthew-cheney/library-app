package DataAccess.Entities;

import DataAccess.Enums.NodeType;
import DataAccess.Utilities.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Node {

    // region Required Properties

    private String id;
    private NodeType nodeType;
    private boolean available;
    private String title;

    // endregion

    // region Foreign Keys

    private String ownerId;

    // endregion

    // region Optional Properties

    private String description;
    private String author;
    private String releaseYear;

    // endregion

    // region Constructors

    public Node(@NotNull NodeType nodeType, boolean available, @NotNull String title, @NotNull String ownerId,
                @Nullable String description, @Nullable String author, @Nullable String releaseYear) {
        id = EntityUtils.generateId();

        setNodeType(nodeType);
        setAvailable(available);
        setTitle(title);

        this.ownerId = ownerId; // readonly property, no setter available

        setDescription(description);
        setAuthor(author);
        setReleaseYear(releaseYear);
    }

    // endregion

    // region Getters

    public String getId() {
        return id;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getTitle() {
        return title;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    // endregion

    // region Setters

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    // endregion
}
