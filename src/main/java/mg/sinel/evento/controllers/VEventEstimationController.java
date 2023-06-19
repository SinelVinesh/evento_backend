package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.models.VEventEstimation;
import mg.sinel.evento.services.VEventEstimationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event-estimations")
public class VEventEstimationController extends CrudController<VEventEstimation, VEventEstimationService, Object> {

    public VEventEstimationController(VEventEstimationService service) {
        super(service);
    }

}
