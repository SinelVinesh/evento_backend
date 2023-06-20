package mg.sinel.evento.services;

import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import mg.sinel.evento.models.*;
import mg.sinel.evento.repositories.EventExpenseRepo;
import mg.sinel.evento.repositories.EventRatedExpenseRepo;
import mg.sinel.evento.repositories.EventRepo;
import mg.sinel.evento.repositories.EventSeatCategoryRepo;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class EventService extends CrudService<Event, EventRepo> {
    private final EventRatedExpenseRepo eventRatedExpenseRepo;
    private final EventExpenseRepo eventExpenseRepo;
    private final EventSeatCategoryRepo eventSeatCategoryRepo;

    public EventService(EventRepo repo, EntityManager manager, EventRatedExpenseRepo eventRatedExpenseRepo, EventExpenseRepo eventExpenseRepo, EventSeatCategoryRepo eventSeatCategoryRepo) {
        super(repo, manager);
        this.eventRatedExpenseRepo = eventRatedExpenseRepo;
        this.eventExpenseRepo = eventExpenseRepo;
        this.eventSeatCategoryRepo = eventSeatCategoryRepo;
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
        for (EventSeatCategory eventSeatCategory : obj.getEventSeatCategories()) {
            eventSeatCategory.setId(null);
            eventSeatCategory.setEvent(obj);
        }
        return super.create(obj);
    }

    @Override
    @Transactional
    public Event update(Event obj) throws Exception {
        eventExpenseRepo.deleteByEventId(obj.getId());
        eventRatedExpenseRepo.deleteByEventId(obj.getId());
        eventSeatCategoryRepo.deleteByEventId(obj.getId());
        for (EventExpense expense : obj.getVariableExpenses()) {
            expense.setId(null);
            expense.setEvent(obj);
        }
        for (EventRatedExpense expense : obj.getRatedExpenses()) {
            expense.setId(null);
            expense.setEvent(obj);
        }
        for (EventSeatCategory eventSeatCategory : obj.getEventSeatCategories()) {
            eventSeatCategory.setId(null);
            eventSeatCategory.setEvent(obj);
        }
        return super.update(obj);
    }
}
