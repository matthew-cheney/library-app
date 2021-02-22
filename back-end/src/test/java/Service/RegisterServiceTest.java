package Service;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.IUserDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.RegisterRequest;
import Response.RegisterResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTest {

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
        service = new RegisterService();
    }

    @AfterEach
    public void tearDownTests() {
        IUserDAO dao = new MySqlUserDAO();
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
