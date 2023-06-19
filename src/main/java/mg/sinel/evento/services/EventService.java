package mg.sinel.evento.services;

import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import mg.sinel.evento.models.Event;
import mg.sinel.evento.models.EventExpense;
import mg.sinel.evento.models.EventRatedExpense;
import mg.sinel.evento.models.EventStatus;
import mg.sinel.evento.repositories.EventExpenseRepo;
import mg.sinel.evento.repositories.EventRatedExpenseRepo;
import mg.sinel.evento.repositories.EventRepo;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class EventService extends CrudService<Event, EventRepo> {
    private final EventRatedExpenseRepo eventRatedExpenseRepo;
    private final EventExpenseRepo eventExpenseRepo;

    public EventService(EventRepo repo, EntityManager manager, EventRatedExpenseRepo eventRatedExpenseRepo, EventExpenseRepo eventExpenseRepo) {
        super(repo, manager);
        this.eventRatedExpenseRepo = eventRatedExpenseRepo;
        this.eventExpenseRepo = eventExpenseRepo;
    }

    @Override
    public Class<Event> getEntityClass() {
        return Event.class;
    }

    @Override
    public Event create(Event obj) throws Exception {
        EventStatus status = new EventStatus();
        obj.setDeleted(false);
        status.setId(1L);
        obj.setEventStatus(status);
        for (EventExpense expense : obj.getVariableExpenses()) {
            expense.setId(null);
            expense.setEvent(obj);
        }
        for (EventRatedExpense expense : obj.getRatedExpenses()) {
            expense.setId(null);
            expense.setEvent(obj);
        }
        return super.create(obj);
    }

    @Override
    @Transactional
    public Event update(Event obj) throws Exception {
        eventExpenseRepo.deleteByEventId(obj.getId());
        eventRatedExpenseRepo.deleteByEventId(obj.getId());
        for (EventExpense expense : obj.getVariableExpenses()) {
            expense.setId(null);
            expense.setEvent(obj);
        }
        for (EventRatedExpense expense : obj.getRatedExpenses()) {
            expense.setId(null);
            expense.setEvent(obj);
        }
        return super.update(obj);
    }
}
