package Service;

import Request.LoginRequest;
import Response.LoginResponse;

public interface ILoginService {
    LoginResponse login(LoginRequest request);
}
