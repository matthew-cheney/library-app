package Service;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.RegisterRequest;
import Response.RegisterResponse;
import TestUtils.TestConfig;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTest {

    private RegisterService service;
    private MySqlUserDAO dao;
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
        dao = Mockito.spy(MySqlUserDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(TestConfig.CONNECTION_POOL);

        service = Mockito.spy(RegisterService.class);
        Mockito.when(service.getUserDAO()).thenReturn(dao);
    }

    @AfterEach
    public void tearDownTests() {
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
