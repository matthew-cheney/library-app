package Service;

import Request.FriendsRequest;
import Response.FriendsResponse;

public interface IGetFriendsService {
    FriendsResponse getFriends(FriendsRequest request);
}
