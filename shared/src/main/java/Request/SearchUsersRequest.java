package Request;

import Request.Abstract.PaginatedRequest;

public class SearchUsersRequest extends PaginatedRequest {

    private String searchCriteria;

    public SearchUsersRequest() {}

    public SearchUsersRequest(String searchCriteria, int offset) {
        setSearchCriteria(searchCriteria);
        setOffset(offset);
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
