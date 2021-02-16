package Lambda;

import Request.EditUserRequest;
import Response.EditUserResponse;
import Service.EditUserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class EditUserHandler implements RequestHandler<EditUserRequest, EditUserResponse> {
    @Override
    public EditUserResponse handleRequest(EditUserRequest request, Context context) {
        EditUserService service = new EditUserService();
        return service.editUser(request);
    }
}