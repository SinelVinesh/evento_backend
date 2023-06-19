package mg.sinel.evento.services;

import mg.sinel.evento.repositories.LocationRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.String;
import mg.sinel.evento.models.Location;


@Service
public class LocationService extends CrudService<Location, LocationRepo> {

    public LocationService(LocationRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Location> getEntityClass() {
        return Location.class;
    }

}
