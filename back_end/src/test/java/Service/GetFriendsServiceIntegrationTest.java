package Service;

import org.junit.jupiter.api.Test;

import Request.FriendsRequest;
import Response.FriendsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetFriendsServiceIntegrationTest {
    @Test
    public void getFriendsIntegrationTest_success() {
        GetFriendsService realService = new GetFriendsService();
        FriendsRequest requestOne = new FriendsRequest("11", 0);
        FriendsResponse responseOne = realService.getFriends(requestOne);
        FriendsRequest requestTwo = new FriendsRequest("11", 10);
        FriendsResponse responseTwo = realService.getFriends(requestTwo);
        assertEquals(10, responseOne.getFriends().size());
        assertEquals(10, responseTwo.getFriends().size());
    }
}
