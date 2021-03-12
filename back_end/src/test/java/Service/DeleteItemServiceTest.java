package Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Date;

import Config.Constants;
import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.MySql.MySqlItemDAO;
import Entities.Item;
import Request.DeleteItemRequest;
import Response.DeleteItemResponse;
import TestUtils.BaseTest;
import TestUtils.TestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteItemServiceTest extends BaseTest {

    private DeleteItemService service;
    private MySqlItemDAO dao;
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
    private DeleteItemRequest successfulRequest = new DeleteItemRequest(boardGame.getId());
    private DeleteItemRequest failureRequest = new DeleteItemRequest("BUBBLES");
    private DeleteItemResponse successResponse = new DeleteItemResponse(true);
    private DeleteItemResponse failureResponse = new DeleteItemResponse(false, "Error deleting item!");

    @BeforeEach
    public void setUpTests() {
        try {
            dao = Mockito.spy(MySqlItemDAO.class);
            Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

            service = Mockito.spy(DeleteItemService.class);
            Mockito.when(service.getItemDAO()).thenReturn(dao);

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
    public void deleteItem_success() {
        boolean responseReceived = false;
        try {
            DeleteItemResponse response = service.deleteItem(successfulRequest);
            assertTrue(response.isSuccess());
            responseReceived = true;
            Item item = dao.getItemById(successfulRequest.getId());
            assertNull(item);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            assertTrue(responseReceived);
        }
    }

    @Test
    public void deleteItem_failure() {
        DeleteItemResponse response = service.deleteItem(failureRequest);
        assertEquals(failureResponse, response);
    }
}
