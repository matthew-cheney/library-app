package Lambda;

import Request.RegisterRequest;
import Response.RegisterResponse;
import Service.RegisterService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RegisterHandler implements RequestHandler<RegisterRequest, RegisterResponse> {
    @Override
    public RegisterResponse handleRequest(RegisterRequest request, Context context) {
        RegisterService service = new RegisterService();
        return service.register(request);
    }
}