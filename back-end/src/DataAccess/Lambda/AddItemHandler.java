package DataAccess.Lambda;

import DataAccess.Request.AddItemRequest;
import DataAccess.Response.AddItemResponse;
import DataAccess.Service.AddItemService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddItemHandler implements RequestHandler<AddItemRequest, AddItemResponse> {
    @Override
    public AddItemResponse handleRequest(AddItemRequest request, Context context) {
        AddItemService service = new AddItemService();
        return service.addItem(request);
    }
}