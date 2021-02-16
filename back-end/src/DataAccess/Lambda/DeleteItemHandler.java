package DataAccess.Lambda;

import DataAccess.Request.DeleteItemRequest;
import DataAccess.Response.DeleteItemResponse;
import DataAccess.Service.DeleteItemService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteItemHandler implements RequestHandler<DeleteItemRequest, DeleteItemResponse> {
    @Override
    public DeleteItemResponse handleRequest(DeleteItemRequest request, Context context) {
        DeleteItemService service = new DeleteItemService();
        return service.deleteItem(request);
    }
}