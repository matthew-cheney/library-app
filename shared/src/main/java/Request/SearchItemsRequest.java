package Request;

import Request.Abstract.PaginatedRequest;

public class SearchItemsRequest extends PaginatedRequest {

    private String ownerId;
    private String searchCriteria;

    public SearchItemsRequest() {}

    public SearchItemsRequest(String ownerId, String searchCriteria, int offset) {
        if (ownerId != null) setOwnerId(ownerId);
        setSearchCriteria(searchCriteria);
        setOffset(offset);
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
