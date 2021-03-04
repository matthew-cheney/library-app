package DataAccess.DAO;

import DataAccess.DAO.MySql.MySqlItemDAO;
import Entities.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlItemDAOTest {

    IItemDAO dao;
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
        dao = new MySqlItemDAO();
        dao.addItem(boardGame);
    }

    @AfterEach
    public void tearDownTests() {
        dao.deleteItem(boardGame.getId());
    }

    @Test
    public void getItem_Success() {
        Item item = dao.getItem(boardGame.getId());
        assertEquals(boardGame, item);
    }

    @Test
    public void getItem_Failure() {
        Item item = dao.getItem("BUBBLES");
        assertNull(item);
    }

    @Test
    public void addItem_Success() {
        boolean success = dao.addItem(movie);
        assertTrue(success);
        Item item = dao.getItem(movie.getId());
        assertEquals(movie, item);
        success = dao.deleteItem(movie.getId());
        assertTrue(success);
    }

    @Test
    public void addItem_Failure() {
        boolean success = dao.addItem(boardGame);
        assertFalse(success);
    }

    @Test
    public void updateItem_Success() {
        Item item = boardGame;
        item.setTimeToPlayInMins(10);
        boolean success = dao.updateItem(boardGame.getId(), item);
        assertTrue(success);
        Item updatedItem = dao.getItem(boardGame.getId());
        assertEquals(item, updatedItem);
    }

    @Test
    public void updateItem_Failure() {
        Item item = book;
        item.setTimeToPlayInMins(10);
        boolean success = dao.updateItem(book.getId(), item);
        assertFalse(success);
        Item notChangedItem = dao.getItem(boardGame.getId());
        assertNotEquals(item, notChangedItem);
    }

    @Test
    public void deleteItem_Success() {
        boolean success = dao.addItem(book);
        assertTrue(success);
        success = dao.deleteItem(book.getId());
        assertTrue(success);
        Item item = dao.getItem(book.getId());
        assertNull(item);
    }

    @Test
    public void deleteItem_Failure() {
        boolean success = dao.deleteItem(book.getId());
        assertFalse(success);
        Item item = dao.getItem(book.getId());
        assertNull(item);
    }
}
