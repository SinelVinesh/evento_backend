package custom.springutils.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pdfutils.PDFUtils;
import custom.springutils.search.*;
import custom.springutils.search.map.FilterInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import custom.springutils.util.ListResponse;
import custom.springutils.search.map.MapUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public abstract class CrudService <E,  R extends JpaRepository<E, Long>> implements Service<E> {

    protected final R repo;

    protected final EntityManager manager;

    public CrudService(R repo, EntityManager manager) {
        this.repo = repo;
        this.manager = manager;
    }

    public byte[] createPDF () throws Exception {
        return PDFUtils.classicList((List)this.search(new Object(), null).getElements(), getEntityClass(), getPdfPath(), getPdfTitle());
    }

    public String getPdfPath () {
        return "pdf/" + getClassName() + ".pdf";
    }

    public String getPdfTitle () {
        return "List of " + getClassName().toLowerCase();
    }

    public abstract Class<?> getEntityClass();

    @Override
    public ListResponse search (Object filter, Integer page) throws Exception {
        return search(filter, MapUtil.convert(filter), page);
    }

    protected ListResponse search (Object filter, Integer page, Class<?> source) throws Exception {
        return search(filter, MapUtil.convert(filter), page, source);
    }

    protected ListResponse search(Object filter, FilterInfo criteria, Integer page, Class<?> source) throws Exception {
        StringBuilder query = new StringBuilder(" where true");
        List<Object> params = new ArrayList<>();

        int i = 1;
        for ( String key: criteria.getConditions().keySet() ) {
            Object val = criteria.getConditions().get(key);
            if (val == null) {
                continue;
            }
            FieldOperator op = FieldOperator.construct(key, val);
            Object index = "?"+i;
            if (op.operator == SearchOperator.isnotnull || op.operator == SearchOperator.isnull) {
                index = "";
            }
            else {
                params.add(val);
            }
            query.append(String.format(" and u.%s %s %s", op.field, op.operator.value, index));
            i++;
        }

        List<Condition> conditions = getAdditionalConditionFrom(filter);
        for (Condition cond: conditions) {
            query.append(cond.getCondition());
        }

        StringBuilder selectQuery = new StringBuilder(query.toString());

        if (!criteria.getOrders().isEmpty()) {
            selectQuery.append(" order by ");
            int j = 0;
            for (OrderObject order: criteria.getOrders()) {
                if (j > 0) {
                    selectQuery.append(", ");
                }
                selectQuery.append("u.").append(order.getCol()).append(order.getMethod().value);
                j++;
            }
        }

        Query q = manager.createQuery("select u from " + source.getSimpleName()+ " u " + selectQuery, getEntityClass());
        Query c = manager.createQuery("select count(u.id) from " + source.getSimpleName()+ " u " + query, Long.class);

        populateQuery(params, conditions, q, c);

        ListResponse response = new ListResponse();

        if (page != null) {
            q.setFirstResult((page - 1) * getPageSize());
            q.setMaxResults(getPageSize());
        }

        response.setElements(q.getResultList());
        response.setCount((Long) c.getResultList().get(0));

        if (page != null) {
            response.setPage(page.longValue());
            response.setPageSize(getPageSize());
        }
        else {
            response.setPageSize(response.getCount().intValue());
            response.setPage(1L);
        }
        return response;
    }

    public List<Condition> getAdditionalConditionFrom(Object filter) throws Exception{
        return new ArrayList<>();
    }

    protected ListResponse search (Object filter, FilterInfo criteria, Integer page) throws Exception {
        return search(filter, criteria, page, getEntityClass());
    }

    public String getClassName () {
        return getEntityClass().getSimpleName();
    }

    private void populateQuery(List<Object> params, List<Condition> conditions, Query... query)  {
        for (int i = 0; i < params.size(); i++) {
            for (Query q: query) {
                q.setParameter(i + 1, params.get(i));
            }
        }

        for (Condition condition: conditions) {
            for (Query q: query) {
                if (condition.getValue() != null) {
                    q.setParameter(condition.getName(), condition.getValue());
                }
            }
        }
    }

    @Override
    public List<E> findAll(int page) throws Exception {
        return repo.findAll(Pageable.ofSize(getPageSize()).withPage(page)).getContent();
    }

    @Override
    public E create(E obj) throws Exception {
        return repo.save(obj);
    }

    @Override
    public E update(E obj) throws Exception {
        return repo.save(obj);
    }

    @Override
    public void delete(Long id) throws Exception{
        repo.deleteById(id);
    }

    @Override
    public E findById(Long id) throws Exception{
        return repo.findById(id).orElse(null);
    }

    @Override
    public Iterable<E> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public List<E>saveAll(List<E> iterable) throws Exception{
        return repo.saveAll(iterable);        
    }

    @Override
    public int getPageSize() {
        return 10;
    }
}

