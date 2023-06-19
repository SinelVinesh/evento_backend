package mg.sinel.evento.services;

import mg.sinel.evento.repositories.EventRatedExpenseRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.Integer;
import mg.sinel.evento.models.Event;
import mg.sinel.evento.models.RatedExpense;
import mg.sinel.evento.models.EventRatedExpense;


@Service
public class EventRatedExpenseService extends CrudService<EventRatedExpense, EventRatedExpenseRepo> {

    public EventRatedExpenseService(EventRatedExpenseRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<EventRatedExpense> getEntityClass() {
        return EventRatedExpense.class;
    }

}
