package DataAccess.Lambda;

import DataAccess.Request.EditItemRequest;
import DataAccess.Response.EditItemResponse;
import DataAccess.Service.EditItemService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class EditItemHandler implements RequestHandler<EditItemRequest, EditItemResponse> {
    @Override
    public EditItemResponse handleRequest(EditItemRequest request, Context context) {
        EditItemService service = new EditItemService();
        return service.editItem(request);
    }
}