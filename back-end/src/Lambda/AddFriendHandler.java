package Lambda;

import Request.AddFriendRequest;
import Response.AddFriendResponse;
import Service.AddFriendService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddFriendHandler implements RequestHandler<AddFriendRequest, AddFriendResponse> {
    @Override
    public AddFriendResponse handleRequest(AddFriendRequest request, Context context) {
        AddFriendService service = new AddFriendService();
        return service.addFriend(request);
    }
}