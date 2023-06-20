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
@Table(name = "location_seat_category")
public class LocationSeatCategory extends HasId {

    @ManyToOne()
    @JoinColumn(name = "seat_category_id")
    private SeatCategory seatCategory;
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Location location;
}