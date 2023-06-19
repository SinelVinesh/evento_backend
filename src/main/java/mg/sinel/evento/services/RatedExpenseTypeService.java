package mg.sinel.evento.services;

import mg.sinel.evento.repositories.RatedExpenseTypeRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import mg.sinel.evento.models.RatedExpenseType;


@Service
public class RatedExpenseTypeService extends CrudService<RatedExpenseType, RatedExpenseTypeRepo> {

    public RatedExpenseTypeService(RatedExpenseTypeRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<RatedExpenseType> getEntityClass() {
        return RatedExpenseType.class;
    }

}
