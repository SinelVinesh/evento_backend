package mg.sinel.evento.services;

import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import mg.sinel.evento.models.Event;
import mg.sinel.evento.models.EventExpense;
import mg.sinel.evento.models.EventRatedExpense;
import mg.sinel.evento.models.EventStatus;
import mg.sinel.evento.repositories.EventRepo;
import org.springframework.stereotype.Service;


@Service
public class EventService extends CrudService<Event, EventRepo> {

    public EventService(EventRepo repo, EntityManager manager) {
        super(repo, manager);
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
}
