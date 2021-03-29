package Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import DataAccess.DAO.MySql.MySqlUserDAO;
import Request.SearchUsersRequest;
import Response.SearchUsersResponse;
import TestUtils.BaseTest;
import TestUtils.DatabaseFiller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchUsersServiceTest extends BaseTest {

    private SearchUsersService service;
    private MySqlUserDAO dao;
    private SearchUsersRequest successfulRequest = new SearchUsersRequest("com", 3);
    private SearchUsersRequest failureRequest = new SearchUsersRequest("ERIN", 0);

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlUserDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

        service = Mockito.spy(SearchUsersService.class);
        Mockito.when(service.getUserDAO()).thenReturn(dao);

        DatabaseFiller.addUnitTestUsers(0, 25, dao);
    }

    @AfterEach
    public void tearDownTests() {
        dao.clearUsersTable();
    }

    @Test
    public void searchUsers_success() {
        SearchUsersResponse response = service.searchUsers(successfulRequest);
        assertTrue(response.isSuccess());
        assertEquals(2, response.getUsers().size());
    }

    @Test
    public void searchUsers_failure() {
        SearchUsersResponse response = service.searchUsers(failureRequest);
        assertEquals(0, response.getUsers().size());
    }
}
