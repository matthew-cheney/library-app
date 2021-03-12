package Response;

import java.util.List;

import Entities.Item;
import Response.Abstract.BaseResponse;

public class CatalogResponse extends BaseResponse {

    private List<Item> items;

    public CatalogResponse() {}

    public CatalogResponse(boolean success, List<Item> items) {
        super(success);
        setItems(items);
    }

    public CatalogResponse(boolean success, String message) {
        super(success, message);

    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
