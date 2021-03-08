package Request.Abstract;

public abstract class PaginatedRequest {

    private int offset;

    public PaginatedRequest() {}

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
