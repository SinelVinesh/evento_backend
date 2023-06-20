package mg.sinel.evento.models.filters;

import lombok.Getter;
import lombok.Setter;
import mg.sinel.evento.models.RatedExpenseType;

@Getter
@Setter
public class RatedExpenseFilter {
    private RatedExpenseType ratedExpenseType;
    private RatedExpenseType noteq_ratedExpenseType;
}
