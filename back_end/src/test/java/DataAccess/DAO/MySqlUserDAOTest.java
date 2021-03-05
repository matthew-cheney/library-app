package DataAccess.DAO;

import DataAccess.DAO.MySql.MySqlUserDAO;
import Entities.User;
import Request.RegisterRequest;
import Service.RegisterService;
import TestUtils.TestConfig;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlUserDAOTest {
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
        Mockito.when(dao.getConnectionPool()).thenReturn(TestConfig.CONNECTION_POOL);
        try {
            dao.addUser(testUser);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @AfterEach
    public void tearDownTests() {
        try {
            dao.deleteUser(testUser.getId());
            User user = dao.getUserByCredentials("actingIsFun", "joey");
            dao.deleteUser(user.getId());
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getUserById_Success() {
        try {
            User user = dao.getUserById(testUser.getId());
            assertEquals(testUser, user);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getUserById_Failure() {
        try {
            User user = dao.getUserById("BUBBLES");
            assertNull(user);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
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
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getUserByCredentials_Failure() {
        try {
            User user = dao.getUserByCredentials("BUBBLES", "BUBBLES");
            assertNull(user);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
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
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
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
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
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
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
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
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void deleteUser_Failure() {
        try {
            boolean success = dao.deleteUser(otherTestUser.getId());
            assertFalse(success);
            User user = dao.getUserById(otherTestUser.getId());
            assertNull(user);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
