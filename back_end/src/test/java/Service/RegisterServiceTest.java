package Service;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.IUserDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.RegisterRequest;
import Response.RegisterResponse;
import TestUtils.BaseTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTest extends BaseTest {

    private RegisterService service;
    private RegisterRequest request = new RegisterRequest(
            "dinosaursAreCool",
            "rossIsBest",
            "Ross",
            "Geller",
            "rgeller@school.edu",
            "8609483092",
            "www.google.com"
    );
    private RegisterResponse successfulResponse = new RegisterResponse(true);
    private RegisterResponse failureResponse =
            new RegisterResponse(false, "Duplicate entry 'dinosaursAreCool' for key 'Username'");

    @BeforeEach
    public void setUpTests() {
        service = Mockito.spy(RegisterService.class);
        Mockito.when(service.getUserDAO()).thenReturn(new MySqlUserDAO(connectionPool));
    }

    @AfterEach
    public void tearDownTests() {
        IUserDAO dao = new MySqlUserDAO(connectionPool);
        try {
            User user = dao.getUserByCredentials(request.getUsername(), request.getPassword());
            dao.deleteUser(user.getId());
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void register_success() {
        RegisterResponse response = service.register(request);
        assertEquals(successfulResponse, response);
    }

    @Test void register_failure() {
        service.register(request);
        RegisterResponse response = service.register(request);
        assertEquals(failureResponse, response);
    }
}
