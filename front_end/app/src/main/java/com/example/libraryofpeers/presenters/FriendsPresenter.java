package com.example.libraryofpeers.presenters;

import android.os.Build;

import androidx.annotation.RequiresApi;

//import com.example.libraryofpeers.service_proxy.GetFriendsServiceProxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Entities.User;
import Request.FriendsRequest;
import Response.FriendsResponse;

public class FriendsPresenter {

    private final FriendsPresenter.View view;

    private List<String> first_names = Arrays.asList("Matthew", "John", "Mark", "Will", "Adrian", "Jessica", "Sarah", "Kathryn", "Jane", "Taylor",
            "James", "Fred", "Elanor", "Whitney", "Jenni", "Landon", "Kai", "Peter", "Harry", "Tanya");
    private List<String> last_names = Arrays.asList("Cheney", "Potter", "Pucey", "Steinacker", "Farrell", "Jones", "Johnson", "Chestnut", "Dyer",
            "Stark", "Jackson", "Pickup", "Peterson", "Davis", "Matthews", "Gibbs", "Sterzer", "Ditty", "Teuscher", "Bryson", "Miller");
    private List<String> email_sites = Arrays.asList("gmail.com", "gmail.com", "gmail.com", "outlook.com", "yahoo.com", "byu.edu");
    private List<String> image_urls = Arrays.asList("https://cheneycreations.com/hairballs/cat1.png", "https://cheneycreations.com/hairballs/cat2.png",
            "https://cheneycreations.com/hairballs/cat3.png", "https://cheneycreations.com/hairballs/cat4.png",
            "https://cheneycreations.com/hairballs/cat5.png");

    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public FriendsPresenter(FriendsPresenter.View view) {
        this.view = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public FriendsResponse getFriends(FriendsRequest request) throws IOException {
//        GetFriendsServiceProxy FriendsService = getFriendsService();
//        FriendsResponse response = FriendsService.getFriends(request);
//        return response;
        return getRandomFriends();
    }


    FriendsResponse getRandomFriends() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = getRandomChoice(first_names);
            String surname = getRandomChoice(last_names);
            String username = name + surname.charAt(0) + Integer.toString(getRandomInt());
            String email = username + "@" + getRandomChoice(email_sites);
            email = email.toLowerCase();
            users.add(new User(getRandomString(), username, getRandomString(), getRandomString(), name, surname, email, getRandomPhoneNumber(), getRandomChoice(image_urls)));
        }
        return new FriendsResponse(true, users);
    }

    String getRandomString() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    String getRandomChoice(List<String> strings) {
        Random random = new Random();
        return strings.get(random.nextInt(strings.size()));
    }

    int getRandomInt() {
        Random random = new Random();
        return random.nextInt(899) + 100;
    }

    String getRandomPhoneNumber() {
        Random random = new Random();
        return Integer.toString(random.nextInt(899) + 100) +
                Integer.toString(random.nextInt(899) + 100) +
                Integer.toString(random.nextInt(8999) + 1000);
    }
//    GetFriendsServiceProxy getFriendsService() {
//        return new GetFriendsServiceProxy();
//    }
}
