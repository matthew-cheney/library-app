package Service;

import Request.EditItemRequest;
import Response.EditItemResponse;

public interface IEditItemService {
    EditItemResponse editItem(EditItemRequest request);
}
