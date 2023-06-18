package custom.springutils.search.map;

import custom.springutils.exception.CustomException;
import custom.springutils.search.FilterObject;
import custom.springutils.search.OrderMethod;
import custom.springutils.search.OrderObject;

import java.lang.reflect.Field;
import java.util.*;

public class MapUtil {


    public static FilterInfo convert(Object obj) throws CustomException {
        Map<String, Object> map = new HashMap<>();
        List<OrderObject> orders = new ArrayList<>();
        try {
            if (obj instanceof FilterObject filterObject) {
                if (filterObject.getField() != null && filterObject.getMethod() != null)
                    orders.add(new OrderObject(0, filterObject.getField(), filterObject.getMethod()));
            }
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.isAnnotationPresent(IgnoreMapping.class)) {
                    Filter filter = field.getAnnotation(Filter.class);

                    if (filter != null) {
                        map.put(filter.value(), field.get(obj));
                    }
                    else {
                        map.put(field.getName(), field.get(obj));
                    }
                }
            }
        }
        catch (ReflectiveOperationException e) {
            throw new CustomException("internal error: "+e.getMessage(), e);
        }

        Collections.sort(orders);
        return new FilterInfo(map, orders);
    }

}
