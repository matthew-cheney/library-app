package Lambda;

import org.junit.jupiter.api.Test;

import Request.SearchItemsRequest;
import Response.SearchItemsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchItemsLambdaTest {
    @Test
    public void searchItems_success() {
        SearchItemsHandler lambda = new SearchItemsHandler();
        SearchItemsRequest request = new SearchItemsRequest("11", "Harry", 0);
        SearchItemsResponse response = lambda.handleRequest(request, null);
        assertTrue(response.isSuccess());
        assertEquals(2, response.getItems().size());
    }
}
