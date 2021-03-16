package Response;

import java.util.List;

import Entities.Item;
import Response.Abstract.BaseResponse;

public class SearchItemsResponse extends BaseResponse {

    private List<Item> items;

    public SearchItemsResponse() {}

    public SearchItemsResponse(boolean success, List<Item> items) {
        super(success);
        setItems(items);
    }

    public SearchItemsResponse(boolean success, String message) {
        super(success, message);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
