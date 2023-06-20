package mg.sinel.evento.models;

import custom.springutils.model.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "location")
public class Location extends HasId {
    
    private String name;
    @ManyToOne
    @JoinColumn(name = "location_type")
    private LocationType locationType;
    private String imageLink;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<LocationSeatCategory> locationSeatCategories;

}