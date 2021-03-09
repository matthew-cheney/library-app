package Request;

import Request.Abstract.PaginatedRequest;

public class CatalogRequest extends PaginatedRequest {

    private String ownerId;

    public CatalogRequest() {}

    public CatalogRequest(String ownerId, int offset) {
        setOwnerId(ownerId);
        setOffset(offset);
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}