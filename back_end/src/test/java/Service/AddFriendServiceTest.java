package Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.MySql.MySqlFriendshipDAO;
import Entities.Friendship;
import Request.AddFriendRequest;
import Response.AddFriendResponse;
import TestUtils.BaseTest;
import TestUtils.TestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddFriendServiceTest extends BaseTest {

    private AddFriendService service;
    private MySqlFriendshipDAO dao;
    private String userIdA = "APPLES";
    private String userIdB = "BUBBLES";
    private AddFriendResponse successResponse = new AddFriendResponse(true);
    private AddFriendResponse failureResponseA = new AddFriendResponse(false, "Friendship already exists!");
    private AddFriendResponse failureResponseB = new AddFriendResponse(false, "You cannot befriend yourself!");

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlFriendshipDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

        service = Mockito.spy(AddFriendService.class);
        Mockito.when(service.getFriendshipDAO()).thenReturn(dao);
    }

    @AfterEach
    public void tearDownTests() {
        try {
            List<String> friendIds = dao.getFriendIdsOfUser(userIdA, TestConfig.TEST_OFFSET);
            if (friendIds.size() != 0) dao.deleteFriendship(new Friendship(userIdA, friendIds.get(0)));
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void addFriend_success() {
        AddFriendRequest request = new AddFriendRequest(new Friendship(userIdA, userIdB));
        AddFriendResponse response = service.addFriend(request);
        assertTrue(response.isSuccess());
    }

    @Test
    public void addFriend_failureA() {
        AddFriendRequest request = new AddFriendRequest(new Friendship(userIdA, userIdB));
        service.addFriend(request);
        AddFriendResponse response = service.addFriend(request);
        assertFalse(response.isSuccess());
        assertEquals(failureResponseA.getMessage(), response.getMessage());
    }

    @Test
    public void addFriend_failureB() {
        AddFriendRequest request = new AddFriendRequest(new Friendship(userIdA, userIdA));
        AddFriendResponse response = service.addFriend(request);
        assertFalse(response.isSuccess());
        assertEquals(failureResponseB.getMessage(), response.getMessage());
    }
}
