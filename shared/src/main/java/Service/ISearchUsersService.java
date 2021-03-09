package Service;

import Request.SearchUsersRequest;
import Response.SearchUsersResponse;

public interface ISearchUsersService {
    SearchUsersResponse searchUsers(SearchUsersRequest request);
}
