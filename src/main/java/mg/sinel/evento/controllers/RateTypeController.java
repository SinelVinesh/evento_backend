package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.RateTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.RateType;

@RestController
@RequestMapping("/rate-types")
public class RateTypeController extends CrudController<RateType, RateTypeService, Object> {

    public RateTypeController(RateTypeService service) {
        super(service);
    }

}
