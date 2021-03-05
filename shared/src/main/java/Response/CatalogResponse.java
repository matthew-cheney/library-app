package Response;

import java.util.List;

import Entities.Item;

public class CatalogResponse {


    // Chayston - you will likely change this code. I'm putting it here to help me set up a recycler view on the front end. -Matthew

    List<Item> items;
    boolean hasMorePages;

    public CatalogResponse(List<Item> items, boolean hasMorePages) {
        this.items = items;
        this.hasMorePages = hasMorePages;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean getHasMorePages() {
        return hasMorePages;
    }

    public void setHasMorePages(boolean hasMorePages) {
        this.hasMorePages = hasMorePages;
    }

    public List<Item> getCatalog() {
        return items;
    }
}
