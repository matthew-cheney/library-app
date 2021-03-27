package Lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import Request.SearchUsersRequest;
import Response.SearchUsersResponse;
import Service.SearchUsersService;

public class SearchUsersHandler implements RequestHandler<SearchUsersRequest, SearchUsersResponse> {
    @Override
    public SearchUsersResponse handleRequest(SearchUsersRequest request, Context context) {
        SearchUsersService service = new SearchUsersService();
        return service.searchUsers(request);
    }
}
