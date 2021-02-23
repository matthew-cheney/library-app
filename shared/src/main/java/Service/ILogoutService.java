package Service;

import Request.LogoutRequest;
import Response.LogoutResponse;

public interface ILogoutService {
    LogoutResponse logout(LogoutRequest request);
}
