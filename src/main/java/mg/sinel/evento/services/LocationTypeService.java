package mg.sinel.evento.services;

import mg.sinel.evento.repositories.LocationTypeRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import mg.sinel.evento.models.LocationType;


@Service
public class LocationTypeService extends CrudService<LocationType, LocationTypeRepo> {

    public LocationTypeService(LocationTypeRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<LocationType> getEntityClass() {
        return LocationType.class;
    }

}
