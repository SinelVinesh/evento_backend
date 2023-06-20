package mg.sinel.evento.services;

import mg.sinel.evento.repositories.EventSeatCategoryRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import mg.sinel.evento.models.LocationSeatCategory;
import java.lang.Integer;
import mg.sinel.evento.models.Event;
import mg.sinel.evento.models.EventSeatCategory;


@Service
public class EventSeatCategoryService extends CrudService<EventSeatCategory, EventSeatCategoryRepo> {

    public EventSeatCategoryService(EventSeatCategoryRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<EventSeatCategory> getEntityClass() {
        return EventSeatCategory.class;
    }

}
