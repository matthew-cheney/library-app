package DataAccess.DAO;

import Config.Constants;
import DataAccess.DAO.MySql.MySqlItemDAO;
import Entities.Item;
import TestUtils.BaseTest;
import TestUtils.DatabaseFiller;
import TestUtils.TestConfig;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlItemDAOTest extends BaseTest {

    MySqlItemDAO dao;
    Item boardGame = new Item(
            "TEST_BOARD_GAME",
            "Pandemic",
            "BOARD_GAME",
            Constants.ITEM_DATE_FORMAT.format(new Date()),
            true,
            "TEST",
            "www.google.com",
            "This game is amazing! So challenging, yet fun.",
            4,
            120,
            null,
            null,
            null,
            null
    );
    Item movie = new Item(
            "TEST_MOVIE",
            "The Lord of the Rings 1",
            "MOVIE",
            Constants.ITEM_DATE_FORMAT.format(new Date()),
            true,
            "TEST_OTHER",
            "www.google.com",
            "This movie is one of the best ever made.",
            null,
            null,
            2000,
            "Action and Adventure",
            "DVD",
            null
    );
    Item book = new Item(
            "TEST_BOOK",
            "Harry Potter and the Sorcerer's Stone",
            "BOOK",
            Constants.ITEM_DATE_FORMAT.format(new Date()),
            true,
            "TEST",
            "www.google.com",
            "My first chapter book! Harry Potter is the best.",
            null,
            null,
            1997,
            "Fantasy",
            "Paperback",
            "JK Rowling"
    );

    @BeforeEach
    public void setUpTests() {
        try {
            dao = Mockito.spy(MySqlItemDAO.class);
            Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);
            dao.addItem(boardGame);
        }
        catch (DatabaseException ignored) {}
    }

    @AfterEach
    public void tearDownTests() {
        try {
            dao.deleteItem(boardGame.getId());
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getItemById_Success() {
        try {
            Item item = dao.getItemById(boardGame.getId());
            assertEquals(boardGame, item);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getItemById_Failure() {
        try {
            Item item = dao.getItemById("BUBBLES");
            assertNull(item);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getItemsByOwner_Success() {
        try {
            List<Item> items = dao.getItemsByOwner(boardGame.getOwnerId(), null, TestConfig.TEST_OFFSET);
            assertNotEquals(0, items.size());
            assertEquals(boardGame, items.get(0));
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getItemsByOwner_Failure() {
        try {
            List<Item> items = dao.getItemsByOwner("BUBBLES", null, TestConfig.TEST_OFFSET);
            assertEquals(0, items.size());
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void getItemsMatchingCriteria_Success() {
        try {
            DatabaseFiller.addUnitTestItems(boardGame.getOwnerId(), 0, 25, dao);
            List<Item> items = dao.getItemsMatchingCriteria(null, "com", TestConfig.TEST_OFFSET);
            assertEquals(10, items.size());
        }
        catch (DatabaseException ignored) {}
        finally {
            dao.clearItemsTable();
        }
    }

    @Test
    public void getItemsMatchingCriteria_Failure() {
        try {
            DatabaseFiller.addUnitTestItems(boardGame.getOwnerId(), 0, 25, dao);
            List<Item> items = dao.getItemsMatchingCriteria(null, "ERIN", TestConfig.TEST_OFFSET);
            assertEquals(0, items.size());
        }
        catch (DatabaseException ignored) {}
        finally {
            dao.clearItemsTable();
        }
    }

    @Test
    public void getItemsMatchingCriteriaWithOwnerId_Success() {
        try {
            DatabaseFiller.addUnitTestItems(boardGame.getOwnerId(), 0, 25, dao);
            List<Item> items = dao.getItemsMatchingCriteria(boardGame.getOwnerId(), "com", TestConfig.TEST_OFFSET);
            assertEquals(10, items.size());
        }
        catch (DatabaseException ignored) {}
        finally {
            dao.clearItemsTable();
        }
    }

    @Test
    public void getItemsMatchingCriteriaWithOwnerId_Failure() {
        try {
            DatabaseFiller.addUnitTestItems(boardGame.getOwnerId(), 0, 25, dao);
            List<Item> items = dao.getItemsMatchingCriteria("BUBBLES", "com", TestConfig.TEST_OFFSET);
            assertEquals(0, items.size());
        }
        catch (DatabaseException ignored) {}
        finally {
            dao.clearItemsTable();
        }
    }

    @Test
    public void addItem_Success() {
        try {
            boolean success = dao.addItem(movie);
            assertTrue(success);
            Item item = dao.getItemById(movie.getId());
            assertEquals(movie, item);
            success = dao.deleteItem(movie.getId());
            assertTrue(success);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void addItem_Failure() {
        try {
            boolean success = dao.addItem(boardGame);
            assertFalse(success);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void updateItem_Success() {
        try {
            Item item = boardGame;
            item.setTimeToPlayInMins(10);
            boolean success = dao.updateItem(boardGame.getId(), item);
            assertTrue(success);
            Item updatedItem = dao.getItemById(boardGame.getId());
            assertEquals(item, updatedItem);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void updateItem_Failure() {
        try {
            Item item = book;
            item.setTimeToPlayInMins(10);
            boolean success = dao.updateItem(book.getId(), item);
            assertFalse(success);
            Item notChangedItem = dao.getItemById(boardGame.getId());
            assertNotEquals(item, notChangedItem);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void deleteItem_Success() {
        try {
            boolean success = dao.addItem(book);
            assertTrue(success);
            success = dao.deleteItem(book.getId());
            assertTrue(success);
            Item item = dao.getItemById(book.getId());
            assertNull(item);
        }
        catch (DatabaseException ignored) {}
    }

    @Test
    public void deleteItem_Failure() {
        try {
            boolean success = dao.deleteItem(book.getId());
            assertFalse(success);
            Item item = dao.getItemById(book.getId());
            assertNull(item);
        }
        catch (DatabaseException ignored) {}
    }
}
