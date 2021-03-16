package Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.MySql.MySqlFriendshipDAO;
import Entities.Friendship;
import Request.RemoveFriendRequest;
import Response.RemoveFriendResponse;
import TestUtils.BaseTest;
import TestUtils.TestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveFriendServiceTest extends BaseTest {

    private RemoveFriendService service;
    private MySqlFriendshipDAO dao;
    private String userIdA = "APPLES";
    private String userIdB = "BUBBLES";
    private RemoveFriendResponse successResponse = new RemoveFriendResponse(true);
    private RemoveFriendResponse failureResponse = new RemoveFriendResponse(false, "Error deleting friendship!");

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlFriendshipDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

        service = Mockito.spy(RemoveFriendService.class);
        Mockito.when(service.getFriendshipDAO()).thenReturn(dao);

        try {
            dao.addFriendship(new Friendship(userIdA, userIdB));
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @AfterEach
    public void tearDownTests() {
        try {
            List<String> friendIds = dao.getFriendIdsOfUser(userIdA, TestConfig.TEST_OFFSET);
            if (friendIds.size() != 0) dao.deleteFriendship(new Friendship(userIdA, friendIds.get(0)));
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void removeFriend_success() {
        RemoveFriendRequest request = new RemoveFriendRequest(new Friendship(userIdA, userIdB));
        RemoveFriendResponse response = service.removeFriend(request);
        assertTrue(response.isSuccess());
    }

    @Test
    public void removeFriend_failure() {
        RemoveFriendRequest request = new RemoveFriendRequest(new Friendship("ERIN", userIdB));
        RemoveFriendResponse response = service.removeFriend(request);
        assertFalse(response.isSuccess());
        assertEquals(failureResponse.getMessage(), response.getMessage());
    }
}
