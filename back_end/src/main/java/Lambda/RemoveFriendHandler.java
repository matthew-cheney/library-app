package Lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import Request.RemoveFriendRequest;
import Response.RemoveFriendResponse;
import Service.RemoveFriendService;

public class RemoveFriendHandler implements RequestHandler<RemoveFriendRequest, RemoveFriendResponse> {
    @Override
    public RemoveFriendResponse handleRequest(RemoveFriendRequest request, Context context) {
        RemoveFriendService service = new RemoveFriendService();
        return service.removeFriend(request);
    }
}