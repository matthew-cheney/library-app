package DataAccess.DAO;

import DataAccess.DAO.MySql.MySqlUserDAO;
import DataAccess.Entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlUserDAOTest {

    IUserDAO dao;
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
        dao = new MySqlUserDAO();
        dao.addUser(testUser);
    }

    @AfterEach
    public void tearDownTests() {
        dao.deleteUser(testUser.getId());
    }

    @Test
    public void getUser_Success() {
        User user = dao.getUser(testUser.getId());
        assertEquals(testUser, user);
    }

    @Test
    public void getUser_Failure() {
        User user = dao.getUser("BUBBLES");
        assertNull(user);
    }

    @Test
    public void addUser_Success() {
        boolean success = dao.addUser(otherTestUser);
        assertTrue(success);
        User user = dao.getUser(otherTestUser.getId());
        assertEquals(otherTestUser, user);
        success = dao.deleteUser(otherTestUser.getId());
        assertTrue(success);
    }

    @Test
    public void addUser_Failure() {
        boolean success = dao.addUser(testUser);
        assertFalse(success);
    }

    @Test
    public void updateUser_Success() {
        User user = testUser;
        user.setEmail("marriedagain@match.com");
        boolean success = dao.updateUser(testUser.getId(), user);
        assertTrue(success);
        User updatedUser = dao.getUser(testUser.getId());
        assertEquals(user, updatedUser);
    }

    @Test
    public void updateUser_Failure() {
        User user = otherTestUser;
        user.setEmail("yetanotherfight@rl.com");
        boolean success = dao.updateUser(otherTestUser.getId(), user);
        assertFalse(success);
        User notChangedUser = dao.getUser(testUser.getId());
        assertNotEquals(user, notChangedUser);
    }

    @Test
    public void deleteUser_Success() {
        boolean success = dao.addUser(otherTestUser);
        assertTrue(success);
        success = dao.deleteUser(otherTestUser.getId());
        assertTrue(success);
        User user = dao.getUser(otherTestUser.getId());
        assertNull(user);
    }

    @Test
    public void deleteUser_Failure() {
        boolean success = dao.deleteUser(otherTestUser.getId());
        assertFalse(success);
        User user = dao.getUser(otherTestUser.getId());
        assertNull(user);
    }
}
