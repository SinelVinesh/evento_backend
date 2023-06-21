package mg.sinel.evento.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.String;
import java.lang.Integer;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "setting")
public class Setting extends HasId {

	private String name;
	private BigDecimal value;

}