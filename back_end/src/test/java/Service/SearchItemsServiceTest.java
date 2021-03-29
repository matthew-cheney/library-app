package Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.MySql.MySqlItemDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.SearchItemsRequest;
import Request.SearchUsersRequest;
import Response.SearchItemsResponse;
import Response.SearchUsersResponse;
import TestUtils.BaseTest;
import TestUtils.DatabaseFiller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchItemsServiceTest extends BaseTest {

    private SearchItemsService service;
    private MySqlItemDAO dao;
    private MySqlUserDAO userDao;
    private SearchItemsRequest successfulRequest = new SearchItemsRequest("TEST", "com", 3);
    private SearchItemsRequest successfulRequestNoOwner = new SearchItemsRequest(null, "com", 3);
    private SearchItemsRequest failureRequest = new SearchItemsRequest("TEST", "ERIN", 0);
    private SearchItemsRequest failureRequestNoOwner = new SearchItemsRequest(null, "ERIN", 0);
    private SearchItemsRequest failureRequestInvalidOwner = new SearchItemsRequest("BUBBLES", "com", 0);

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlItemDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

        userDao = Mockito.spy(MySqlUserDAO.class);
        Mockito.when(userDao.getConnectionPool()).thenReturn(CONNECTION_POOL);

        service = Mockito.spy(SearchItemsService.class);
        Mockito.when(service.getItemDAO()).thenReturn(dao);
        Mockito.when(service.getUserDAO()).thenReturn(userDao);

        DatabaseFiller.addUnitTestItems("TEST", 0, 10, dao);
        DatabaseFiller.addUnitTestItems("BUBBLES", 10, 15, dao);
        try {
            User testUser = new User(
                    "TEST",
                    "dinosaursAreCool",
                    "RachelIsMyDreamGirl",
                    "Emma",
                    "Ross",
                    "Geller",
                    "rgeller@school.edu",
                    "8609483092",
                    "www.google.com"
            );
            userDao.addUser(testUser);
        }
        catch (DatabaseException ignored) {}
    }

    @AfterEach
    public void tearDownTests() {
        dao.clearItemsTable();
        try {
            userDao.deleteUser("TEST");
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void searchItems_success() {
        SearchItemsResponse response = service.searchItems(successfulRequest);
        assertTrue(response.isSuccess());
        assertEquals(2, response.getItems().size());
    }

    @Test
    public void searchItemsNoOwner_success() {
        SearchItemsResponse response = service.searchItems(successfulRequestNoOwner);
        assertTrue(response.isSuccess());
        assertEquals(7, response.getItems().size());
    }

    @Test
    public void searchItems_failure() {
        SearchItemsResponse response = service.searchItems(failureRequest);
        assertEquals(0, response.getItems().size());
    }

    @Test
    public void searchItemsNoOwner_failure() {
        SearchItemsResponse response = service.searchItems(failureRequestNoOwner);
        assertEquals(0, response.getItems().size());
    }

    @Test
    public void searchItemsInvalidOwner_failure() {
        SearchItemsResponse response = service.searchItems(failureRequestInvalidOwner);
        assertFalse(response.isSuccess());
    }
}
