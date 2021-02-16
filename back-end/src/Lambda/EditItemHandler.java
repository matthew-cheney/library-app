package Lambda;

import Request.EditItemRequest;
import Response.EditItemResponse;
import Service.EditItemService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class EditItemHandler implements RequestHandler<EditItemRequest, EditItemResponse> {
    @Override
    public EditItemResponse handleRequest(EditItemRequest request, Context context) {
        EditItemService service = new EditItemService();
        return service.editItem(request);
    }
}