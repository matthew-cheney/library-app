package com.example.libraryofpeers.presenters;

import android.os.Build;

import androidx.annotation.RequiresApi;

//import com.example.libraryofpeers.service_proxy.GetFriendsServiceProxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entities.User;
import Request.FriendsRequest;
import Response.FriendsResponse;

public class FriendsPresenter {

    private final FriendsPresenter.View view;

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
        users.add(new User(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
        users.add(new User(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
        users.add(new User(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
        users.add(new User(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
        users.add(new User(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
        users.add(new User(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
        users.add(new User(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
        users.add(new User(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
        users.add(new User(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
        users.add(new User(getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
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
//    GetFriendsServiceProxy getFriendsService() {
//        return new GetFriendsServiceProxy();
//    }
}
