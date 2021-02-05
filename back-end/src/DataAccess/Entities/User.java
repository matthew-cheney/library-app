package DataAccess.Entities;

import DataAccess.Utilities.EntityUtils;

public class User {

    private String id;
    private String username;
    private String passwordHash;
    private String passwordSalt;
    private String firstName;
    private String lastName;
    private boolean isFemale; // gender
    private String email;
    private String phoneNumber;

    public User(String username, String password, String firstName, String lastName,
                boolean isFemale, String email, String phoneNumber) {
        id = EntityUtils.generateId();

        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(isFemale);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

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

    public boolean getGender() {
        return isFemale;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

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

    public void setGender(boolean isFemale) {
        isFemale = isFemale;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
