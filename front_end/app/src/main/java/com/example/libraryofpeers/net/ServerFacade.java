package com.example.libraryofpeers.net;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import Request.AddFriendRequest;
import Request.AddItemRequest;
import Request.CatalogRequest;
import Request.DeleteItemRequest;
import Request.EditItemRequest;
import Request.EditUserRequest;
import Request.LoginRequest;
import Request.RegisterRequest;
import Request.RemoveFriendRequest;
import Request.SearchItemsRequest;
import Request.SearchUsersRequest;
import Response.AddFriendResponse;
import Response.AddItemResponse;
import Response.CatalogResponse;
import Response.DeleteItemResponse;
import Response.EditItemResponse;
import Response.EditUserResponse;
import Response.LoginResponse;
import Response.RegisterResponse;
import Response.RemoveFriendResponse;
import Response.SearchItemsResponse;
import Response.SearchUsersResponse;

public class ServerFacade {
    private static final String SERVER_URL = "https://uvxkvq54nl.execute-api.us-east-2.amazonaws.com/beta";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public EditUserResponse editUser(EditUserRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, EditUserResponse.class);
    }

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public AddItemResponse addItem(AddItemRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, AddItemResponse.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public EditItemResponse editItem(EditItemRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, EditItemResponse.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public CatalogResponse getCatalog(CatalogRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, CatalogResponse.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public DeleteItemResponse deleteItem(DeleteItemRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, DeleteItemResponse.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public SearchItemsResponse searchItems(SearchItemsRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, SearchItemsResponse.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public SearchUsersResponse searchUsers(SearchUsersRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, SearchUsersResponse.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public AddFriendResponse addFriend(AddFriendRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, AddFriendResponse.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public RemoveFriendResponse removeFriend(RemoveFriendRequest request, String urlPath) throws IOException {
        ClientCommunicator communicator = new ClientCommunicator(SERVER_URL);
        return communicator.doPost(urlPath, request, null, RemoveFriendResponse.class);
    }

}
