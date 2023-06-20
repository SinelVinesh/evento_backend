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
@Table(name = "event_seat_category")
public class EventSeatCategory extends HasId {

    private BigDecimal price;
    @ManyToOne()
    @JoinColumn(name = "location_seat_category_id")
    private LocationSeatCategory locationSeatCategory;
    @ManyToOne()
    @JoinColumn(name = "event_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Event event;

}