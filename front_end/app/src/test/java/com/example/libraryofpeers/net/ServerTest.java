package com.example.libraryofpeers.net;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import Entities.User;
import Request.EditUserRequest;
import Request.LoginRequest;
import Request.RegisterRequest;
import Response.EditUserResponse;
import Response.LoginResponse;
import Response.RegisterResponse;

import static org.junit.Assert.*;

public class ServerTest {
    private ServerFacade serverFacade;
    private static final String REGISTER_URL = "/register";
    private static final String LOGIN_URL = "/login";
    private static final String EDIT_USER_URL = "/edituser";

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
        User update = new User(
                request.getUsername(),
                request.getPassword(),
                "updatedFirstName",
                "updatedLastName",
                "updatedEmail",
                "updatedPhone",
                "updatedURL"
        );
        EditUserResponse editUserResponse = serverFacade.editUser(new EditUserRequest(update), EDIT_USER_URL);
        assertTrue(editUserResponse.getMessage(), editUserResponse.isSuccess());
        LoginResponse loginResponse = serverFacade.login(new LoginRequest(request.getUsername(), request.getPassword()), LOGIN_URL);
        assertEquals(update, loginResponse.getUser());
    }
}
