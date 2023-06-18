package custom.springutils.search;

public class OrderObject implements Comparable<OrderObject> {

    int order;
    String col;
    OrderMethod method;

    public OrderObject(int order, String col, OrderMethod method) {
        this.order = order;
        this.col = col;
        this.method = method;
    }

    @Override
    public int compareTo(OrderObject o) {
        return order - o.order;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public OrderMethod getMethod() {
        return method;
    }

    public void setMethod(OrderMethod method) {
        this.method = method;
    }
}
