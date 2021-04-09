package Service;

import org.junit.jupiter.api.Test;

import Request.SearchItemsRequest;
import Response.SearchItemsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchItemsServiceIntegrationTest {
    @Test
    public void searchItems_success() {
        SearchItemsService service = new SearchItemsService();
        SearchItemsRequest request = new SearchItemsRequest("11", "Harry", 0);
        SearchItemsResponse response = service.searchItems(request);
        assertTrue(response.isSuccess());
        assertEquals(2, response.getItems().size());
    }

    @Test
    public void searchItems_anotherTest_success() {
        SearchItemsService service = new SearchItemsService();
        SearchItemsRequest request = new SearchItemsRequest("11", "0", 0);
        SearchItemsResponse response = service.searchItems(request);
        assertTrue(response.isSuccess());
        assertEquals(10, response.getItems().size());
    }

    @Test
    public void searchItemsNoOwner_success() {
        SearchItemsService service = new SearchItemsService();
        SearchItemsRequest request = new SearchItemsRequest(null, "Harry", 0);
        SearchItemsResponse response = service.searchItems(request);
        assertTrue(response.isSuccess());
        assertEquals(5, response.getItems().size());
    }
}
