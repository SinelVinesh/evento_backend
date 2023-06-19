package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.RatedExpenseTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.RatedExpenseType;

@RestController
@RequestMapping("/rated-expense-types")
public class RatedExpenseTypeController extends CrudController<RatedExpenseType, RatedExpenseTypeService, Object> {

    public RatedExpenseTypeController(RatedExpenseTypeService service) {
        super(service);
    }

}
