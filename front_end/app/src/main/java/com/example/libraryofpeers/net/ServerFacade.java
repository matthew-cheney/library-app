package com.example.libraryofpeers.net;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import Request.LoginRequest;
import Request.RegisterRequest;
import Response.LoginResponse;
import Response.RegisterResponse;

public class ServerFacade {
    private static final String SERVER_URL = "https://uvxkvq54nl.execute-api.us-east-2.amazonaws.com/beta";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public LoginResponse login(LoginRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, LoginResponse.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public RegisterResponse register(RegisterRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, RegisterResponse.class);
    }
}
