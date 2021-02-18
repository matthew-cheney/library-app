package Lambda;

import Request.FriendsRequest;
import Response.FriendsResponse;
import Service.GetFriendsService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetFriendsHandler implements RequestHandler<FriendsRequest, FriendsResponse> {
    @Override
    public FriendsResponse handleRequest(FriendsRequest request, Context context) {
        GetFriendsService service = new GetFriendsService();
        return service.getFriends(request);
    }
}