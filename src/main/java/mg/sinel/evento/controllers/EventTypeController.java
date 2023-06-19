package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.EventTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.EventType;

@RestController
@RequestMapping("/event-types")
public class EventTypeController extends CrudController<EventType, EventTypeService, Object> {

    public EventTypeController(EventTypeService service) {
        super(service);
    }

}
