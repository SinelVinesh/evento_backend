package mg.sinel.evento.services;

import mg.sinel.evento.repositories.EventTypeRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import mg.sinel.evento.models.EventType;


@Service
public class EventTypeService extends CrudService<EventType, EventTypeRepo> {

    public EventTypeService(EventTypeRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<EventType> getEntityClass() {
        return EventType.class;
    }

}
