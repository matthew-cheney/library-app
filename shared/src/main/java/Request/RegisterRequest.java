package Request;

import Utilities.EntityUtils;

public class RegisterRequest {

    // region Properties

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String imageUrl;

    // endregion

    // region Constructors

    public RegisterRequest() {}

    public RegisterRequest(String username, String password, String firstName, String lastName,
                           String email, String phoneNumber, String imageUrl) {
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setImageUrl(imageUrl);
    }

    // endregion

    // region Getters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
        this.password = password;
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

        final RegisterRequest other = (RegisterRequest) obj;

        return this.getUsername().equals(other.getUsername()) &&
                this.getPassword().equals(other.getPassword()) &&
                this.getFirstName().equals(other.getFirstName()) &&
                this.getLastName().equals(other.getLastName()) &&
                EntityUtils.checkNullableObjects(this.getEmail(), other.getEmail()) &&
                EntityUtils.checkNullableObjects(this.getPhoneNumber(), other.getPhoneNumber()) &&
                EntityUtils.checkNullableObjects(this.getImageUrl(), other.getImageUrl());
    }

    // endregion
}
