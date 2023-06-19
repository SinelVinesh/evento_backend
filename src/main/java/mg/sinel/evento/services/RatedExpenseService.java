package mg.sinel.evento.services;

import mg.sinel.evento.repositories.RatedExpenseRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import mg.sinel.evento.models.RateType;
import java.math.BigDecimal;
import java.lang.String;
import java.lang.Integer;
import mg.sinel.evento.models.RatedExpense;


@Service
public class RatedExpenseService extends CrudService<RatedExpense, RatedExpenseRepo> {

    public RatedExpenseService(RatedExpenseRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<RatedExpense> getEntityClass() {
        return RatedExpense.class;
    }

}
