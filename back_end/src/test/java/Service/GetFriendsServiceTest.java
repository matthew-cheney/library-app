package Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import DataAccess.DAO.MySql.MySqlFriendshipDAO;
import Request.FriendsRequest;
import Response.FriendsResponse;
import TestUtils.BaseTest;
import TestUtils.DatabaseFiller;
import TestUtils.TestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetFriendsServiceTest extends BaseTest {

    private GetFriendsService service;
    private MySqlFriendshipDAO dao;
    private String userIdA = "APPLES";
    private String userIdB = "BUBBLES";

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlFriendshipDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

        service = Mockito.spy(GetFriendsService.class);
        Mockito.when(service.getFriendshipDAO()).thenReturn(dao);

        DatabaseFiller.addUnitTestFriendships(userIdA, 25, dao);
    }

    @AfterEach
    public void tearDownTests() {
        dao.clearFriendshipsTable();
    }

    @Test
    public void getFriends_success() {
        FriendsRequest request = new FriendsRequest(userIdA, TestConfig.TEST_OFFSET);
        FriendsResponse response = service.getFriends(request);
        assertEquals(10, response.getFriendships().size());
    }

    @Test
    public void getFriends_failure() {
        FriendsRequest request = new FriendsRequest(userIdB, TestConfig.TEST_OFFSET);
        FriendsResponse response = service.getFriends(request);
        assertEquals(0, response.getFriendships().size());
    }
}
