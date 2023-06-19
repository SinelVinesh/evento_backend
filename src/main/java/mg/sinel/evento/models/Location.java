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
@Table(name = "location")
public class Location extends HasId {

    private Integer maxCapacity;
    private BigDecimal rentPrice;
    private String name;
    @ManyToOne
    @JoinColumn(name = "location_type")
    private LocationType locationType;

}