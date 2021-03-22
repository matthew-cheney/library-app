package DataAccess.DAO;

import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.RegisterRequest;
import Service.RegisterService;
import TestUtils.BaseTest;
import TestUtils.DatabaseFiller;
import TestUtils.TestConfig;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlUserDAOTest extends BaseTest {
    MySqlUserDAO dao;
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
    User otherTestUser = new User(
            "TEST_OTHER",
            "shoppingIsFun",
            "RossIsTheOneImStuckWith",
            "Emma",
            "Rachel",
            "Green",
            "rgreen@rl.com",
            "0928309291",
            "www.google.com"
    );

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlUserDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);
        try {
            dao.addUser(testUser);
        }
        catch (DatabaseException ignored) {}
    }

    @AfterEach
    public void tearDownTests() {
        try {
            dao.deleteUser(testUser.getId());
            User user = dao.getUserByCredentials("actingIsFun", "joey");
            dao.deleteUser(user.getId());
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getUserById_Success() {
        try {
            User user = dao.getUserById(testUser.getId());
            assertEquals(testUser, user);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getUserById_Failure() {
        try {
            User user = dao.getUserById("BUBBLES");
            assertNull(user);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getUsersByIds_Success() {
        try {
            dao.addUser(otherTestUser);
            List<String> ids = new ArrayList<>();
            ids.add("TEST");
            ids.add("TEST_OTHER");

            List<User> users = dao.getUsersByIds(ids, 0);
            assertEquals(2, users.size());
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getUsersByIds_Failure() {
        try {
            List<String> ids = new ArrayList<>();
            ids.add("ERIN");
            ids.add("WOOD");

            List<User> users = dao.getUsersByIds(ids, 0);
            assertEquals(0, users.size());
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getUserByCredentials_Success() {
        try {
            RegisterService registerService = Mockito.spy(RegisterService.class);
            Mockito.when(registerService.getUserDAO()).thenReturn(dao);

            RegisterRequest request = new RegisterRequest(
                    "actingIsFun",
                    "joey",
                    "Joey",
                    "Tribianni",
                    "jt@acting.com",
                    "8603583092",
                    "www.google.com"
            );
            registerService.register(request);

            User user = dao.getUserByCredentials("actingIsFun", "joey");
            assertEquals("Joey", user.getFirstName());
            assertEquals("Tribianni", user.getLastName());
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getUserByCredentials_Failure() {
        try {
            User user = dao.getUserByCredentials("BUBBLES", "BUBBLES");
            assertNull(user);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getUsersMatchingCriteria_Success() {
        try {
            DatabaseFiller.addUnitTestUsers(0, 25, dao);
            List<User> users = dao.getUsersMatchingCriteria("com", TestConfig.TEST_OFFSET);
            assertEquals(5, users.size());
        }
        catch (DatabaseException ignored) {}
        finally {
            dao.clearUsersTable();
        }
    }

    @Test
    public void getUsersMatchingCriteria_Failure() {
        try {
            DatabaseFiller.addUnitTestUsers(0, 25, dao);
            List<User> users = dao.getUsersMatchingCriteria("ERIN", TestConfig.TEST_OFFSET);
            assertEquals(0, users.size());
        }
        catch (DatabaseException ignored) {}
        finally {
            dao.clearUsersTable();
        }
    }

    @Test
    public void addUser_Success() {
        try {
            boolean success = dao.addUser(otherTestUser);
            assertTrue(success);
            User user = dao.getUserById(otherTestUser.getId());
            assertEquals(otherTestUser, user);
            success = dao.deleteUser(otherTestUser.getId());
            assertTrue(success);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void addUser_Failure() {
        boolean expected = false;
        try {
            dao.addUser(testUser);
        }
        catch (DatabaseException ex) {
            expected = true;
        }
        assertTrue(expected);
    }

    @Test
    public void updateUser_Success() {
        try {
            User user = testUser;
            user.setEmail("marriedagain@match.com");
            boolean success = dao.updateUser(testUser.getId(), user);
            assertTrue(success);
            User updatedUser = dao.getUserById(testUser.getId());
            assertEquals(user, updatedUser);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void updateUser_Failure() {
        try {
            User user = otherTestUser;
            user.setEmail("yetanotherfight@rl.com");
            boolean success = dao.updateUser(otherTestUser.getId(), user);
            assertFalse(success);
            User notChangedUser = dao.getUserById(testUser.getId());
            assertNotEquals(user, notChangedUser);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void deleteUser_Success() {
        try {
            boolean success = dao.addUser(otherTestUser);
            assertTrue(success);
            success = dao.deleteUser(otherTestUser.getId());
            assertTrue(success);
            User user = dao.getUserById(otherTestUser.getId());
            assertNull(user);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void deleteUser_Failure() {
        try {
            boolean success = dao.deleteUser(otherTestUser.getId());
            assertFalse(success);
            User user = dao.getUserById(otherTestUser.getId());
            assertNull(user);
        }
        catch (DatabaseException ignored) {}
    }
}
