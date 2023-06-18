package custom.springutils.search.map;

import custom.springutils.search.OrderMethod;
import custom.springutils.search.OrderObject;

import java.util.List;
import java.util.Map;

public class FilterInfo {
    Map<String, Object> conditions;
    List<OrderObject> orders;

    public FilterInfo(Map<String, Object> conditions, List<OrderObject> orders) {
        this.conditions = conditions;
        this.orders = orders;
    }

    public Map<String, Object> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }

    public List<OrderObject> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderObject> orders) {
        this.orders = orders;
    }
}
