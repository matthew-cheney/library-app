package Service;

import Request.CheckFriendshipExistsRequest;
import Response.CheckFriendshipExistsResponse;

public interface ICheckFriendshipExistsService {
    CheckFriendshipExistsResponse friendshipExists(CheckFriendshipExistsRequest request);
}
