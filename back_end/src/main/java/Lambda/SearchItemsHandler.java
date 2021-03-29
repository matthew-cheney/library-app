package Lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import Request.SearchItemsRequest;
import Response.SearchItemsResponse;
import Service.SearchItemsService;

public class SearchItemsHandler implements RequestHandler<SearchItemsRequest, SearchItemsResponse> {
    @Override
    public SearchItemsResponse handleRequest(SearchItemsRequest request, Context context) {
        SearchItemsService service = new SearchItemsService();
        return service.searchItems(request);
    }
}
