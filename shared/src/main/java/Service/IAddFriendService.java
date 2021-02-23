package Service;

import Request.AddFriendRequest;
import Response.AddFriendResponse;

public interface IAddFriendService {
    AddFriendResponse addFriend(AddFriendRequest request);
}
