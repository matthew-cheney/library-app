package Entities;

import Utilities.EntityUtils;

import java.util.Map;

public class User {

    // region Required Properties

    private String id;
    private String username;
    private String passwordHash;
    private String passwordSalt;
    private String firstName;
    private String lastName;

    // endregion

    // region Optional Properties

    private String email;
    private String phoneNumber;
    private String imageUrl;

    // endregion

    // region Constructors

    /**
     * This is a constructor to use when receiving a user from the client
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     * @param imageUrl
     */
    public User(String username, String password, String firstName, String lastName,
                 String email, String phoneNumber, String imageUrl) {
        id = EntityUtils.generateId();

        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);

        setEmail(email);
        setPhoneNumber(phoneNumber);
        setImageUrl(imageUrl);
    }

    /**
     * This is a constructor to use when retrieving a user from the database
     * @param id
     * @param username
     * @param passwordHash
     * @param passwordSalt
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     * @param imageUrl
     */
    public User(String id, String username, String passwordHash, String passwordSalt, String firstName, String lastName,
                String email, String phoneNumber, String imageUrl) {
        this.id = id;

        setUsername(username);

        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;

        setFirstName(firstName);
        setLastName(lastName);

        setEmail(email);
        setPhoneNumber(phoneNumber);
        setImageUrl(imageUrl);
    }

    // endregion

    // region Getters

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // endregion

    // region Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        Map<String, String> passwordPair = EntityUtils.hashPassword(password);
        assert(passwordPair.entrySet().size() == 1);
        for (Map.Entry<String, String> entry : passwordPair.entrySet()) {
            this.passwordSalt = entry.getKey();
            this.passwordHash = entry.getValue();
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

        final User other = (User) obj;

        return this.getId().equals(other.getId()) &&
                this.getUsername().equals(other.getUsername()) &&
                this.getPasswordHash().equals(other.getPasswordHash()) &&
                this.getPasswordSalt().equals(other.getPasswordSalt()) &&
                this.getFirstName().equals(other.getFirstName()) &&
                this.getLastName().equals(other.getLastName()) &&
                EntityUtils.checkNullableObjects(this.getEmail(), other.getEmail()) &&
                EntityUtils.checkNullableObjects(this.getPhoneNumber(), other.getPhoneNumber()) &&
                EntityUtils.checkNullableObjects(this.getImageUrl(), other.getImageUrl());
    }

    // endregion
}
