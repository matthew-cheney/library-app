package DataAccess.Entities;

import DataAccess.Utilities.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public class Item {

    // region Required Properties

    private String id;
    private String title;
    private String category;
    private Date dateCreated;
    private boolean available;

    // endregion

    // region Foreign Keys

    private String ownerId;

    // endregion

    // region Optional Properties

    private String imageUrl;
    private String description;
    private int numPlayers;
    private int timeToPlayInMins;
    private int releaseYear;
    private String genre;
    private String format;
    private String author;

    // endregion

    // region Constructors

    /**
     * This is the constructor for a board game
     * @param title
     * @param available
     * @param ownerId
     * @param imageUrl
     * @param description
     * @param numPlayers
     * @param timeToPlayInMins
     */
    public Item(@NotNull String title, boolean available, @NotNull String ownerId, @Nullable String imageUrl,
                @Nullable String description, @Nullable Integer numPlayers, @Nullable Integer timeToPlayInMins) {
        id = EntityUtils.generateId();

        setTitle(title);
        setCategory("BOARD_GAME");
        setAvailable(available);

        this.dateCreated = new Date(); // readonly property, no setter available
        this.ownerId = ownerId; // readonly property, no setter available

        setImageUrl(imageUrl);
        setDescription(description);
        if (numPlayers != null) setNumPlayers(numPlayers);
        if (timeToPlayInMins != null) setTimeToPlayInMins(timeToPlayInMins);
    }

    /**
     * This is the constructor for a movie
     * @param title
     * @param available
     * @param ownerId
     * @param imageUrl
     * @param description
     * @param releaseYear
     * @param genre
     * @param format
     */
    public Item(@NotNull String title, boolean available, @NotNull String ownerId, @Nullable String imageUrl,
                @Nullable String description, @Nullable Integer releaseYear, @Nullable String genre, @Nullable String format) {
        id = EntityUtils.generateId();

        setTitle(title);
        setCategory("MOVIE");
        setAvailable(available);

        this.dateCreated = new Date(); // readonly property, no setter available
        this.ownerId = ownerId; // readonly property, no setter available

        setImageUrl(imageUrl);
        setDescription(description);
        if (releaseYear != null) setReleaseYear(releaseYear);
        setGenre(genre);
        setFormat(format);
    }

    /**
     * This is the constructor for a book
     * @param title
     * @param available
     * @param ownerId
     * @param imageUrl
     * @param description
     * @param releaseYear
     * @param genre
     * @param format
     * @param author
     */
    public Item(@NotNull String title, boolean available, @NotNull String ownerId, @Nullable String imageUrl,
                @Nullable String description, @Nullable Integer releaseYear, @Nullable String genre, @Nullable String format,
                @Nullable String author) {
        id = EntityUtils.generateId();

        setTitle(title);
        setCategory("BOOK");
        setAvailable(available);

        this.dateCreated = new Date(); // readonly property, no setter available
        this.ownerId = ownerId; // readonly property, no setter available

        setImageUrl(imageUrl);
        setDescription(description);
        if (releaseYear != null) setReleaseYear(releaseYear);
        setGenre(genre);
        setFormat(format);
        setAuthor(author);
    }

    // endregion

    // region Getters

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Date getDateCreated() {
        return dateCreated;
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

    public String getFormat() {
        return format;
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

    public void setFormat(String format) {
        this.format = format;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // endregion
}
