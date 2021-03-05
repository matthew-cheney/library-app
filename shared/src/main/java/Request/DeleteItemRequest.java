package Request;

public class DeleteItemRequest {

    private String id;

    public DeleteItemRequest() {}

    public DeleteItemRequest(String id) {
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
