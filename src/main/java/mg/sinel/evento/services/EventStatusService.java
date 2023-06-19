package mg.sinel.evento.services;

import mg.sinel.evento.repositories.EventStatusRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import mg.sinel.evento.models.EventStatus;


@Service
public class EventStatusService extends CrudService<EventStatus, EventStatusRepo> {

    public EventStatusService(EventStatusRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<EventStatus> getEntityClass() {
        return EventStatus.class;
    }

}
