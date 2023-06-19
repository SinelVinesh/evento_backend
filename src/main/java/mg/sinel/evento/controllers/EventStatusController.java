package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.EventStatusService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.EventStatus;

@RestController
@RequestMapping("/event-statuss")
public class EventStatusController extends CrudController<EventStatus, EventStatusService, Object> {

    public EventStatusController(EventStatusService service) {
        super(service);
    }

}
