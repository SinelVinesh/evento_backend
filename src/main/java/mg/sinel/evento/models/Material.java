package mg.sinel.evento.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.lang.String;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "material")
public class Material extends HasId {

	private BigDecimal rentPrice;
	private String name;

}