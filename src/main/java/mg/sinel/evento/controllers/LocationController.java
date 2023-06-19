package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.LocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.Location;

@RestController
@RequestMapping("/locations")
public class LocationController extends CrudController<Location, LocationService, Object> {

    public LocationController(LocationService service) {
        super(service);
    }

}
