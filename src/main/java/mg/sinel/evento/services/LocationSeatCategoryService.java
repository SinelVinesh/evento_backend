package mg.sinel.evento.services;

import mg.sinel.evento.repositories.LocationSeatCategoryRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import mg.sinel.evento.models.SeatCategory;
import java.lang.Integer;
import mg.sinel.evento.models.LocationSeatCategory;


@Service
public class LocationSeatCategoryService extends CrudService<LocationSeatCategory, LocationSeatCategoryRepo> {

    public LocationSeatCategoryService(LocationSeatCategoryRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<LocationSeatCategory> getEntityClass() {
        return LocationSeatCategory.class;
    }

}
