package mg.sinel.evento.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.String;


@Getter
@Setter
@Entity
@Table(name = "location")
public class Location extends HasId {

	private Integer maxCapacity;
	private BigDecimal rentPrice;
	private String name;
	private Integer locationType;

}