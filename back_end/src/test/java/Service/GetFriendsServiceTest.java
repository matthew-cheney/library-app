package Service;

import com.mysql.cj.x.protobuf.MysqlxSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import Config.Constants;
import DataAccess.Connection.ConnectionPool;
import DataAccess.DAO.MySql.MySqlFriendshipDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.FriendsRequest;
import Response.FriendsResponse;
import TestUtils.BaseTest;
import TestUtils.DatabaseFiller;
import TestUtils.TestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetFriendsServiceTest extends BaseTest {

    private GetFriendsService service;
    private MySqlFriendshipDAO dao;
    private MySqlUserDAO userDao;
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

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlFriendshipDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

        userDao = Mockito.spy(MySqlUserDAO.class);
        Mockito.when(userDao.getConnectionPool()).thenReturn(CONNECTION_POOL);

        service = Mockito.spy(GetFriendsService.class);
        Mockito.when(service.getFriendshipDAO()).thenReturn(dao);
        Mockito.when(service.getUserDAO()).thenReturn(userDao);

        DatabaseFiller.addUnitTestUsers(0, 25, userDao);
        DatabaseFiller.addUnitTestFriendships(testUser.getId(), 25, dao);
    }

    @AfterEach
    public void tearDownTests() {
        dao.clearFriendshipsTable();
        userDao.clearUsersTable();
    }

    @Test
    public void getFriends_success() {
        FriendsRequest request = new FriendsRequest(testUser.getId(), TestConfig.TEST_OFFSET);
        FriendsResponse response = service.getFriends(request);
        assertEquals(10, response.getFriends().size());
    }

    @Test
    public void getFriends_failure() {
        FriendsRequest request = new FriendsRequest("BUBBLES", TestConfig.TEST_OFFSET);
        FriendsResponse response = service.getFriends(request);
        assertEquals(0, response.getFriends().size());
    }
}
