package Entities;

import Utilities.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {

    // region Properties

    public transient final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    // region Required

    private String id;
    private String title;
    private String category;
    private String dateCreated;
    private boolean available;

    // endregion

    // region Foreign Keys

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

    public Item() {}

    /**
     * This is a constructor to use when retrieving an item from the client
     * @param title
     * @param category
     * @param available
     * @param ownerId
     * @param imageUrl
     * @param description
     * @param numPlayers
     * @param timeToPlayInMins
     * @param releaseYear
     * @param genre
     * @param itemFormat
     * @param author
     */
    public Item(String title, String category, boolean available, String ownerId,
                String imageUrl, String description, Integer numPlayers, Integer timeToPlayInMins, Integer releaseYear,
                String genre, String itemFormat, String author) {
        setId(EntityUtils.generateId());
        setTitle(title);
        setCategory(category);
        setDateCreated(DATE_FORMAT.format(new Date()));
        setAvailable(available);
        setOwnerId(ownerId);
        setImageUrl(imageUrl);
        setDescription(description);
        if (numPlayers != null) setNumPlayers(numPlayers);
        if (timeToPlayInMins != null) setTimeToPlayInMins(timeToPlayInMins);
        if (releaseYear != null) setReleaseYear(releaseYear);
        if (genre != null) setGenre(genre);
        if (itemFormat != null) setItemFormat(itemFormat);
        if (author != null) setAuthor(author);
    }

    /**
     * This is a constructor to use when retrieving an item from the database
     * @param id
     * @param title
     * @param category
     * @param dateCreated
     * @param available
     * @param ownerId
     * @param imageUrl
     * @param description
     * @param numPlayers
     * @param timeToPlayInMins
     * @param releaseYear
     * @param genre
     * @param itemFormat
     * @param author
     */
    public Item(String id, String title, String category, String dateCreated, boolean available, String ownerId,
                String imageUrl, String description, Integer numPlayers, Integer timeToPlayInMins, Integer releaseYear,
                String genre, String itemFormat, String author) {
        setId(id);
        setTitle(title);
        setCategory(category);
        setDateCreated(dateCreated);
        setAvailable(available);
        setOwnerId(ownerId);
        setImageUrl(imageUrl);
        setDescription(description);
        if (numPlayers != null) setNumPlayers(numPlayers);
        if (timeToPlayInMins != null) setTimeToPlayInMins(timeToPlayInMins);
        if (releaseYear != null) setReleaseYear(releaseYear);
        if (genre != null) setGenre(genre);
        if (itemFormat != null) setItemFormat(itemFormat);
        if (author != null) setAuthor(author);
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

    public String getDateCreated() {
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

    public String getItemFormat() {
        return itemFormat;
    }

    public String getAuthor() {
        return author;
    }

    // endregion

    // region Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
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

    // region Overrides

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Item other = (Item) obj;

        return this.getId().equals(other.getId()) &&
                this.getTitle().equals(other.getTitle()) &&
                this.getCategory().equals(other.getCategory()) &&
                this.getDateCreated().equals(other.getDateCreated()) &&
                this.isAvailable() == other.isAvailable() &&
                this.getOwnerId().equals(other.getOwnerId()) &&
                this.getImageUrl().equals(other.getImageUrl()) &&
                EntityUtils.checkNullableObjects(this.getDescription(), other.getDescription()) &&
                this.getNumPlayers() == other.getNumPlayers() &&
                this.getTimeToPlayInMins() == other.getTimeToPlayInMins() &&
                this.getReleaseYear() == other.getReleaseYear() &&
                EntityUtils.checkNullableObjects(this.getGenre(), other.getGenre()) &&
                EntityUtils.checkNullableObjects(this.getItemFormat(), other.getItemFormat()) &&
                EntityUtils.checkNullableObjects(this.getAuthor(), other.getAuthor());
    }

    // endregion

    // region FrontEndUtils

    public static Item getNullItem() {
        return new Item(null, null, false, null, null, null, 0, 0, 0, null, null, null);
    }

    // endregion
}
