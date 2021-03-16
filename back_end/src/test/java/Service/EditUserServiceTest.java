package Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.EditUserRequest;
import Request.RegisterRequest;
import Response.EditUserResponse;
import TestUtils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditUserServiceTest extends BaseTest {

    private EditUserService service;
    private MySqlUserDAO dao;
    private User user;
    private EditUserRequest successfulRequest;
    private EditUserRequest failureRequest;
    private EditUserResponse successResponse = new EditUserResponse(true);
    private EditUserResponse failureResponse = new EditUserResponse(false, "Error updating user!");

    @BeforeEach
    public void setUpTests() {
        try {
            dao = Mockito.spy(MySqlUserDAO.class);
            Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

            service = Mockito.spy(EditUserService.class);
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

            user = dao.getUserByCredentials("dinosaursAreCool", "rossIsBest");
            successfulRequest = new EditUserRequest(user);
            User invalidUser = new User(
                    user.getUsername(),
                    "rossIsBest",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getImageUrl()
            );
            failureRequest = new EditUserRequest(invalidUser);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @AfterEach
    public void tearDownTests() {
        try {
            dao.deleteUser(user.getId());
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void editUser_success() {
        try {
            EditUserResponse response = service.editUser(successfulRequest);
            assertTrue(response.isSuccess());
            User resultingUser = dao.getUserById(user.getId());
            assertEquals(user, resultingUser);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void editUser_failure() {
        EditUserResponse response = service.editUser(failureRequest);
        assertEquals(failureResponse, response);
    }
}
