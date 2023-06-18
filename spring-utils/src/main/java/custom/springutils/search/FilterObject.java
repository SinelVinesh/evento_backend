package custom.springutils.search;

public class FilterObject {
    private String field;
    private OrderMethod method;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public OrderMethod getMethod() {
        return method;
    }

    public void setMethod(OrderMethod method) {
        this.method = method;
    }
}
