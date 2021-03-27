package Service;

import Request.SearchItemsRequest;
import Response.SearchItemsResponse;

public interface ISearchItemsService {
    SearchItemsResponse searchItems(SearchItemsRequest request);
}
