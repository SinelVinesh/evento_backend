package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.Event;

@RestController
@RequestMapping("/events")
public class EventController extends CrudController<Event, EventService, Object> {

    public EventController(EventService service) {
        super(service);
    }

}
