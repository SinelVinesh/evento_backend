package mg.sinel.evento.services;

import mg.sinel.evento.repositories.ExpenseTypeRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import mg.sinel.evento.models.ExpenseType;


@Service
public class ExpenseTypeService extends CrudService<ExpenseType, ExpenseTypeRepo> {

    public ExpenseTypeService(ExpenseTypeRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<ExpenseType> getEntityClass() {
        return ExpenseType.class;
    }

}
