package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.RatedExpenseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.RatedExpense;

@RestController
@RequestMapping("/rated-expenses")
public class RatedExpenseController extends CrudController<RatedExpense, RatedExpenseService, Object> {

    public RatedExpenseController(RatedExpenseService service) {
        super(service);
    }

}
