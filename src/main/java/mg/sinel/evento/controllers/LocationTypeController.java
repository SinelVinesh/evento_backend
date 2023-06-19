package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.LocationTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.LocationType;

@RestController
@RequestMapping("/location-types")
public class LocationTypeController extends CrudController<LocationType, LocationTypeService, Object> {

    public LocationTypeController(LocationTypeService service) {
        super(service);
    }

}
