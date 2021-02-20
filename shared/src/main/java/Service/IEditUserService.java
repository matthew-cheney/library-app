package Service;

import Request.EditUserRequest;
import Response.EditUserResponse;

public interface IEditUserService {
    EditUserResponse editUser(EditUserRequest request);
}
