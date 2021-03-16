package Service;

import Request.RemoveFriendRequest;
import Response.RemoveFriendResponse;

public interface IRemoveFriendService {
    RemoveFriendResponse removeFriend(RemoveFriendRequest request);
}
