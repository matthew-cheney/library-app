package Lambda;

import Request.LoginRequest;
import Response.LoginResponse;
import Service.LoginService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LoginHandler implements RequestHandler<LoginRequest, LoginResponse> {
    @Override
    public LoginResponse handleRequest(LoginRequest request, Context context) {
        LoginService service = new LoginService();
        return service.login(request);
    }
}