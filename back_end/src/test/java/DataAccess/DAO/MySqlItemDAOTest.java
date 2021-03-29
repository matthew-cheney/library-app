package DataAccess.DAO;

import DataAccess.DAO.MySql.MySqlItemDAO;
import Entities.Item;
import TestUtils.TestConfig;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlItemDAOTest {

    MySqlItemDAO dao;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    Item boardGame = new Item(
            "TEST_BOARD_GAME",
            "Pandemic",
            "BOARD_GAME",
            dateFormat.format(new Date()),
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
            dateFormat.format(new Date()),
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
            dateFormat.format(new Date()),
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
            Mockito.when(dao.getConnectionPool()).thenReturn(TestConfig.CONNECTION_POOL);
            dao.addItem(boardGame);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @AfterEach
    public void tearDownTests() {
        try {
            dao.deleteItem(boardGame.getId());
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getItemById_Success() {
        try {
            Item item = dao.getItemById(boardGame.getId());
            assertEquals(boardGame, item);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getItemById_Failure() {
        try {
            Item item = dao.getItemById("BUBBLES");
            assertNull(item);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getItemsByOwner_Success() {
        try {
            List<Item> items = dao.getItemsByOwner(boardGame.getOwnerId(), TestConfig.TEST_OFFSET);
            assertNotEquals(0, items.size());
            assertEquals(boardGame, items.get(0));
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void getItemsByOwner_Failure() {
        try {
            List<Item> items = dao.getItemsByOwner("BUBBLES", TestConfig.TEST_OFFSET);
            assertEquals(0, items.size());
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
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
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void addItem_Failure() {
        try {
            boolean success = dao.addItem(boardGame);
            assertFalse(success);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
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
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
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
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
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
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void deleteItem_Failure() {
        try {
            boolean success = dao.deleteItem(book.getId());
            assertFalse(success);
            Item item = dao.getItemById(book.getId());
            assertNull(item);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
