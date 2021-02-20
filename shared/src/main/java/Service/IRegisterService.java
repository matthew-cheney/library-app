package Service;

import Request.RegisterRequest;
import Response.RegisterResponse;

public interface IRegisterService {
    RegisterResponse register(RegisterRequest request);
}
