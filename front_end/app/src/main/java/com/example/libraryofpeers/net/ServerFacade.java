package com.example.libraryofpeers.net;

import java.io.IOException;

import Request.EditUserRequest;
import Request.LoginRequest;
import Request.RegisterRequest;
import Response.EditUserResponse;
import Response.LoginResponse;
import Response.RegisterResponse;

public class ServerFacade {
    private static final String SERVER_URL = "https://uvxkvq54nl.execute-api.us-east-2.amazonaws.com/beta";

    public EditUserResponse editUser(EditUserRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, EditUserResponse.class);
    }

    public LoginResponse login(LoginRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, LoginResponse.class);
    }

    public RegisterResponse register(RegisterRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, RegisterResponse.class);
    }
}
