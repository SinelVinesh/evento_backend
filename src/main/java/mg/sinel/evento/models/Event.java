package mg.sinel.evento.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;
import java.lang.Integer;
import java.lang.Boolean;
import mg.sinel.evento.models.EventType;
import java.lang.String;
import mg.sinel.evento.models.Location;
import java.math.BigDecimal;
import mg.sinel.evento.models.EventStatus;


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

}