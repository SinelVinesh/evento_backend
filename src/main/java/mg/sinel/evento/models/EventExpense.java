package mg.sinel.evento.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "event_expense")
public class EventExpense extends HasId {

    private BigDecimal amount;
    @ManyToOne()
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "expense_type_id")
    private ExpenseType expenseType;
    @ManyToOne()
    @JoinColumn(name = "event_id")
    private Event event;

}