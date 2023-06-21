package mg.sinel.evento.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "v_event_estimation")
@Immutable
public class VEventEstimation extends HasId {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private BigDecimal totalExpense;
    private BigDecimal estimateIncome;
    private BigDecimal realIncome;
    private BigDecimal realIncomeWithTaxe;
}