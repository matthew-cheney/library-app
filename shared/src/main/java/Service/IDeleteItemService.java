package Service;

import Request.DeleteItemRequest;
import Response.DeleteItemResponse;

public interface IDeleteItemService {
    DeleteItemResponse deleteItem(DeleteItemRequest request);
}
