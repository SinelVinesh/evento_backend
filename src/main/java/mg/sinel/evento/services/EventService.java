package mg.sinel.evento.services;

import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import mg.sinel.evento.models.Event;
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
        return obj;
    }
}
