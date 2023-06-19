package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.MaterialService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.Material;

@RestController
@RequestMapping("/materials")
public class MaterialController extends CrudController<Material, MaterialService, Object> {

    public MaterialController(MaterialService service) {
        super(service);
    }

}
