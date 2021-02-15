package DataAccess.Lambda;

import DataAccess.Request.AddFriendRequest;
import DataAccess.Response.AddFriendResponse;
import DataAccess.Service.AddFriendService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddFriendHandler implements RequestHandler<AddFriendRequest, AddFriendResponse> {
    @Override
    public AddFriendResponse handleRequest(AddFriendRequest request, Context context) {
        AddFriendService service = new AddFriendService();
        return service.addFriend(request);
    }
}