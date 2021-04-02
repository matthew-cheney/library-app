package com.example.libraryofpeers.net;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import Entities.Item;
import Entities.User;
import Request.AddItemRequest;
import Request.CatalogRequest;
import Request.EditItemRequest;
import Request.EditUserRequest;
import Request.LoginRequest;
import Request.RegisterRequest;
import Response.AddItemResponse;
import Response.CatalogResponse;
import Response.EditItemResponse;
import Response.EditUserResponse;
import Response.LoginResponse;
import Response.RegisterResponse;

import static org.junit.Assert.*;

public class ServerTest {
    private ServerFacade serverFacade;
    private static final String REGISTER_URL = "/register";
    private static final String LOGIN_URL = "/login";
    private static final String EDIT_USER_URL = "/edituser";
    private static final String ADD_ITEM_URL = "/additem";
    private static final String EDIT_ITEM_URL = "/edititem";
    private static final String CATALOG_URL = "/catalog";


    private RegisterRequest request;

    @Before
    public void before() {
        serverFacade = new ServerFacade();
        request = new RegisterRequest(
                UUID.randomUUID().toString(),
                "testPassword",
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPhoneNumber",
                "testImageURL"
        );
    }

    @Test
    public void testRegister() throws IOException {
        RegisterResponse response = serverFacade.register(request, REGISTER_URL);
        assertTrue(response.isSuccess());
    }

    @Test
    public void testLogin() throws IOException {
        RegisterResponse response = serverFacade.register(request, REGISTER_URL);
        assertTrue(response.isSuccess());
        LoginResponse loginResponse = serverFacade.login(new LoginRequest(request.getUsername(), request.getPassword()), LOGIN_URL);
        assertTrue(loginResponse.isSuccess());
        assertEquals(request.getUsername(), loginResponse.getUser().getUsername());
    }

    @Test
    public void testEditProfile() throws IOException {
        RegisterResponse response = serverFacade.register(request, REGISTER_URL);
        assertTrue(response.isSuccess());
        // Login to get the user
        LoginResponse loginResponse = serverFacade.login(new LoginRequest(request.getUsername(), request.getPassword()), LOGIN_URL);
        assertTrue(loginResponse.isSuccess());
        assertEquals(request.getUsername(), loginResponse.getUser().getUsername());
        // Change the user a bit
        User update = loginResponse.getUser();
        update.setPhoneNumber("updatedPhone");
        update.setEmail("updatedEmail");
        EditUserResponse editUserResponse = serverFacade.editUser(new EditUserRequest(update), EDIT_USER_URL);
        assertTrue(editUserResponse.getMessage(), editUserResponse.isSuccess());
        LoginResponse loginResponse2 = serverFacade.login(new LoginRequest(request.getUsername(), request.getPassword()), LOGIN_URL);
        assertEquals(update, loginResponse2.getUser());
    }

    @Test
    public void testAddItem() throws IOException {
        RegisterResponse response = serverFacade.register(request, REGISTER_URL);
        assertTrue(response.isSuccess());
        LoginResponse loginResponse = serverFacade.login(new LoginRequest(request.getUsername(), request.getPassword()), LOGIN_URL);
        assertTrue(loginResponse.isSuccess());
        User user = loginResponse.getUser();
        AddItemRequest addItemRequest = new AddItemRequest(
                "testItem",
                "Movies",
                true,
                user.getId(),
                "testitem.com/pic.jpg",
                "a test item",
                null,
                null
        );
        AddItemResponse addItemResponse = serverFacade.addItem(addItemRequest, ADD_ITEM_URL);
        assertTrue(addItemResponse.getMessage(), addItemResponse.isSuccess());
    }

    @Test
    public void testEditItem() throws IOException {
        RegisterResponse response = serverFacade.register(request, REGISTER_URL);
        assertTrue(response.isSuccess());
        LoginResponse loginResponse = serverFacade.login(new LoginRequest(request.getUsername(), request.getPassword()), LOGIN_URL);
        assertTrue(loginResponse.isSuccess());
        User user = loginResponse.getUser();
        AddItemRequest addItemRequest = new AddItemRequest(
                "testItem",
                "Movies",
                true,
                user.getId(),
                "testitem.com/pic.jpg",
                "a test item",
                null,
                null
        );
        AddItemResponse addItemResponse = serverFacade.addItem(addItemRequest, ADD_ITEM_URL);
        assertTrue(addItemResponse.getMessage(), addItemResponse.isSuccess());
        EditItemRequest editItemRequest = new EditItemRequest(new Item(
                "54524f9f-2885-40bd-8a97-48c4967ec42b",
                "testItem",
                "Movies",
                "date created",
                true,
                user.getId(),
                "testitem.com/pic.jpg",
                UUID.randomUUID().toString(),
                null,
                null,
                null,
                "comedy",
                "DVD",
                null
        ));
        EditItemResponse editItemResponse = serverFacade.editItem(editItemRequest, EDIT_ITEM_URL);
        assertTrue(editItemResponse.getMessage(),editItemResponse.isSuccess());
    }

    @Test
    public void testGetCatalog() throws IOException {
        RegisterResponse response = serverFacade.register(request, REGISTER_URL);
        assertTrue(response.isSuccess());
        LoginResponse loginResponse = serverFacade.login(new LoginRequest(request.getUsername(), request.getPassword()), LOGIN_URL);
        assertTrue(loginResponse.isSuccess());
        User user = loginResponse.getUser();
        String itemName = UUID.randomUUID().toString();
        AddItemRequest addItemRequest = new AddItemRequest(
                itemName,
                "Movies",
                true,
                user.getId(),
                "testitem.com/pic.jpg",
                "a test item",
                null,
                null
        );
        AddItemResponse addItemResponse = serverFacade.addItem(addItemRequest, ADD_ITEM_URL);
        assertTrue(addItemResponse.getMessage(), addItemResponse.isSuccess());

        CatalogRequest request = new CatalogRequest(user.getId(), null, 0);
        CatalogResponse response1 = serverFacade.getCatalog(request, CATALOG_URL);
        assertEquals(1, response1.getItems().size());
        assertEquals(itemName, response1.getItems().get(0).getTitle());
    }
}
