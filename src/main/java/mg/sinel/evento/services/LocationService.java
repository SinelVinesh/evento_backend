package mg.sinel.evento.services;

import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import mg.sinel.evento.models.Location;
import mg.sinel.evento.models.LocationSeatCategory;
import mg.sinel.evento.repositories.LocationRepo;
import org.springframework.stereotype.Service;


@Service
public class LocationService extends CrudService<Location, LocationRepo> {
    private final StorageService storageService;

    public LocationService(LocationRepo repo, EntityManager manager, StorageService storageService) {
        super(repo, manager);
        this.storageService = storageService;
    }

    @Override
    public Location create(Location obj) throws Exception {
        for (LocationSeatCategory locationSeatCategory : obj.getLocationSeatCategories()) {
            locationSeatCategory.setLocation(obj);
        }
        return super.create(obj);
    }

    @Override
    public Location update(Location obj) throws Exception {
        for (LocationSeatCategory locationSeatCategory : obj.getLocationSeatCategories()) {
            locationSeatCategory.setLocation(obj);
        }
        return super.update(obj);
    }

    @Override
    public Class<Location> getEntityClass() {
        return Location.class;
    }
}
