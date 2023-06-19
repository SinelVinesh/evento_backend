package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.services.ExpenseTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mg.sinel.evento.models.ExpenseType;

@RestController
@RequestMapping("/expense-types")
public class ExpenseTypeController extends CrudController<ExpenseType, ExpenseTypeService, Object> {

    public ExpenseTypeController(ExpenseTypeService service) {
        super(service);
    }

}
