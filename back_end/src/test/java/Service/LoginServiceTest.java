package Service;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.LoginRequest;
import Request.RegisterRequest;
import Response.LoginResponse;
import TestUtils.TestConfig;
import Utilities.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private LoginService service;
    private MySqlUserDAO dao;
    private LoginRequest successfulRequest = new LoginRequest("dinosaursAreCool", "rossIsBest");
    private LoginRequest failureUsernameRequest = new LoginRequest("false", "credentials");
    private LoginRequest failurePasswordRequest = new LoginRequest("dinosaursAreCool", "credentials");
    private LoginResponse successfulResponse = new LoginResponse(
            true,
            new User(
                    "TEST",
                    "dinosaursAreCool",
                    null,
                    null,
                    "Ross",
                    "Geller",
                    "rgeller@school.edu",
                    "8609483092",
                    "www.google.com"
            ));
    private LoginResponse invalidUsernameResponse = new LoginResponse(false, "Invalid Username!");
    private LoginResponse invalidPasswordResponse = new LoginResponse(false, "Invalid Password!");

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlUserDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(TestConfig.connectionPool);

        service = Mockito.spy(LoginService.class);
        Mockito.when(service.getUserDAO()).thenReturn(dao);

        RegisterService registerService = Mockito.spy(RegisterService.class);
        Mockito.when(registerService.getUserDAO()).thenReturn(dao);

        RegisterRequest request = new RegisterRequest(
                "dinosaursAreCool",
                "rossIsBest",
                "Ross",
                "Geller",
                "rgeller@school.edu",
                "8609483092",
                "www.google.com"
        );
        registerService.register(request);
    }

    @AfterEach
    public void tearDownTests() {
        try {
            User user = dao.getUserByCredentials(successfulRequest.getUsername(), successfulRequest.getPassword());
            dao.deleteUser(user.getId());
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void login_success() {
        LoginResponse response = service.login(successfulRequest);
        assertTrue(response.isSuccess());
        assertTrue(equalsNoPasswordOrId(response.getUser(), successfulResponse.getUser()));
    }

    @Test
    public void login_failure_invalidUsername() {
        LoginResponse response = service.login(failureUsernameRequest);
        assertEquals(invalidUsernameResponse, response);
    }

    @Test
    public void login_failure_invalidPassword() {
        LoginResponse response = service.login(failurePasswordRequest);
        assertEquals(invalidPasswordResponse, response);
    }

    private boolean equalsNoPasswordOrId(User expected, User actual) {
        return expected.getUsername().equals(actual.getUsername()) &&
                expected.getFirstName().equals(actual.getFirstName()) &&
                expected.getLastName().equals(actual.getLastName()) &&
                EntityUtils.checkNullableObjects(expected.getEmail(), actual.getEmail()) &&
                EntityUtils.checkNullableObjects(expected.getPhoneNumber(), actual.getPhoneNumber()) &&
                EntityUtils.checkNullableObjects(expected.getImageUrl(), actual.getImageUrl());
    }
}
