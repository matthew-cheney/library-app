package Service;

import Request.AddItemRequest;
import Response.AddItemResponse;

public interface IAddItemService {
    AddItemResponse addItem(AddItemRequest request);
}
