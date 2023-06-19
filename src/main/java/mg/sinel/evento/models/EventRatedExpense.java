package mg.sinel.evento.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "event_rated_expense")
public class EventRatedExpense extends HasId {

    private Integer duration;
    @ManyToOne()
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne()
    @JoinColumn(name = "rated_expense_id")
    private RatedExpense ratedExpense;

}