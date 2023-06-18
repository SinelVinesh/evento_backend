package custom.springutils.service;

import java.util.List;
import java.util.Map;

import com.pdfutils.PDFUtils;
import custom.springutils.exception.CustomException;
import custom.springutils.search.map.FilterInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import custom.springutils.util.ListResponse;
import custom.springutils.search.map.MapUtil;
import jakarta.persistence.EntityManager;

/*
*
*
*/
public abstract class CrudServiceWithFK<E, FK, FKR extends JpaRepository<FK, Long>, R extends JpaRepository<E, Long> > extends CrudService<E, R> implements ServiceWithFK<E, FK> {

    protected FKR fkRepo;

    public CrudServiceWithFK(R repo, EntityManager manager, FKR fkr) {
        super(repo, manager);
        this.fkRepo = fkr;
    }

    public byte[] createPDF (Long fkId) throws Exception {
        return PDFUtils.classicList((List)this.search(new Object(),fkId, null).getElements(), getEntityClass(), getPdfPath(), getPdfTitle());
    }

    public ListResponse search (Object filter, Long fkId, Integer page) throws Exception {
        FilterInfo map = MapUtil.convert(filter);
        FK fk = this.fkRepo.findById(fkId).orElse(null);
        if (fk == null) {
            throw new CustomException("FK not found");
        }
        map.getConditions().put(getFkName(), getFKValue(fk));
        return search(filter,map, page);
    }

    public abstract Object getFKValue (FK fk);

}
