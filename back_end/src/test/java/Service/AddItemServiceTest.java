package Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import DataAccess.DAO.DatabaseException;
import DataAccess.DAO.MySql.MySqlItemDAO;
import Entities.Item;
import Request.AddItemRequest;
import Response.AddItemResponse;
import TestUtils.TestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddItemServiceTest {

    private AddItemService service;
    private MySqlItemDAO dao;
    private AddItemRequest successfulRequest = new AddItemRequest(
            "Pandemic",
            "BOARD_GAME",
            true,
            "TEST",
            "www.google.com",
            "This game is amazing! So challenging, yet fun.",
            4,
            120
    );
    private AddItemRequest failureRequest = new AddItemRequest(
            "Pandemic",
            "BOARD_GAME",
            true,
            null,
            "www.google.com",
            "This game is amazing! So challenging, yet fun.",
            4,
            120
    );
    private AddItemResponse successResponse = new AddItemResponse(true);
    private AddItemResponse failureResponse = new AddItemResponse(false, "Column 'OwnerId' cannot be null");

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlItemDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(TestConfig.CONNECTION_POOL);

        service = Mockito.spy(AddItemService.class);
        Mockito.when(service.getItemDAO()).thenReturn(dao);
    }

    @AfterEach
    public void tearDownTests() {
        try {
            List<Item> items = dao.getItemsByOwner("TEST", TestConfig.TEST_OFFSET);
            if (items.size() != 0) dao.deleteItem(items.get(0).getId());
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void addItem_success() {
        boolean responseReceived = false;
        try {
            AddItemResponse response = service.addItem(successfulRequest);
            assertTrue(response.isSuccess());
            responseReceived = true;
            List<Item> items = dao.getItemsByOwner(successfulRequest.getOwnerId(), TestConfig.TEST_OFFSET);
            assertEquals(1, items.size());
            assertEquals("This game is amazing! So challenging, yet fun.", items.get(0).getDescription());
        }
        catch (DatabaseException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            assertTrue(responseReceived);
        }
    }

    @Test
    public void addItem_failure() {
        AddItemResponse response = service.addItem(failureRequest);
        assertEquals(failureResponse, response);
    }
}
