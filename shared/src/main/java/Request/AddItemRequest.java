package Request;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AddItemRequest {

    // region Properties

    // region Required

    private String title;
    private String category;
    private boolean available;
    private String ownerId;

    // endregion

    // region Optional

    private String imageUrl;
    private String description;
    private int numPlayers;
    private int timeToPlayInMins;
    private int releaseYear;
    private String genre;
    private String itemFormat;
    private String author;

    // endregion

    // endregion

    // region Constructors

    public AddItemRequest() {}

    /**
     * This is a constructor to use when retrieving a board game
     * @param title
     * @param category
     * @param available
     * @param ownerId
     * @param imageUrl
     * @param description
     * @param numPlayers
     * @param timeToPlayInMins
     */
    public AddItemRequest(@NotNull String title, @NotNull String category, boolean available,
                          @NotNull String ownerId, @Nullable String imageUrl, @Nullable String description,
                          @Nullable Integer numPlayers, @Nullable Integer timeToPlayInMins) {
        setTitle(title);
        setCategory(category);
        setAvailable(available);
        setOwnerId(ownerId);
        if (imageUrl != null) setImageUrl(imageUrl);
        if (description != null) setDescription(description);
        if (numPlayers != null) setNumPlayers(numPlayers);
        if (timeToPlayInMins != null) setTimeToPlayInMins(timeToPlayInMins);
    }

    /**
     * This is a constructor to use for a movie
     * @param title
     * @param category
     * @param available
     * @param ownerId
     * @param imageUrl
     * @param description
     * @param releaseYear
     * @param genre
     * @param itemFormat
     */
    public AddItemRequest(@NotNull String title, @NotNull String category, boolean available,
                          @NotNull String ownerId, @Nullable String imageUrl, @Nullable String description,
                          @Nullable Integer releaseYear, @Nullable String genre, @Nullable String itemFormat) {
        setTitle(title);
        setCategory(category);
        setAvailable(available);
        setOwnerId(ownerId);
        if (imageUrl != null) setImageUrl(imageUrl);
        if (description != null) setDescription(description);
        if (releaseYear != null) setReleaseYear(releaseYear);
        if (genre != null) setGenre(genre);
        if (itemFormat != null) setItemFormat(itemFormat);
    }

    /**
     * This is a constructor to use for a book
     * @param title
     * @param category
     * @param available
     * @param ownerId
     * @param imageUrl
     * @param description
     * @param releaseYear
     * @param genre
     * @param itemFormat
     * @param author
     */
    public AddItemRequest(@NotNull String title, @NotNull String category, boolean available,
                          @NotNull String ownerId, @Nullable String imageUrl, @Nullable String description,
                          @Nullable Integer releaseYear, @Nullable String genre, @Nullable String itemFormat,
                          @Nullable String author) {
        setTitle(title);
        setCategory(category);
        setAvailable(available);
        setOwnerId(ownerId);
        if (imageUrl != null) setImageUrl(imageUrl);
        if (description != null) setDescription(description);
        if (releaseYear != null) setReleaseYear(releaseYear);
        if (genre != null) setGenre(genre);
        if (itemFormat != null) setItemFormat(itemFormat);
        if (author != null) setAuthor(author);
    }

    // endregion

    // region Getters

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getTimeToPlayInMins() {
        return timeToPlayInMins;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getItemFormat() {
        return itemFormat;
    }

    public String getAuthor() {
        return author;
    }

    // endregion

    // region Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setTimeToPlayInMins(int timeToPlayInMins) {
        this.timeToPlayInMins = timeToPlayInMins;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setItemFormat(String itemFormat) {
        this.itemFormat = itemFormat;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // endregion
}
