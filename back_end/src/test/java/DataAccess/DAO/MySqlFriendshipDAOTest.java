package DataAccess.DAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import DataAccess.DAO.MySql.MySqlFriendshipDAO;
import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.Friendship;
import TestUtils.BaseTest;
import TestUtils.TestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MySqlFriendshipDAOTest extends BaseTest {

    MySqlFriendshipDAO dao;
    Friendship friendship = new Friendship("TEST", "APPLES");

    @BeforeEach
    public void setUpTests() {
        try {
            dao = Mockito.spy(MySqlFriendshipDAO.class);
            Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

            dao.addFriendship(friendship);
        }
        catch (DatabaseException ignored) {}
    }

    @AfterEach
    public void tearDownTests() {
        try {
            dao.deleteFriendship(friendship);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void friendshipExists_Success() {
        try {
            assertTrue(dao.friendshipExists(friendship));
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void friendshipExists_Failure() {
        try {
            dao.friendshipExists(new Friendship("BUBBLES", "ERIN"));
        }
        catch (DatabaseException ex) {
            assertEquals("Friendship already exists!", ex.getMessage());
        }
    }

    @Test
    public void getFriendsOfUser_Success() {
        try {
            List<String> friendIds = dao.getFriendIdsOfUser("TEST", TestConfig.TEST_OFFSET);
            assertEquals("APPLES", friendIds.get(0));
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getFriendsOfUser_Failure() {
        try {
            List<String> friendIds = dao.getFriendIdsOfUser("BUBBLES", TestConfig.TEST_OFFSET);
            assertEquals(0, friendIds.size());
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void addFriendship_Success() {
        try {
            Friendship newFriendship = new Friendship("ERIN", "WOOD");
            assertTrue(dao.addFriendship(newFriendship));
            dao.deleteFriendship(newFriendship);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void addFriendship_Failure() {
        boolean expectedResult = false;
        try {
            dao.addFriendship(friendship);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
            expectedResult = true;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void deleteFriendship_Success() {
        try {
            assertTrue(dao.deleteFriendship(friendship));
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void deleteFriendship_Failure() {
        boolean expectedResult = false;
        try {
            dao.deleteFriendship(new Friendship("ERIN", "WOOD"));
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
            expectedResult = true;
        }
        assertTrue(expectedResult);
    }
}
