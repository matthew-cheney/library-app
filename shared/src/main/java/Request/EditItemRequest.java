package Request;

import Entities.Item;

public class EditItemRequest {

    private Item item;

    public EditItemRequest() {}

    public EditItemRequest(Item item) {
        setItem(item);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
