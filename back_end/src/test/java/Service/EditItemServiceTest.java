package Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Date;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.MySql.MySqlItemDAO;
import Entities.Item;
import Request.EditItemRequest;
import Response.EditItemResponse;
import TestUtils.TestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditItemServiceTest {

    private EditItemService service;
    private MySqlItemDAO dao;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    String currDate = dateFormat.format(new Date());
    Item boardGame = new Item(
            "TEST_BOARD_GAME",
            "Pandemic",
            "BOARD_GAME",
            currDate,
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
    Item editedBoardGame = new Item (
            "TEST_BOARD_GAME",
            "Pandemic",
            "BOARD_GAME",
            currDate,
            true,
            "TEST",
            "www.microsoft.com",
            "This game is amazing! So challenging, yet fun.",
            2,
            10,
            null,
            null,
            null,
            null
    );
    Item errorItem = new Item (
            "ERROR",
            "Pandemic",
            "BOARD_GAME",
            currDate,
            true,
            "TEST",
            "www.microsoft.com",
            "This game is amazing! So challenging, yet fun.",
            2,
            10,
            null,
            null,
            null,
            null
    );
    private EditItemRequest successfulRequest = new EditItemRequest(editedBoardGame);
    private EditItemRequest failureRequest = new EditItemRequest(errorItem);
    private EditItemResponse successResponse = new EditItemResponse(true);
    private EditItemResponse failureResponse = new EditItemResponse(false, "Error updating item!");

    @BeforeEach
    public void setUpTests() {
        try {
            dao = Mockito.spy(MySqlItemDAO.class);
            Mockito.when(dao.getConnectionPool()).thenReturn(TestConfig.connectionPool);

            service = Mockito.spy(EditItemService.class);
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
    public void editItem_success() {
        boolean responseReceived = false;
        try {
            EditItemResponse response = service.editItem(successfulRequest);
            assertTrue(response.isSuccess());
            responseReceived = true;
            Item item = dao.getItemById(successfulRequest.getItem().getId());
            assertEquals(editedBoardGame, item);
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            assertTrue(responseReceived);
        }
    }

    @Test
    public void editItem_failure() {
        EditItemResponse response = service.editItem(failureRequest);
        assertEquals(failureResponse, response);
    }
}
