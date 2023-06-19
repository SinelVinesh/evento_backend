package mg.sinel.evento.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "rated_expense_type")
public class RatedExpenseType extends HasId {

    private String name;
    @ManyToOne()
    @JoinColumn(name = "rate_type_id")
    private RateType rateType;

}