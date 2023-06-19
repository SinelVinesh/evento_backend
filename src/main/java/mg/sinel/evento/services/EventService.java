package mg.sinel.evento.services;

import mg.sinel.evento.repositories.EventRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.lang.Integer;
import java.lang.Boolean;
import mg.sinel.evento.models.EventType;
import java.lang.String;
import mg.sinel.evento.models.Location;
import java.math.BigDecimal;
import mg.sinel.evento.models.EventStatus;
import mg.sinel.evento.models.Event;


@Service
public class EventService extends CrudService<Event, EventRepo> {

    public EventService(EventRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Event> getEntityClass() {
        return Event.class;
    }

}
