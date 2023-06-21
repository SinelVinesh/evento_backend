package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.SettingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.Setting;

@RestController
@RequestMapping("/settings")
public class SettingController extends CrudController<Setting, SettingService, Object> {

    public SettingController(SettingService service) {
        super(service);
    }

}
