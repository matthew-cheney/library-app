package Request;

import Request.Abstract.PaginatedRequest;

public class CatalogRequest extends PaginatedRequest {

    private String ownerId;
    private String categoryFilter;

    public CatalogRequest() {}

    public CatalogRequest(String ownerId, String categoryFilter, int offset) {
        setOwnerId(ownerId);
        setCategoryFilter(categoryFilter);
        setOffset(offset);
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getCategoryFilter() {
        return categoryFilter;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setCategoryFilter(String categoryFilter) {
        this.categoryFilter = categoryFilter;
    }
}