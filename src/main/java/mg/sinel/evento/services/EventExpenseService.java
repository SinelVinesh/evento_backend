package mg.sinel.evento.services;

import mg.sinel.evento.repositories.EventExpenseRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import mg.sinel.evento.models.ExpenseType;
import java.lang.Integer;
import mg.sinel.evento.models.Event;
import mg.sinel.evento.models.EventExpense;


@Service
public class EventExpenseService extends CrudService<EventExpense, EventExpenseRepo> {

    public EventExpenseService(EventExpenseRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<EventExpense> getEntityClass() {
        return EventExpense.class;
    }

}
