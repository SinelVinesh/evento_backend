package mg.sinel.evento.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "rated_expense")
public class RatedExpense extends HasId {

    @ManyToOne()
    @JoinColumn(name = "rate_type_id")
    private RateType rateType;
    private BigDecimal rentPrice;
    private String name;

    @ManyToOne
    @JoinColumn(name = "rated_expense_type_id")
    private RatedExpenseType ratedExpenseType;
}