package mg.sinel.evento.services;

import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import mg.sinel.evento.models.EventSeatCategory;
import mg.sinel.evento.repositories.EventSeatCategoryRepo;
import org.springframework.stereotype.Service;


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
