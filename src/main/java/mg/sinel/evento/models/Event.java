package mg.sinel.evento.models;

import custom.springutils.model.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "event")
public class Event extends HasId {

    private Timestamp endDate;
    private Integer tourId;
    private Boolean deleted;
    @ManyToOne()
    @JoinColumn(name = "event_type_id")
    private EventType eventType;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "location_id")
    private Location location;
    private BigDecimal locationPrice;
    private Timestamp startDate;
    @ManyToOne()
    @JoinColumn(name = "event_status_id")
    private EventStatus eventStatus;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventExpense> variableExpenses;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventRatedExpense> ratedExpenses;
}