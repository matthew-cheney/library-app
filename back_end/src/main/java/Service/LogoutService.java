package Service;

import Request.LogoutRequest;
import Response.LogoutResponse;

public class LogoutService implements ILogoutService {
    @Override
    public LogoutResponse logout(LogoutRequest request) {
        return new LogoutResponse(true);
    }
}
