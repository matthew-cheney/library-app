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
import Request.CheckFriendshipExistsRequest;
import Response.AddFriendResponse;
import Response.CheckFriendshipExistsResponse;
import TestUtils.BaseTest;
import TestUtils.TestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckFriendshipExistsServiceTest extends BaseTest {

    private CheckFriendshipExistsService service;
    private MySqlFriendshipDAO dao;
    private String userIdA = "APPLES";
    private String userIdB = "BUBBLES";
    private CheckFriendshipExistsResponse successResponse = new CheckFriendshipExistsResponse(true, true);
    private CheckFriendshipExistsResponse failureResponse = new CheckFriendshipExistsResponse(true, false);

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlFriendshipDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

        service = Mockito.spy(CheckFriendshipExistsService.class);
        Mockito.when(service.getFriendshipDAO()).thenReturn(dao);

        try {
            dao.addFriendship(new Friendship(userIdA, userIdB));
        }
        catch (DatabaseException ignored) {}
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
    public void checkFriendshipExists_success() {
        CheckFriendshipExistsRequest request = new CheckFriendshipExistsRequest(new Friendship(userIdA, userIdB));
        CheckFriendshipExistsResponse response = service.friendshipExists(request);
        assertTrue(response.isSuccess());
        assertTrue(response.isFriendshipExists());
    }

    @Test
    public void checkFriendshipExists_failure() {
        CheckFriendshipExistsRequest request = new CheckFriendshipExistsRequest(new Friendship("ERIN", userIdB));
        CheckFriendshipExistsResponse response = service.friendshipExists(request);
        assertTrue(response.isSuccess());
        assertFalse(response.isFriendshipExists());
    }
}
